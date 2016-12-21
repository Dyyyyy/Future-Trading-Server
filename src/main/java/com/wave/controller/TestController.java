package com.wave.controller;

import com.wave.cache.FuturesExchange;
import com.wave.cache.TradeCache;
import com.wave.model.ContractItem;
import com.wave.repository.CacheRepository.FutureExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @RequestMapping(value = "/test/contract_item")
    public boolean testContractItem(){
        return true;
    }

    @RequestMapping(value = "/test/future_exchange")
    public List<FuturesExchange> testFutureExchange(){
        List<FuturesExchange> error=new ArrayList<>();
        FuturesExchange exchange=new FuturesExchange();
        exchange.setAbbreviation("error");
        exchange.setExchange_name("error_name");
        error.add(exchange);

        error.add(new FuturesExchange());

        List<FuturesExchange> exchanges=repository.findAll();
        if(exchanges!=null){
            return exchanges;
        }
        else return error;
    }

    @RequestMapping(value = "/test/insert_future_exchange")
    public FuturesExchange testInsertFutureExchange(){
        FuturesExchange exchange=new FuturesExchange();
        exchange.setAbbreviation("error");
        exchange.setExchange_name("error_name");
        repository.save(exchange);
        return exchange;
    }
}
