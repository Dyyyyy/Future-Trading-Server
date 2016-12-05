package com.wave.dao;

import com.wave.model.User;
import org.hibernate.SessionFactory;

/**
 * Created by Json on 2016/11/14.
 */
public class UserDAO extends DAO<User> {
    public UserDAO(){
        entity_name="User";
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

