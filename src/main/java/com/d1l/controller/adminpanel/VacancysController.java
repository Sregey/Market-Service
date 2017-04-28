package com.d1l.controller.adminpanel;

import com.d1l.dao.CategoryDao;
import com.d1l.dao.CompanyDao;
import com.d1l.dao.VacancyDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Category;
import com.d1l.model.Company;
import com.d1l.model.Vacancy;
import com.d1l.util.Validation;
import com.d1l.util.ErrorList;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.ValidationException;

import java.util.List;

public class VacancysController extends ActionSupport {
    
    private Vacancy vacancy;
    private List<Vacancy> vacancyList;
    private int id;
    private int companyId;
    private List<Category> categoriesList;
    private List<Company> companiesList;
    private String errorString;

    public Vacancy getVacancy() {
        return vacancy;
    }
    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }
    public void setVacancyList(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<Category> getCategoriesList() {
        return categoriesList;
    }
    public void setCategoriesList(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public List<Company> getCompaniesList() {
        return companiesList;
    }
    public void setCompaniesList(List<Company> companiesList) {
        this.companiesList = companiesList;
    }

    public int getCompanyId(){
        return companyId;
    }
    public void setCompanyId(int companyId){
        this.companyId = companyId;
    }

    public String getErrorString() {
        return errorString;
    }
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    @Override
    public String execute() throws Exception {
        try {
            vacancyList = DaoFactory.getInstance().getVacancyDao().getVacancyList();
            categoriesList = DaoFactory.getInstance().getCategoruDao().getCategoriesList();
            companiesList = DaoFactory.getInstance().getCompanyDao().getCompanyList();
            return Action.SUCCESS;
        }
        catch (Exception e) {
            errorString = ErrorList.DEFAULT_ERROR_MESSAGE;
            return Action.ERROR;
        }
    }

    public String addOrUpdate() {
        try {
            Validation.validateVacancy(vacancy);
            Category vacancyCategory = DaoFactory.getInstance().getCategoruDao().getCategoryById(vacancy.getCategory().getId());
            vacancy.setCategory(vacancyCategory);
            Company vacancyCompany = DaoFactory.getInstance().getCompanyDao().getCompanyById(vacancy.getCompany().getId());
            vacancy.setCompany(vacancyCompany);
            DaoFactory.getInstance().getVacancyDao().addOrUpdateVacancy(vacancy);
            return Action.SUCCESS;
        }
        catch (ValidationException e){
            errorString = e.getMessage();
            return Action.ERROR;
        }
        catch (Exception e) {
            errorString = ErrorList.DEFAULT_ERROR_MESSAGE;
            return Action.ERROR;
        }
    }

    public String delete() {
        try {
            DaoFactory.getInstance().getVacancyDao().deleteVacancy(id);
            return Action.SUCCESS;
        }
        catch (Exception e) {
            errorString = ErrorList.DEFAULT_ERROR_MESSAGE;
            return Action.ERROR;
        }
    }

}
