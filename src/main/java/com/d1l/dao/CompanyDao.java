package com.d1l.dao;

import com.d1l.model.Company;
import com.d1l.model.User;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CompanyDao {

    public static void addOrUpdateCompany(Company company) {
        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(company);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteCompany(int id) {

        Session session = HibernateUtil.makeSession();
        try {
            session.beginTransaction();
            Company company = session.get(Company.class, id);

            if (company != null) {
                session.delete(company);
            }
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    public static Company getCompanyById(int id) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Company company = null;
        try {
            Criteria criteria = session.createCriteria(Company.class);
            criteria.add(Restrictions.eq("id", id));
            company = (Company)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return company;
    }

    public static Company getCompanyByUser(User user) {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        Company company = null;
        try {
            Criteria criteria = session.createCriteria(Company.class);
            criteria.add(Restrictions.eq("user", user));
            List list = criteria.list();
            company = (Company)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return company;
    }

    public static List<Company> getCompanyList() {
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        List<Company> suppliersList = null;
        try {
            suppliersList = (List<Company>)session.createCriteria(Company.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return suppliersList;
    }
}
