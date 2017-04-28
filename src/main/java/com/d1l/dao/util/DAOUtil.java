package com.d1l.dao.util;

import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public final class DAOUtil {
    private DAOUtil(){}

    public static void addOrUpdateEntity(Object o) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(o);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static <T> void deleteEntity(Class<T> c, int id) {
        Session session = HibernateUtil.makeSession();
        try {
            T o = session.get(c, id);
            if (o != null) {
                session.beginTransaction();
                session.delete(o);
                session.getTransaction().commit();
            }
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static <T> T getEntityBy(Class<T> c, String propertyName, Object o){
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        T entity = null;
        try {
            Criteria criteria = session.createCriteria(c);
            criteria.add(Restrictions.eq(propertyName, o));
            entity = (T)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    public static <T> List<T> getEntityList(Class<T> c){
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<T> list = null;
        try {
            list = (List<T>)session.createCriteria(c).list();
            session.getTransaction().commit();
        } catch (HibernateException e){
            // TODO: Переадресовать на страницу с ошибкой вместо распечатки стектрейса
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return list;
    }

    public static <T> List<T> getEntityListBy(Class<T> c, String propertyName, Object o) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<T> list = null;
        try {
            Criteria criteria = session.createCriteria(c);
            criteria.add(Restrictions.eq(propertyName, o));
            list = (List<T>)criteria.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return list;
    }
}
