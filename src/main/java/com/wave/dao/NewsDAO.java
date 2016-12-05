package com.wave.dao;

import com.wave.model.News;
import org.hibernate.SessionFactory;

/**
 * Created by Json on 2016/11/18.
 */
public class NewsDAO extends DAO<News> {
    public NewsDAO(){
        entity_name="News";
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
