package com.d1l.dao;

import com.d1l.model.Company;
import com.d1l.model.User;

import java.util.List;

public interface CompanyDao {
    void addOrUpdateCompany(Company company);
    void deleteCompany(int id);
    Company getCompanyById(int id);
    Company getCompanyByUser(User user);
    List<Company> getCompanyList();
}
