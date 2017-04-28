package com.d1l.controller.company;

import com.d1l.dao.*;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Category;
import com.d1l.model.Company;
import com.d1l.model.User;
import com.d1l.model.Vacancy;
import com.d1l.util.ErrorList;
import com.d1l.util.Validation;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.ValidationException;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacancysController extends ActionSupport {


    private Vacancy vacancy;
    private List<Vacancy> vacancyList;
    private int id;
    private int companyId;
    private List<Category> categoriesList;
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
            Map session = ActionContext.getContext().getSession();
            if(session.containsKey("id")) {
                companyId = Integer.parseInt(session.get("id").toString());
                User user = DaoFactory.getInstance().getUserDao().getUserById(companyId);
                Company currCompany = DaoFactory.getInstance().getCompanyDao().getCompanyByUser(user);
                vacancyList = DaoFactory.getInstance().getVacancyDao().getVacancyByCompany(currCompany);
                categoriesList = DaoFactory.getInstance().getCategoruDao().getCategoriesList();
            }
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

            Map session = ActionContext.getContext().getSession();
            if (session.containsKey("id")) {
                Category category = DaoFactory.getInstance().getCategoruDao().getCategoryById(vacancy.getCategory().getId());
                vacancy.setCategory(category);
                User user = DaoFactory.getInstance().getUserDao().getUserById(Integer.parseInt(session.get("id").toString()));
                vacancy.setCompany(DaoFactory.getInstance().getCompanyDao().getCompanyByUser(user));
                DaoFactory.getInstance().getVacancyDao().addOrUpdateVacancy(vacancy);
            }
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
