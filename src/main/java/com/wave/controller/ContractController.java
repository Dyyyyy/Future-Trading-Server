package com.wave.controller;

import com.wave.cache.ContractTradeInfo;
import com.wave.cache.FuturesExchange;
import com.wave.cache.ProductFuture;
import com.wave.cache.TradeCache;
import com.wave.model.ContractItem;
import com.wave.viewmodel.SimpleContract;
import com.wave.viewmodel.SimpleTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Json on 2016/12/19.
 */
@RequestMapping(value = "/contract")
@RestController
public class ContractController {
    @Autowired
    private TradeCache tradeCache;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/item")
    public ContractItem getContract(){
        return tradeCache.getContract(request.getParameter("name"));
    }

    @RequestMapping(value = "/trade_refresh")
    public List<SimpleTrade> getTrade(){
        List<SimpleTrade> result=new ArrayList<>();
        HashMap<String,ContractItem> item_maps=tradeCache.getItem_Map();
        try {
            float delta= (float) (Math.round((Math.random()*10-5)*100))/100;
            String param=request.getParameter("names");
            String[] list=param.split(",");
            for(String abbr:list){
                ContractItem item=item_maps.get(abbr.toLowerCase());
                if(item!=null){
                    ContractTradeInfo info=item.getInfo();
                    SimpleTrade trade=new SimpleTrade(item.getTicker().toUpperCase(),info.getAsk()+delta,info.getBid()+delta,(float) item.getTradeCommiNum(),
                            info.getHighest_price(),info.getLowest_price(),info.getOpening_price(),info.getYesterday_closing_price(),
                            info.getOpen_Interest(),info.getVolume());
                    result.add(trade);
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/contract_basic_list")
    public List<SimpleContract> getBasicList(){
        Collection<ContractItem> items=tradeCache.getItem_Map().values();
        List<SimpleContract> result=new ArrayList<>();
        for(ContractItem item:items){
            ContractTradeInfo info=item.getInfo();
            String color;
            if(info.getYesterday_settlement_price()<info.getLatest_price()) color="red";
            else color="green";
            SimpleContract simple_contract=new SimpleContract(item.getTicker().toUpperCase(),item.getTradeCommiNum(), info.getBid(),info.getAsk(),color);
            result.add(simple_contract);
        }
        return result;
    }


}
