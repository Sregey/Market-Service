package com.d1l.dao;

import com.d1l.model.FeedbackVacancy;
import com.d1l.model.OrderVacancy;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class FeedbackVacancyDao {

    public static void addOrUpdateFeedbackVacancy(FeedbackVacancy feedbackVacancy) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(feedbackVacancy);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteFeedbackVacancy(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            FeedbackVacancy feedbackVacancy = session.get(FeedbackVacancy.class, id);

            if (feedbackVacancy != null) {
                session.delete(feedbackVacancy);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static List<FeedbackVacancy> getFeedbackVacancysList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<FeedbackVacancy> list = null;
        try {
            list = (List<FeedbackVacancy>)session.createCriteria(FeedbackVacancy.class).list();
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
