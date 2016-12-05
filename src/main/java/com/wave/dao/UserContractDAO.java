package com.wave.dao;

import com.wave.model.UserContract;
import org.hibernate.SessionFactory;

/**
 * Created by Json on 2016/12/5.
 */
public class UserContractDAO extends DAO<UserContract>{
    public UserContractDAO(){
        entity_name="UserContract";
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
