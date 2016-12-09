package com.wave.cache;

import com.wave.model.ContractItem;

import java.util.ArrayList;

/**
 * Created by Json on 2016/12/7.
 */
public class ProductFuture {
    /**
     * this array consist of one product future.e.g.: M, CF, AG etc.
     */
    private String product_name;
    private String abbreviation;
    private ContractItem[] items = new ContractItem[13];

    public ProductFuture(String p_n,String abbr){
        product_name=p_n;
        abbreviation=abbr;
    }

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

    public void refreshContracts(String[] strings){
        for(int i=0;i<13;i++){
            items[i].getInfo().refresh(strings[i]);
        }
    }
}
