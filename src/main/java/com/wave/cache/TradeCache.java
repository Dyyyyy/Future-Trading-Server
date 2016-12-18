package com.wave.cache;

import com.wave.repository.CacheRepository.FutureExchangeRepository;
import com.wave.staticsetting.StaticConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;

import net.sf.json.JSONObject;

/**
 * Created by Json on 2016/12/5.
 */
public class TradeCache {
    private List<FuturesExchange> exchanges;
    private String contracts_url;
    private int contract_count;
    @Autowired
    private FutureExchangeRepository repository;

    static private TradeCache trade_cache=new TradeCache();
    /**
     * init TradeCache
     */
    private TradeCache(){
        exchanges = null;
        contracts_url = StaticConfig.CONTRACT_CURRENT_URL;
        contract_count = 0;
    }
    static public TradeCache getInstance(){
        return trade_cache;
    }

    public void init(){
        exchanges=repository.findAll();
        for(FuturesExchange exchange:exchanges){
            exchange.init();
        }
        configContractUrl();
    }
    /**
     * update the data
     */
    @Scheduled(cron = "* * 9-11,14-15,21-23 ? * MON-FRI")
    public void updateDataSlotOne() {
        refresh();
    }

    @Scheduled(cron = "* 0-30 11,23 ? * MON-FRI")
    public void updateDataSlotTwo() {
        refresh();
    }

    @Scheduled(cron = "* 30-60 13 ? * MON-FRI")
    public void updateDataSlotThree() {
        refresh();
    }

    public void configContractUrl() {
        Calendar calendar = Calendar.getInstance();
        int current_year = calendar.YEAR;

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
        try {
            URI uri = new URI(contracts_url);
            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
            ClientHttpRequest request = schr.createRequest(uri, HttpMethod.GET);
            ClientHttpResponse response = request.execute();
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));

            String str;
            String[][] p_f_data = new String[contract_count][13];
            Date current_date=Calendar.getInstance().getTime();
            int x=0;
            int y = 0;
            int pos=0;
            while ((str = br.readLine()) != null && pos < contract_count) {
                p_f_data[x][y] = str;
                pos++;
                x=pos/13;
                y=pos%13;
            }
            x=0;
            for (FuturesExchange exchange : exchanges) {
                for (ProductFuture future : exchange.getFutures()) {
                    if(x<contract_count) {
                        future.refreshContracts(p_f_data[x], current_date);
                        x++;
                    }
                    else break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshContractItem(){
        List<FuturesExchange> exchanges=repository.findAll();

        String result="";
        for(FuturesExchange exchange:exchanges){
            String url=StaticConfig.CONTRACT_ITEM_URL;
            url+="key="+StaticConfig.APP_KEY;
            url+="&exchangeID="+exchange.getAbbreviation();
            try{
                URI uri = new URI(contracts_url);
                SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
                ClientHttpRequest request = schr.createRequest(uri, HttpMethod.GET);
                ClientHttpResponse response = request.execute();
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));

                StringBuffer sb=new StringBuffer();
                String str;
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
                str = sb.toString();
                JSONObject object=JSONObject.fromObject(str);
                if(object.getInt("error_code")==0){
                    System.out.println(object.get("result"));
                }else{
                    System.out.println(object.get("error_code")+":"+object.get("reason"));
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
