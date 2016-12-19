package com.wave.cache;


import com.wave.repository.CacheRepository.ProductFutureRepository;
import com.wave.repository.EntityRepository.ContractItemRepository;
import com.wave.repository.EntityRepository.HistoryTradeRepository;

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

    @OneToMany(mappedBy = "futures_exchange", cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = ProductFuture.class)
    private List<ProductFuture> futures_list;


    @Transient
    private HashMap<String,ProductFuture> futures=new HashMap<>();

    public String getExchange_name() {
        return exchange_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void init(ProductFutureRepository p_repository, ContractItemRepository c_repository, HistoryTradeRepository h_repository){
        List<ProductFuture> future_list=p_repository.findByExchange(this);
        for(ProductFuture future:future_list){
            future.init(c_repository,h_repository);
            futures.put(future.getAbbreviation(),future);
        }
    }

    public void setFutures(HashMap<String, ProductFuture> futures) {
        this.futures = futures;
    }

    public List<ProductFuture> getFutures_list() {
        return futures_list;
    }

    public void setFutures_list(List<ProductFuture> futures_list) {
        this.futures_list = futures_list;
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
