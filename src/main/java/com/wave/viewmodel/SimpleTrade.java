package com.wave.viewmodel;

/**
 * Created by Json on 2016/12/20.
 */
public class SimpleTrade {
    private String name;
    private float sell;
    private float buy;
    private float point;
    private float highest;
    private float lowest;
    private float open;
    private float closed;
    private int open_Interest;               //持仓量
    private int volume;                      //成交量

    public SimpleTrade(String ticker,float ask,float bid, float p,float h,float l,float o, float c,int o_i, int v){
        name=ticker;
        sell=ask;
        buy=bid;
        point=p;
        highest=h;
        lowest=l;
        open=o;
        closed=c;
        open_Interest=o_i;
        volume=v;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSell(float sell) {
        this.sell = sell;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public void setHighest(float highest) {
        this.highest = highest;
    }

    public void setLowest(float lowest) {
        this.lowest = lowest;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public void setClosed(float closed) {
        this.closed = closed;
    }

    public String getName() {
        return name;
    }

    public float getSell() {
        return sell;
    }

    public float getBuy() {
        return buy;
    }

    public float getPoint() {
        return point;
    }

    public float getHighest() {
        return highest;
    }

    public float getLowest() {
        return lowest;
    }

    public float getOpen() {
        return open;
    }

    public float getClosed() {
        return closed;
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
}
