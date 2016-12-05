package com.wave.dao;

import com.wave.model.TradingTime;
import org.hibernate.SessionFactory;

/**
 * Created by Json on 2016/12/2.
 */
public class TradingTimeDAO extends DAO<TradingTime> {
    public TradingTimeDAO(){
        entity_name="TradingTime";
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
