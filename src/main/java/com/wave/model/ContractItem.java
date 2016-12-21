package com.wave.model;

import com.wave.cache.ContractTradeInfo;

import javax.persistence.*;
import javax.swing.text.StringContent;
import java.util.Date;

/**
 * Created by Json on 2016/12/2.
 */
@Entity
public class ContractItem implements Cloneable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String secID;              //合约内部编码
    private String listDate;             //上市时间
    private String secFullName;        //合约全称
    private String secShortName;       //合约简称
    private String ticker;             //合约代码
    private String exchangeCD;         //交易所简称
    private String contractType;       //合约类型
    private String contractObject;     //合约标的
    private String priceUnit;          //报价单位
    private double minChgPriceNum;     //最小变动价位(数值)
    private String minChgPriceUnit;    //最小变动价位(单位)
    private int priceValidDecimal;     //行情有效小数位
    private double limitUpNum;         //涨停板幅度(数值)
    private String limitUpUnit;        //涨停板幅度(单位)
    private double limitDownNum;       //跌停板幅度(数值)
    private String limitDownUnit;      //跌停板幅度(单位)
    private String transCurrCD;        //交易货币
    private double contMultNum;        //合约乘数(数值)
    private String contMultUnit;       //合约乘数(单位)
    private double tradeMarginRatio;   //交易保证金(%)
    private int deliYear;              //交割年
    private int deliMonth;             //交割月
    private String lastTradeDate;        //最后交易日
    private String firstDeliDate;        //开始交割日
    private String lastDeliDate;         //最后交割日
    private String deliMethod;         //交割方式
    @Lob
    private String deliGrade;          //交割品级
    private double tradeCommiNum;      //交易手续费(数值)
    private String tradeCommiUnit;     //交易手续费(单位)
    private double deliCommiNum;       //交割手续费(数值)
    private String deliCommiUnit;      //交割手续费(单位)
    private double listBasisPrice;     //挂牌基准价
    private String settPriceMethod;    //当日结算价计算方式
    private String deliPriceMethod;    //交割结算价计算方式
    private String contractStatus;     //合约状态

    @Transient
    private ContractTradeInfo info;

    public ContractTradeInfo getInfo() {
        return info;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setInfo(ContractTradeInfo info) {
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSecID() {
        return secID;
    }

    public void setSecID(String secID) {
        this.secID = secID;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getSecFullName() {
        return secFullName;
    }

    public void setSecFullName(String secFullName) {
        this.secFullName = secFullName;
    }

    public String getSecShortName() {
        return secShortName;
    }

    public void setSecShortName(String secShortName) {
        this.secShortName = secShortName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getExchangeCD() {
        return exchangeCD;
    }

    public void setExchangeCD(String exchangeCD) {
        this.exchangeCD = exchangeCD;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractObject() {
        return contractObject;
    }

    public void setContractObject(String contractObject) {
        this.contractObject = contractObject;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public double getMinChgPriceNum() {
        return minChgPriceNum;
    }

    public void setMinChgPriceNum(double minChgPriceNum) {
        this.minChgPriceNum = minChgPriceNum;
    }

    public String getMinChgPriceUnit() {
        return minChgPriceUnit;
    }

    public void setMinChgPriceUnit(String minChgPriceUnit) {
        this.minChgPriceUnit = minChgPriceUnit;
    }

    public int getPriceValidDecimal() {
        return priceValidDecimal;
    }

    public void setPriceValidDecimal(int priceValidDecimal) {
        this.priceValidDecimal = priceValidDecimal;
    }

    public double getLimitUpNum() {
        return limitUpNum;
    }

    public void setLimitUpNum(double limitUpNum) {
        this.limitUpNum = limitUpNum;
    }

    public String getLimitUpUnit() {
        return limitUpUnit;
    }

    public void setLimitUpUnit(String limitUpUnit) {
        this.limitUpUnit = limitUpUnit;
    }

    public double getLimitDownNum() {
        return limitDownNum;
    }

    public void setLimitDownNum(double limitDownNum) {
        this.limitDownNum = limitDownNum;
    }

    public String getLimitDownUnit() {
        return limitDownUnit;
    }

    public void setLimitDownUnit(String limitDownUnit) {
        this.limitDownUnit = limitDownUnit;
    }

    public String getTransCurrCD() {
        return transCurrCD;
    }

    public void setTransCurrCD(String transCurrCD) {
        this.transCurrCD = transCurrCD;
    }

    public double getContMultNum() {
        return contMultNum;
    }

    public void setContMultNum(double contMultNum) {
        this.contMultNum = contMultNum;
    }

    public String getContMultUnit() {
        return contMultUnit;
    }

    public void setContMultUnit(String contMultUnit) {
        this.contMultUnit = contMultUnit;
    }

    public double getTradeMarginRatio() {
        return tradeMarginRatio;
    }

    public void setTradeMarginRatio(double tradeMarginRatio) {
        this.tradeMarginRatio = tradeMarginRatio;
    }

    public int getDeliYear() {
        return deliYear;
    }

    public void setDeliYear(int deliYear) {
        this.deliYear = deliYear;
    }

    public int getDeliMonth() {
        return deliMonth;
    }

    public void setDeliMonth(int deliMonth) {
        this.deliMonth = deliMonth;
    }

    public String getLastTradeDate() {
        return lastTradeDate;
    }

    public void setLastTradeDate(String lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }

    public String getFirstDeliDate() {
        return firstDeliDate;
    }

    public void setFirstDeliDate(String firstDeliDate) {
        this.firstDeliDate = firstDeliDate;
    }

    public String getLastDeliDate() {
        return lastDeliDate;
    }

    public void setLastDeliDate(String lastDeliDate) {
        this.lastDeliDate = lastDeliDate;
    }

    public String getDeliMethod() {
        return deliMethod;
    }

    public void setDeliMethod(String deliMethod) {
        this.deliMethod = deliMethod;
    }

    public String getDeliGrade() {
        return deliGrade;
    }

    public void setDeliGrade(String deliGrade) {
        this.deliGrade = deliGrade;
    }

    public double getTradeCommiNum() {
        return tradeCommiNum;
    }

    public void setTradeCommiNum(double tradeCommiNum) {
        this.tradeCommiNum = tradeCommiNum;
    }

    public String getTradeCommiUnit() {
        return tradeCommiUnit;
    }

    public void setTradeCommiUnit(String tradeCommiUnit) {
        this.tradeCommiUnit = tradeCommiUnit;
    }

    public double getDeliCommiNum() {
        return deliCommiNum;
    }

    public void setDeliCommiNum(double deliCommiNum) {
        this.deliCommiNum = deliCommiNum;
    }

    public String getDeliCommiUnit() {
        return deliCommiUnit;
    }

    public void setDeliCommiUnit(String deliCommiUnit) {
        this.deliCommiUnit = deliCommiUnit;
    }

    public double getListBasisPrice() {
        return listBasisPrice;
    }

    public void setListBasisPrice(double listBasisPrice) {
        this.listBasisPrice = listBasisPrice;
    }

    public String getSettPriceMethod() {
        return settPriceMethod;
    }

    public void setSettPriceMethod(String settPriceMethod) {
        this.settPriceMethod = settPriceMethod;
    }

    public String getDeliPriceMethod() {
        return deliPriceMethod;
    }

    public void setDeliPriceMethod(String deliPriceMethod) {
        this.deliPriceMethod = deliPriceMethod;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }
}
