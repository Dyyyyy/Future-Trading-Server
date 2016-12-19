package com.wave.cache;

import com.wave.model.ContractItem;
import com.wave.repository.EntityRepository.ContractItemRepository;
import com.wave.repository.EntityRepository.HistoryTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    /**
     * this array consist of one product future.e.g.: M, CF, AG etc.
     */
    private String product_name;
    private String abbreviation;

    @Transient
    private HistoryTradeRepository h_repository;

    @Transient
    private ContractItem[] items = new ContractItem[13];

    public void init(ContractItemRepository c_repository, HistoryTradeRepository h){
        h_repository=h;
        List<ContractItem> items_list=c_repository.findByContractObject(abbreviation);
        try {
            int avail_pos=-1;
            for (ContractItem item : items_list) {
                int item_month = item.getDeliMonth();
                String item_last_trade_date = item.getLastTradeDate();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                Date date = format.parse(item_last_trade_date);
                Date now_date=new Date();
                if (item_month==0||date.after(now_date)){
                    avail_pos=item_month;
                    items[item_month]=item;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FuturesExchange getFutures_exchange() {
        return futures_exchange;
    }

    public void setFutures_exchange(FuturesExchange futures_exchange) {
        this.futures_exchange = futures_exchange;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public ContractItem[] getItems() {
        return items;
    }

    public void setItems(ContractItem[] items) {
        this.items = items;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public ContractItem getContract(int month) {
        return items[month];
    }

    public void updateContact(ContractItem item, int current_month) {
        items[current_month] = item;
    }

    public void refreshContracts(String[] strings, Date current_time){
        for(int i=0;i<13;i++){
            if(items[i]!=null) {
                if(items[i].getInfo()==null) {
                    ContractTradeInfo info = new ContractTradeInfo(items[i].getSecShortName(),items[i].getTicker(),items[i],h_repository);
                    items[i].setInfo(info);
                }
                items[i].getInfo().refresh(strings[i], current_time);
            }
        }
    }
}
