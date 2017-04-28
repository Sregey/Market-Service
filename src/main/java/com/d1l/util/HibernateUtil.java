package com.d1l.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateUtil {

    @Autowired
    private final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try {
            Configuration configuration = new Configuration();
            return  configuration.configure().buildSessionFactory();
        } catch (ExceptionInInitializerError e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw e;
        }
        catch (Throwable err){
            err.printStackTrace();
            return null;
        }
    }

    public static Session makeSession() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (Exception e) {
            session = sessionFactory.openSession();
        }
        return session;
    }

}
