package com.wave.viewmodel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Json on 2016/12/21.
 */
public class KLineData {
    String x_data;
    List<Float> trade_data;

    public KLineData(Date time, float open_price, float closed_price, float lowest_price, float highest_price){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        x_data=sdf.format(time);
        trade_data.add(open_price);
        trade_data.add(closed_price);
        trade_data.add(lowest_price);
        trade_data.add(highest_price);
    }

    public String getX_data() {
        return x_data;
    }

    public List<Float> getTrade_data() {
        return trade_data;
    }
}
