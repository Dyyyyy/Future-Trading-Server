package com.wave.cache;

import com.wave.staticsetting.StaticConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Json on 2016/12/20.
 */
public class ContractKLine {
    private String ticker;
    private Date lastest_refresh_time;
    private Date second_refresh_time;
    private boolean isinit;

    private KLineItem[] k_line_items=new KLineItem[200];
    private int head;

    public ContractKLine(String t){
        ticker=t;
        head=0;
        isinit=false;
        lastest_refresh_time=new Date();
        second_refresh_time=new Date();
    }

    @Scheduled(cron = "5 0/5 9-11,14-15,21-23 ? * MON-FRI")
    public void updateDataSlotOne() {
        refreshKLine();
    }

    @Scheduled(cron = "5 0,5,10,15,20,25,30 11,23 ? * MON-FRI")
    public void updateDataSlotTwo() {
        refreshKLine();
    }

    @Scheduled(cron = "5 30/5 13 ? * MON-FRI")
    public void updateDataSlotThree() {
        refreshKLine();
    }
    public void refreshKLine(){
        try {
            String url = StaticConfig.KLINE_5MIN_URL + ticker;
            HttpGet get = new HttpGet(url);
            CloseableHttpClient client = StaticConfig.createHttpsClient();
            CloseableHttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity);
            JSONArray array = JSONArray.fromObject(body);
            if(!isinit) {
                for (int i = 0; i < array.size(); i++) {
                    if (i < 200) {
                        k_line_items[i] = (KLineItem) JSONObject.toBean(array.getJSONObject(i), KLineItem.class);
                    }
                    else break;
                }
                head=200-1;
            }
            else {
                k_line_items[(head++)%200]=(KLineItem) JSONObject.toBean(array.getJSONObject(0),KLineItem.class);
                second_refresh_time=lastest_refresh_time;
                lastest_refresh_time=new Date();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<KLineItem> getAllItem(){
        List<KLineItem> result=new ArrayList<>();
        int count=200;
        int pos=head;
        while(count>0){
            result.add(k_line_items[pos]);
            pos=(pos-1+200)%200;
            count--;
        }
        return result;
    }

    public List<KLineItem> getItemByTime(String time){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<KLineItem> result=new ArrayList<>();
        try {
            Date t = format.parse(time);
            if(t.before(second_refresh_time)){
                result.add(k_line_items[(head-1+200)%200]);
                result.add(k_line_items[head]);
            }
            else if(t.before(lastest_refresh_time)){
                result.add(k_line_items[head]);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}