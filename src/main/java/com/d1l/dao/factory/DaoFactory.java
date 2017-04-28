package com.d1l.dao.factory;

import com.d1l.dao.*;
import com.d1l.dao.impl.*;

public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getInstance(){
        return instance;
    }
    public CategoryDao getCategoruDao(){
        return new CategoryDaoImpl();
    }
    public CompanyDao getCompanyDao(){ return new CompanyDaoImpl(); }
    public CustomerDao getCustomerDao(){
        return new CustomerDaoImpl();
    }
    public FeedbackDao getFeedbackDao(){
        return new FeedbackDaoImpl();
    }
    public FeedbackVacancyDao getFeedbackVacancyDao(){
        return new FeedbackVacancyDaoImpl();
    }
    public ResumeDao getResumeDao(){
        return new ResumeDaoImpl();
    }
    public RoleDao getRoleDao(){
        return new RoleDaoImpl();
    }
    public StatusDao getStatusDao(){
        return new StatusDaoImpl();
    }
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
    public VacancyDao getVacancyDao(){
        return new VacancyDaoImpl();
    }
}
