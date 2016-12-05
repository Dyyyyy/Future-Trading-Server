package com.wave.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Json on 2016/11/14.
 */
public class DAO<T> {
    protected SessionFactory sessionFactory;
    protected String entity_name;

    public T getById(int id){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        T result=(T)session.get(entity_name, id);
        session.getTransaction().commit();
        session.close();
        return null;
    }
    public List<T> getList(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        List<T> result=session.createQuery("from " + entity_name).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void save(T elem){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity_name, elem);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(T elem){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity_name, elem);
        session.getTransaction().commit();
        session.close();
    }
    public void update(T elem){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity_name, elem);
        session.getTransaction().commit();
        session.close();
    }
}
