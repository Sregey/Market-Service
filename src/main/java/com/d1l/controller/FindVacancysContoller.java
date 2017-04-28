package com.d1l.controller;

import com.d1l.dao.*;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class FindVacancysContoller extends ActionSupport {
    private List<Vacancy> vacancyList;

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }
    public void setVacancyList(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

    @Override
    public String execute() throws Exception {
        try{
            vacancyList = DaoFactory.getInstance().getVacancyDao().getVacancyList();
            return Action.SUCCESS;
        }
        catch (Exception exp) {
            return Action.SUCCESS;
        }
    }
}

