package com.wave.controller;

import com.wave.cache.TradeCache;
import com.wave.model.ContractItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Json on 2016/12/19.
 */
@RestController
public class ContractController {
    @Autowired
    private TradeCache tradeCache;
    @RequestMapping(value = "/contract/item/{ticker}")
    public ContractItem getContract(@PathVariable String ticker){
        return tradeCache.getContract(ticker);
    }
}
