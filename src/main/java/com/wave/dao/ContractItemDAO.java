package com.wave.dao;

import com.wave.model.ContractItem;
import org.hibernate.SessionFactory;

/**
 * Created by Json on 2016/11/14.
 */
public class ContractItemDAO extends DAO<ContractItem>{

    public ContractItemDAO(){
        entity_name="ContractItem";
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
