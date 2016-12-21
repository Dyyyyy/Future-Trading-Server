package com.wave.cache;
import com.wave.model.ContractItem;
import com.wave.model.HistoryTrade;
import com.wave.repository.EntityRepository.HistoryTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;

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
    private float settlement_price;            //结算价
    private float yesterday_settlement_price;  //昨结算
    private int open_Interest;               //持仓量
    private int volume;                      //成交量

    private ContractItem contract_item;
    private Date date;                       //日期

    private boolean isinit;

    public ContractTradeInfo(String n, String abbr, ContractItem item){
        name=n;
        abbreviation=abbr;
        isinit=false;
    }

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
        return settlement_price;
    }

    public void setSettlement_Price(float settlement_Price) {
        this.settlement_price = settlement_Price;
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

    public void refresh(String str,Date current_time, List<HistoryTrade> list){
        if(str==null||current_time==null) return;
        String[] data = str.split(",");
        if(data.length<14) return;
        if(isinit) {
            HistoryTrade history_trade = new HistoryTrade();
            history_trade.setContract_item(contract_item);
            history_trade.setOpening_price(opening_price);
            history_trade.setHighest_price(highest_price);
            history_trade.setLowest_price(lowest_price);
            history_trade.setSettlement_price(settlement_price);
            history_trade.setDate(date);
            if (data.length >= 18) {
                history_trade.setVolume(Integer.parseInt(data[14]) - volume);
            }
            list.add(history_trade);
        }
        else{
            isinit=true;
        }
        opening_price=Float.parseFloat(data[2]);
        highest_price=Float.parseFloat(data[3]);
        lowest_price=Float.parseFloat(data[4]);
        yesterday_closing_price=Float.parseFloat(data[5]);
        Bid=Float.parseFloat(data[6]);
        Ask=Float.parseFloat(data[7]);
        latest_price=Float.parseFloat(data[8]);
        settlement_price=Float.parseFloat(data[9]);
        yesterday_settlement_price=Float.parseFloat(data[10]);
        open_Interest=Integer.parseInt(data[13]);
        volume=Integer.parseInt(data[14]);
        date=current_time;
    }
}
