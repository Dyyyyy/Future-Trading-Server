package com.wave.cache;

import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Json on 2016/11/13.
 */
public class ContractTradeInfo {
    private String name;                     //名字
    private String abbreviation;             //简称
    private float opening_price;               //开盘价
    private float highest_price;               //最高价
    private float lowest_price;                //最低价
    private float yesterday_closing_price;     //昨日收盘价
    private float Bid;                         //买价
    private float Ask;                         //卖价
    private float latest_price;                //最新价
    private float settlement_Price;            //结算价
    private float yesterday_settlement_price;  //昨结算
    private int open_Interest;               //持仓量
    private int volume;                      //成交量

    private Date date;                       //日期
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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

    public float getYesterday_closing_price() {
        return yesterday_closing_price;
    }

    public void setYesterday_closing_price(float yesterday_closing_price) {
        this.yesterday_closing_price = yesterday_closing_price;
    }

    public float getBid() {
        return Bid;
    }

    public void setBid(float bid) {
        Bid = bid;
    }

    public float getAsk() {
        return Ask;
    }

    public void setAsk(float ask) {
        Ask = ask;
    }

    public float getLatest_price() {
        return latest_price;
    }

    public void setLatest_price(float latest_price) {
        this.latest_price = latest_price;
    }

    public float getSettlement_Price() {
        return settlement_Price;
    }

    public void setSettlement_Price(float settlement_Price) {
        this.settlement_Price = settlement_Price;
    }

    public float getYesterday_settlement_price() {
        return yesterday_settlement_price;
    }

    public void setYesterday_settlement_price(float yesterday_settlement_price) {
        this.yesterday_settlement_price = yesterday_settlement_price;
    }

    public int getOpen_Interest() {
        return open_Interest;
    }

    public void setOpen_Interest(int open_Interest) {
        this.open_Interest = open_Interest;
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

    public void refresh(String str){
        //
    }
}
