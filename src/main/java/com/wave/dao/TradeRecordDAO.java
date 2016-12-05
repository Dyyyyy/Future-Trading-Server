package com.wave.dao;

import com.wave.model.TradeRecord;
import org.hibernate.SessionFactory;

/**
 * Created by Json on 2016/12/2.
 */
public class TradeRecordDAO extends DAO<TradeRecord>{
    public TradeRecordDAO(){
        entity_name="TradeRecord";
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
