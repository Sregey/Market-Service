package com.d1l.dao.impl;

import com.d1l.dao.CompanyDao;
import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Company;
import com.d1l.model.User;

import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    public void addOrUpdateCompany(Company company) {
        DAOUtil.addOrUpdateEntity(company);
    }

    public void deleteCompany(int id) {
        DAOUtil.deleteEntity(Company.class, id);
    }

    public Company getCompanyById(int id) {
        return DAOUtil.getEntityBy(Company.class, PropertyName.ID, id);
    }

    public Company getCompanyByUser(User user) {
        return DAOUtil.getEntityBy(Company.class, PropertyName.USER, user);
    }

    public List<Company> getCompanyList() {
        return DAOUtil.getEntityList(Company.class);
    }
}
