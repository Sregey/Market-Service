package com.d1l.dao.impl;

import com.d1l.dao.UserDao;
import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.User;
import com.d1l.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoImpl implements UserDao {

    public void addOrUpdateUser(User user) {
        DAOUtil.addOrUpdateEntity(user);
    }

    public void deleteUser(int id) {
        DAOUtil.deleteEntity(User.class, id);
    }

    public User getUserById(int id) {
        return DAOUtil.getEntityBy(User.class, PropertyName.ID, id);
    }

    public User getUserByLogin(String login){
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

    public List<User> getUsersList() {
        return DAOUtil.getEntityList(User.class);
    }
}
