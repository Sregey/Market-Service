package com.d1l.dao;

import com.d1l.model.Company;
import com.d1l.model.Vacancy;

import java.util.List;

public interface VacancyDao {
    void addOrUpdateVacancy(Vacancy vacancy);
    void deleteVacancy(int id);
    List<Vacancy> getVacancyByCompany(Company company);
    Vacancy getVacancyById(int id);
    List<Vacancy> getVacancyList();
}
