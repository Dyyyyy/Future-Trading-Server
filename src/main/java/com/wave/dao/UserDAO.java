package com.wave.dao;

import com.wave.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Json on 2016/11/14.
 */
public class UserDAO extends DAO<User> {
    public UserDAO() {
        entity_name = "User";
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User getByPhone_Number(String phone_number) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery("select user " +
                "from User user " +
                "where user.phone_number = :phone_number").
                setParameter("phone_number", phone_number).list();
        session.getTransaction().commit();
        session.close();
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    public User getByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery(
                "select user " +
                "from User user " +
                "where user.email = :email")
                .setParameter("email", email).list();
        session.getTransaction().commit();
        session.close();
        if (result != null && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

}

