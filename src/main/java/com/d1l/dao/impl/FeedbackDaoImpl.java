package com.d1l.dao.impl;

import com.d1l.dao.FeedbackDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.*;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDaoImpl implements FeedbackDao {

    public void addOrUpdateFeedback(Feedback feedback) {
        DAOUtil.addOrUpdateEntity(feedback);
    }

    public void deleteFeedback(int id) {
        DAOUtil.deleteEntity(Feedback.class, id);
    }

    public Feedback getFeedbackById(int id) {
        return DAOUtil.getEntityBy(Feedback.class, PropertyName.ID, id);
    }

    public List<Feedback> getFeedbackListByCustomer(Customer customer) {
        Resume resume = DaoFactory.getInstance().getResumeDao().getResumeByCustomer(customer);
        return DAOUtil.getEntityListBy(Feedback.class, PropertyName.RESUME, resume);
    }

    public Feedback getFeedbackByResumeAndVacancy(Resume resume, Vacancy vacancy) {
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

    public List<Feedback> getFeedbackListByCompany(Company company) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        ArrayList<Feedback> feedbacks = null;
        try {
            List<Vacancy> vacancys = DaoFactory.getInstance().getVacancyDao().getVacancyByCompany(company);
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
    public Feedback getFeedbackByCustomerAndLastDate(Customer customer) {
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

    public List<Feedback> getFeedbacksList() {
        return DAOUtil.getEntityList(Feedback.class);
    }


}
