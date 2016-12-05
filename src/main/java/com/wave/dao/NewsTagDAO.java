package com.wave.dao;

import com.wave.model.NewsTag;
import org.hibernate.SessionFactory;

/**
 * Created by Json on 2016/11/18.
 */
public class NewsTagDAO extends DAO<NewsTag>{
    public NewsTagDAO(){
        entity_name="NewsTag";
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
