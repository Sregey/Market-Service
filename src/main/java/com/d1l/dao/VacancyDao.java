package com.d1l.dao;

import com.d1l.model.Vacancy;
import com.d1l.model.Company;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class VacancyDao {

    public static void addOrUpdateVacancy(Vacancy vacancy) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(vacancy);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteVacancy(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Vacancy vacancy = session.get(Vacancy.class, id);

            if (vacancy != null) {
                session.delete(vacancy);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static List<Vacancy> getVacancyByCompany(Company company) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Vacancy> vacancyList = null;
        try {
            Criteria criteria = session.createCriteria(Vacancy.class);
            criteria.add(Restrictions.eq("company", company));
            vacancyList = (List<Vacancy>)criteria.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return vacancyList;
    }

    public static Vacancy getVacancyById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Vacancy vacancy = null;
        try {
            Criteria criteria = session.createCriteria(Vacancy.class);
            criteria.add(Restrictions.eq("id", id));
            vacancy = (Vacancy)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return vacancy;
    }

    public static List<Vacancy> getVacancyList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Vacancy> itemsList = null;
        try {
            itemsList = (List<Vacancy>)session.createCriteria(Vacancy.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return itemsList;
    }


}
