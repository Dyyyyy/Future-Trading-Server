package com.wave.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Json on 2016/12/5.
 */
@Entity
public class HistoryTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = ContractItem.class)
    @JoinColumn(name = "contract_item_id", foreignKey = @ForeignKey(name = "HISTORYTRADE_CONTRACT_ID_FK"))
    private ContractItem contract_item;
    private float opening_price;
    private float highest_price;
    private float lowest_price;
    private float settlement_price;
    private int volume;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContractItem getContract_item() {
        return contract_item;
    }

    public void setContract_item(ContractItem contract_item) {
        this.contract_item = contract_item;
    }

    public float getOpening_price() {
        return opening_price;
    }

    public void setOpening_price(float opening_price) {
        this.opening_price = opening_price;
    }

    public float getHighest_price() {
        return highest_price;
    }

    public void setHighest_price(float highest_price) {
        this.highest_price = highest_price;
    }

    public float getLowest_price() {
        return lowest_price;
    }

    public void setLowest_price(float lowest_price) {
        this.lowest_price = lowest_price;
    }

    public float getSettlement_price() {
        return settlement_price;
    }

    public void setSettlement_price(float settlement_price) {
        this.settlement_price = settlement_price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
