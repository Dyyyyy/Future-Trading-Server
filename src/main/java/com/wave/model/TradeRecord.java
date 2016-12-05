package com.wave.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Json on 2016/12/2.
 */
@Entity
public class TradeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User user;

    @ManyToOne(targetEntity = ContractItem.class)
    @JoinColumn(name = "contract_item_id", foreignKey = @ForeignKey(name = "CONTRACT_ITEM_ID_FK"))
    private ContractItem contract_item;

    private int trading_type;
    private int open_offset;
    private float amount;
    private float price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContractItem getContract_item() {
        return contract_item;
    }

    public void setContract_item(ContractItem contract_item) {
        this.contract_item = contract_item;
    }

    public int getTrading_type() {
        return trading_type;
    }

    public void setTrading_type(int trading_type) {
        this.trading_type = trading_type;
    }

    public int getOpen_offset() {
        return open_offset;
    }

    public void setOpen_offset(int open_offset) {
        this.open_offset = open_offset;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
