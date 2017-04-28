package com.d1l.dao;

import com.d1l.model.Market;
import com.d1l.model.Status;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StatusDao {

    public static void addOrUpdateStatus(Status status) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(status);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteStatus(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Status status = session.get(Status.class, id);

            if (status != null) {
                session.delete(status);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Status getStatusById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Status status = null;
        try {
            Criteria criteria = session.createCriteria(Status.class);
            criteria.add(Restrictions.eq("id", id));
            status = (Status)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return status;
    }

    private static Status getStatusByName(String name){
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Status status = null;
        try {
            Criteria criteria = session.createCriteria(Status.class);
            criteria.add(Restrictions.eq("name", name));
            status = (Status)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return status;
    }

    public static Status getApprovedStatus() {
        return getStatusByName("Approved");
    }

    public static Status getRejectedStatus() {
        return getStatusByName("Rejected");
    }

    public static Status getDefaultStatus() {
        return getStatusByName("Default");
    }
}
