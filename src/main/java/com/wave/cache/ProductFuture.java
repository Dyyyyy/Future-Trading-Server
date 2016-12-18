package com.wave.cache;

import com.wave.model.ContractItem;
import com.wave.repository.EntityRepository.ContractItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Json on 2016/12/7.
 */
@Entity
public class ProductFuture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = FuturesExchange.class)
    @JoinColumn(name = "futures_exchange_id", foreignKey = @ForeignKey(name = "EXCHANGE_ID_FK"))
    private FuturesExchange futures_exchange;

    @Transient
    @Autowired
    private ContractItemRepository repository;
    /**
     * this array consist of one product future.e.g.: M, CF, AG etc.
     */
    private String product_name;
    private String abbreviation;

    @Transient
    private ContractItem[] items = new ContractItem[13];

    public void init(){

    };

    public String getProduct_name() {
        return product_name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public ContractItem[] getContracts() {
        return items;
    }

    public ContractItem getContract(int month) {
        return items[month];
    }

    public void updateContact(ContractItem item, int current_month) {
        items[current_month] = item;
    }

    public void refreshContracts(String[] strings, Date current_time){
        for(int i=0;i<13;i++){
            items[i].getInfo().refresh(strings[i], current_time);
        }
    }
}
