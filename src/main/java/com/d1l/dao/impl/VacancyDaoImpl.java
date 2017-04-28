package com.d1l.dao.impl;

import com.d1l.dao.VacancyDao;
import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Vacancy;
import com.d1l.model.Company;

import java.util.List;

public class VacancyDaoImpl implements VacancyDao{

    public void addOrUpdateVacancy(Vacancy vacancy) {
        DAOUtil.addOrUpdateEntity(vacancy);
    }

    public void deleteVacancy(int id) {
        DAOUtil.deleteEntity(Vacancy.class, id);
    }

    public List<Vacancy> getVacancyByCompany(Company company) {
        return DAOUtil.getEntityListBy(Vacancy.class, PropertyName.COMPANY, company);
    }

    public Vacancy getVacancyById(int id) {
        return DAOUtil.getEntityBy(Vacancy.class, PropertyName.ID, id);
    }

    public List<Vacancy> getVacancyList() {
        return DAOUtil.getEntityList(Vacancy.class);
    }
}
