package com.wave.controller;

import com.wave.cache.TradeCache;
import com.wave.repository.CacheRepository.FutureExchangeRepository;

import com.wave.staticsetting.StaticConfig;
import net.sf.json.JSONArray;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Json on 2016/12/18.
 */
@RestController
public class TestController {
    @Autowired
    FutureExchangeRepository repository;
    @Autowired
    TradeCache tradeCache;
    
    @RequestMapping(value = "/test/configure_url")
    public String testConfigUrl(){
        return tradeCache.getContracts_url();
    }

    @RequestMapping(value = "/test/init")
    public boolean testinit(){
        return true;
    }

    @RequestMapping(value = "/test/5min")
    public int count(){
        try {
            URI uri = new URI("http://stock2.finance.sina.com.cn/futures/api/json.php/IndexService.getInnerFuturesMiniKLine10m?symbol=M0");
            HttpGet get = new HttpGet(uri);
            get.addHeader("Authorization", "Bearer " + StaticConfig.ACCESS_TOKEN);
            CloseableHttpClient client = StaticConfig.createHttpsClient();
            CloseableHttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity);
            JSONArray array=JSONArray.fromObject(body);
            return array.size();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    

    
}
