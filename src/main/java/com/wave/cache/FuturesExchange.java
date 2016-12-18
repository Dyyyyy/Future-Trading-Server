package com.wave.cache;


import com.wave.repository.CacheRepository.ProductFutureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Json on 2016/12/7.
 */
@Entity
public class FuturesExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String exchange_name;

    @Column(length = 10)
    private String abbreviation;



    @Transient
    @Autowired
    private ProductFutureRepository repository;

    @Transient
    private HashMap<String,ProductFuture> futures;

    public String getExchange_name() {
        return exchange_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void init(){
        List<ProductFuture> future_list=repository.findByExchange(id);
        for(ProductFuture future:future_list){
            future.init();
            futures.put(future.getProduct_name(),future);
        }
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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
