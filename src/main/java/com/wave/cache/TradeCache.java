package com.wave.cache;

import com.wave.model.ContractItem;
import com.wave.staticsetting.StaticConfig;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by Json on 2016/12/5.
 */
public class TradeCache {
    private ArrayList<FuturesExchange> exchanges = new ArrayList<FuturesExchange>();
    private String contracts_url = StaticConfig.CONTRACT_CURRENT_URL;
    private int contract_count = 0;

    /**
     * update the data
     */
    @Scheduled(cron = "0 * 9-11,14-15,21-23 ? * MON-FRI")
    public void updateDataSlotOne() {

    }

    @Scheduled(cron = "0 0-30 11,23 ? * MON-FRI")
    public void updateDataSlotTwo() {

    }

    @Scheduled(cron = "0 30-60 13 ? * MON-FRI")
    public void updateDataSlotThree() {

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
                        future.refreshContracts(p_f_data[x]);
                        x++;
                    }
                    else break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
