package com.d1l.dao;

import com.d1l.model.Customer;
import com.d1l.model.Resume;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class ResumeDao {

    public static void addOrUpdateResume(Resume resume) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(resume);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteResume(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Resume resume = session.get(Resume.class, id);

            if (resume != null) {
                session.delete(resume);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Resume getResumeById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Resume resume = null;
        try {
            Criteria criteria = session.createCriteria(Resume.class);
            criteria.add(Restrictions.eq("id", id));
            resume = (Resume) criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return resume;
    }

    public static Resume getResumeByCustomer(Customer customer) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Resume resume = null;
        try {
            Criteria criteria = session.createCriteria(Resume.class);
            criteria.add(Restrictions.eq("customer", customer));
            resume = (Resume) criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return resume;
    }

    public static List<Resume> getResumeList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Resume> resumeList = null;
        try {
            resumeList = (List<Resume>)session.createCriteria(Resume.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return resumeList;
    }
}
