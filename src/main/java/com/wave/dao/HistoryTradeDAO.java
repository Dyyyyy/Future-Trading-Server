package com.wave.dao;

import com.wave.model.HistoryTrade;
import org.hibernate.SessionFactory;

/**
 * Created by Json on 2016/12/2.
 */
public class HistoryTradeDAO extends DAO<HistoryTrade>{
    public HistoryTradeDAO(){
        entity_name="HistoryTrade";
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
