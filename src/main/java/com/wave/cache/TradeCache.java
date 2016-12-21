package com.wave.cache;

import com.wave.model.ContractItem;
import com.wave.model.HistoryTrade;
import com.wave.repository.CacheRepository.FutureExchangeRepository;
import com.wave.repository.CacheRepository.ProductFutureRepository;
import com.wave.repository.EntityRepository.ContractItemRepository;
import com.wave.repository.EntityRepository.HistoryTradeRepository;
import com.wave.staticsetting.StaticConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.mapping.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Json on 2016/12/5.
 */

@Component
public class TradeCache {
    private List<FuturesExchange> exchanges;
    private HashMap<String, ContractItem> item_map = new HashMap<>();
    private String contracts_url;
    private int contract_count;

    @Autowired
    FutureExchangeRepository f_repository;
    @Autowired
    ProductFutureRepository p_repository;
    @Autowired
    ContractItemRepository c_repository;
    @Autowired
    HistoryTradeRepository h_repository;

    /**
     * init TradeCache
     */
    public TradeCache() {
        exchanges = null;
        contracts_url = StaticConfig.CONTRACT_CURRENT_URL;
        contract_count = 0;
    }

    public void init() {
        exchanges = f_repository.findAll();
        for (FuturesExchange exchange : exchanges) {
            exchange.init(p_repository, c_repository);
            Collection<ProductFuture> futures = exchange.getFutures();
            for (ProductFuture future : futures) {
                ContractItem[] items = future.getItems();
                for (ContractItem item : items) {
                    if (item != null) {
                        item_map.put(item.getTicker(), item);
                    }
                }
            }
        }
        configContractUrl();
    }

    public String getContracts_url() {
        return this.contracts_url;
    }

//    /**
//     * update the data
//     */
//    @Scheduled(cron = "* * 9-11,14-15,21-23 ? * MON-FRI")
//    public void updateDataSlotOne() {
//        refresh();
//    }
//
//    @Scheduled(cron = "* 0-30 11,23 ? * MON-FRI")
//    public void updateDataSlotTwo() {
//        refresh();
//    }
//
//    @Scheduled(cron = "* 30-59 13 ? * MON-FRI")
//    public void updateDataSlotThree() {
//        refresh();
//    }

    public void configContractUrl() {
        Calendar calendar = Calendar.getInstance();
        int current_year = calendar.get(Calendar.YEAR) % 100 + 1;

        contract_count = 0;
        contracts_url = StaticConfig.CONTRACT_CURRENT_URL;
        for (FuturesExchange exchange : exchanges) {
            Set<String> set = exchange.getAbbrList();
            for (String string : set) {
                contracts_url += string + "0" + ",";
                for (int i = 1; i <= 12; i++) {
                    if (i <= 10) {
                        contracts_url += string + current_year + "0" + i + ",";
                    } else contracts_url += string + current_year + i + ",";
                }
                contract_count++;
            }
        }
        contracts_url = contracts_url.substring(0, contracts_url.length() - 1);
    }

    private void refresh() {
        Date time = Calendar.getInstance().getTime();
        System.out.println("************" + time);
        try {
            URI uri = new URI(contracts_url);
            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
            ClientHttpRequest request = schr.createRequest(uri, HttpMethod.GET);
            ClientHttpResponse response = request.execute();
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));

