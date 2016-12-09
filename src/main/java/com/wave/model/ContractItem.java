package com.wave.model;

import com.wave.cache.ContractTradeInfo;

import javax.persistence.*;
import javax.swing.text.StringContent;

/**
 * Created by Json on 2016/12/2.
 */
@Entity
public class ContractItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = TradingTime.class)
    @JoinColumn(name = "trading_time_id", foreignKey = @ForeignKey(name = "TRADING_TIME_ID_FK"))
    private TradingTime trading_time;

    private String name;            //名称
    private float unit_of_trading;
    private float minimum_price;
    private float limit_up_down;
    private int delivery_month;
    private int last_trading_day;
    private int lld_type;
    private int last_delivery_day;

    @Transient
    private ContractTradeInfo info;

    @Column(length = 50)
    private String delivery_grade;

    private float margin;

    @Column(length = 20)
    private String poundage_type;

    @Column(length = 20)
    private String poundage_value;

    @Column(length = 20)
    private String delivery_type;

    @Column(length = 10)
    private String trading_code;

    @Column(length = 20)
    private String delivery_point;

    public TradingTime getTrading_time() {
        return trading_time;
    }

    public void setTrading_time(TradingTime trading_time) {
        this.trading_time = trading_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUnit_of_trading() {
        return unit_of_trading;
    }

    public void setUnit_of_trading(float unit_of_trading) {
        this.unit_of_trading = unit_of_trading;
    }

    public float getMinimum_price() {
        return minimum_price;
    }

    public void setMinimum_price(float minimum_price) {
        this.minimum_price = minimum_price;
    }

    public float getLimit_up_down() {
        return limit_up_down;
    }

    public void setLimit_up_down(float limit_up_down) {
        this.limit_up_down = limit_up_down;
    }

    public int getDelivery_month() {
        return delivery_month;
    }

    public void setDelivery_month(int delivery_month) {
        this.delivery_month = delivery_month;
    }

    public int getLast_trading_day() {
        return last_trading_day;
    }

    public void setLast_trading_day(int last_trading_day) {
        this.last_trading_day = last_trading_day;
    }

    public int getLld_type() {
        return lld_type;
    }

    public void setLld_type(int lld_type) {
        this.lld_type = lld_type;
    }

    public int getLast_delivery_day() {
        return last_delivery_day;
    }

    public void setLast_delivery_day(int last_delivery_day) {
        this.last_delivery_day = last_delivery_day;
    }

    public String getDelivery_grade() {
        return delivery_grade;
    }

    public void setDelivery_grade(String delivery_grade) {
        this.delivery_grade = delivery_grade;
    }

    public float getMargin() {
        return margin;
    }

    public void setMargin(float margin) {
        this.margin = margin;
    }

    public String getPoundage_type() {
        return poundage_type;
    }

    public void setPoundage_type(String poundage_type) {
        this.poundage_type = poundage_type;
    }

    public String getPoundage_value() {
        return poundage_value;
    }

    public void setPoundage_value(String poundage_value) {
        this.poundage_value = poundage_value;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getTrading_code() {
        return trading_code;
    }

    public void setTrading_code(String trading_code) {
        this.trading_code = trading_code;
    }

    public String getDelivery_point() {
        return delivery_point;
    }

    public void setDelivery_point(String delivery_point) {
        this.delivery_point = delivery_point;
    }

    public int getId() {
        return id;
    }

    public ContractTradeInfo getInfo() {
        return info;
    }
}
