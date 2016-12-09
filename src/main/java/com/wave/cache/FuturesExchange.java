package com.wave.cache;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Json on 2016/12/7.
 */
public class FuturesExchange {
    private String exchange_name;
    private HashMap<String,ProductFuture> futures;
    FuturesExchange(HashMap<String,ProductFuture> f){
        futures=f;
    }
    public String getExchange_name() {
        return exchange_name;
    }

    public void setExchange_name(String exchange_name) {
        this.exchange_name = exchange_name;
    }

    public Collection<ProductFuture> getFutures(){
        return futures.values();
    }

    public Set<String> getAbbrList(){
       return futures.keySet();
    }
}
