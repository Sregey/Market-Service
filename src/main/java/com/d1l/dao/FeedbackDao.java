package com.d1l.dao;

import com.d1l.model.*;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDao {

    public static void addOrUpdateFeedback(Feedback feedback) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(feedback);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteFeedback(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Feedback feedback = session.get(Feedback.class, id);

            if (feedback != null) {
                session.delete(feedback);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Feedback getFeedbackById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Feedback feedback = null;
        try {
            Criteria criteria = session.createCriteria(Feedback.class);
            criteria.add(Restrictions.eq("id", id));
            feedback = (Feedback)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return feedback;
    }

    public static List<Feedback> getFeedbackListByCustomer(Customer customer) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Feedback> feedbacks = null;
        try {
            Criteria criteria = session.createCriteria(Feedback.class);
            Resume resume = ResumeDao.getResumeByCustomer(customer);
            criteria.add(Restrictions.eq("resume", resume));
            feedbacks = (List<Feedback>)criteria.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return feedbacks;
    }

    public static Feedback getFeedbackByResumeAndVacancy(Resume resume, Vacancy vacancy) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Feedback feedback = null;
        try {
            Criteria criteria = session.createCriteria(Feedback.class);
            criteria.add(Restrictions.eq("resume", resume));
            criteria.add(Restrictions.eq("vacancy", vacancy));
            feedback = (Feedback) criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return feedback;
    }

    public static List<Feedback> getFeedbackListByCompany(Company company) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        ArrayList<Feedback> feedbacks = null;
        try {
            List<Vacancy> vacancys = VacancyDao.getVacancyByCompany(company);
            for (Vacancy vacancy : vacancys) {
                Criteria criteria = session.createCriteria(Feedback.class);
                criteria.add(Restrictions.eq("vacancy", vacancy));
                List<Feedback> list = (List<Feedback>)criteria.list();
                if(list != null) {
                    if(feedbacks == null){
                        feedbacks = new ArrayList<Feedback>();
                    }
                    feedbacks.addAll(list);
                }
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return feedbacks;
    }

    //todo CHECK
    public static Feedback getFeedbackByCustomerAndLastDate(Customer customer) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Feedback> feedbacks = null;
        try {
            Criteria criteria = session.createCriteria(Feedback.class)
                    .addOrder(org.hibernate.criterion.Order.desc("date") );
            criteria.add(Restrictions.eq("customer", customer));
            criteria.setFirstResult(0);
            criteria.setMaxResults(1);
            feedbacks = (List<Feedback>)criteria.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return feedbacks.get(0);
    }

    public static List<Feedback> getFeedbacksList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Feedback> ordersList = null;
        try {
            ordersList = (List<Feedback>)session.createCriteria(Feedback.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return ordersList;
    }


}
