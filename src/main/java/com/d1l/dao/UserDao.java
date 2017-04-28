package com.d1l.dao;

import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.User;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDao {

    public static void addOrUpdateUser(User user) {
        DAOUtil.addOrUpdateEntity(user);
    }

    public static void deleteUser(int id) {
        DAOUtil.deleteEntity(User.class, id);
    }

    public static User getUserById(int id) {
        return DAOUtil.getEntityBy(User.class, PropertyName.ID, id);
    }

    public static User getUserByLogin(String login){
        Session session = HibernateUtil.makeSession();
        session.beginTransaction();
        User user = null;
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.ilike("login", login));
            user = (User)criteria.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    public static List<User> getUsersList() {
        return DAOUtil.getEntityList(User.class);
    }
}
