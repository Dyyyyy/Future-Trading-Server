package com.wave.viewmodel;

/**
 * Created by Json on 2016/12/20.
 */
public class SimpleContract {
    private String name;       //合约简称
    private double point;      //交易手续费(数值)
    private float sell;        //买价
    private float buy;         //卖价
    private String color;      //颜色

    public SimpleContract(String sec,double t,float bid, float ask, String c){
        name=sec;
        point=t;
        sell=ask;
        buy=bid;
        color=c;
    }

    public String getName() {
        return name;
    }

    public double getPoint() {
        return point;
    }

    public float getSell() {
        return sell;
    }

    public float getBuy() {
        return buy;
    }

    public String getColor() {
        return color;
    }
}