            String str;
            String[][] p_f_data = new String[contract_count][13];
            Date current_date = Calendar.getInstance().getTime();
            int x = 0;
            int y = 0;
            int pos = 0;
            while ((str = br.readLine()) != null && x < contract_count) {
                p_f_data[x][y] = str;
                pos++;
                x = pos / 13;
                y = pos % 13;
            }
            x = 0;
            List<HistoryTrade> trades=new ArrayList<>();
            for (FuturesExchange exchange : exchanges) {
                for (ProductFuture future : exchange.getFutures()) {
                    if (x < contract_count) {
                        future.refreshContracts(p_f_data[x], current_date,trades);
                        x++;
                    } else break;
                }
            }
            h_repository.save(trades);
            System.out.println("trades: "+trades.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * init future exchange, product future and contract item.
     *
     * @return
     */

    private boolean initFoundationData() {
        List<FuturesExchange> exchanges = initExchanges();
        List<ProductFuture> futures = new ArrayList<>();
        List<ContractItem> items = new ArrayList<>();

        try {
            for (FuturesExchange exchange : exchanges) {
                String url = StaticConfig.CONTRACT_ITEM_URL;
                url += exchange.getAbbreviation();

                //access item data
                HttpGet get = new HttpGet(url);
                get.addHeader("Authorization", "Bearer " + StaticConfig.ACCESS_TOKEN);
                CloseableHttpClient client = createHttpsClient();
                CloseableHttpResponse response = client.execute(get);
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);

                //handle
                JSONObject object = JSONObject.fromObject(body);
                JSONArray array = object.getJSONArray("data");
                System.out.println(exchange.getExchange_name() + ": " + array.size());

                String product_name = "";          //etc "沪白银"
                for (int i = 0; i < array.size(); i++) {
                    ContractItem contract_item = (ContractItem) JSONObject.toBean(array.getJSONObject(i), ContractItem.class);
                    String item_name = contract_item.getSecShortName();
                    String tmp_name = item_name.substring(0, item_name.length() - 4);
                    if (!tmp_name.equals(product_name)) {
                        ContractItem item_0 = (ContractItem) contract_item.clone();
                        String contract_obj=item_0.getContractObject();
                        //init future 0
                        item_0.setDeliMonth(0);
                        item_0.setDeliYear(0);

                        item_0.setTicker(contract_obj.toLowerCase()+0);
                        item_0.setSecID(contract_obj+0+"."+exchange.getAbbreviation());

                        item_0.setSecShortName(tmp_name+"连续");
                        item_0.setSecFullName(tmp_name+"连续合约");

                        item_0.setFirstDeliDate("");
                        item_0.setLastDeliDate("");
                        item_0.setLastDeliDate("");
                        item_0.setListDate("");
                        items.add(item_0);

                        product_name = tmp_name;
                        ProductFuture future = new ProductFuture();
                        future.setFutures_exchange(exchange);
                        future.setProduct_name(product_name);
                        future.setAbbreviation(contract_item.getContractObject());
                        futures.add(future);
                    }
                    items.add(contract_item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        f_repository.save(exchanges);
        p_repository.save(futures);
        c_repository.save(items);
        return true;
    }

    private CloseableHttpClient createHttpsClient() {
        X509TrustManager x509mgr = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        //因为java客户端要进行安全证书的认证，这里我们设置ALLOW_ALL_HOSTNAME_VERIFIER来跳过认证，否则将报错
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509mgr}, null);
            sslsf = new SSLConnectionSocketFactory(
                    sslContext,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    private List<FuturesExchange> initExchanges() {
        List<FuturesExchange> exchanges = new ArrayList<>();
        FuturesExchange exchange;

        //Shanghai Futures Exchange
        exchange = new FuturesExchange();
        exchange.setExchange_name("Shanghai Futures Exchange");
        exchange.setAbbreviation("XSGE");
        exchanges.add(exchange);

        //Dalian Commodity Exchange
        exchange = new FuturesExchange();
        exchange.setExchange_name("Dalian Commodity Exchange");
        exchange.setAbbreviation("XDCE");
        exchanges.add(exchange);

        //Zhengzhou Commodity Exchange
        exchange = new FuturesExchange();
        exchange.setExchange_name("Zhengzhou Commodity Exchange");
        exchange.setAbbreviation("XZCE");
        exchanges.add(exchange);

        //China Financial Futures Exchange
        exchange = new FuturesExchange();
        exchange.setExchange_name("China Financial Futures Exchange");
        exchange.setAbbreviation("CCFX");
        exchanges.add(exchange);
        return exchanges;
    }

    /**
     * Universal API
     */

    public ContractItem getContract(String ticker) {
        return item_map.get(ticker.toLowerCase());
    }
}
