package com.wave.dao;

import com.wave.model.UserTag;
import org.hibernate.SessionFactory;

/**
 * Created by Json on 2016/12/2.
 */
public class UserTagDAO extends DAO<UserTag>{
    public UserTagDAO(){
        entity_name="UserTag";
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
