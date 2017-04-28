package com.d1l.controller.adminpanel;

import com.d1l.dao.CategoryDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Category;
import com.d1l.util.Validation;
import com.d1l.util.ErrorList;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.ValidationException;

import java.util.List;

public class CategoriesController extends ActionSupport {

    private int id;
    private Category category;
    private List<Category> categoriesList;
    private String errorString;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoriesList() {
        return categoriesList;
    }
    public void setCategoriesList(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
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
            categoriesList = DaoFactory.getInstance().getCategoruDao().getCategoriesList();
            return Action.SUCCESS;
        }
        catch (Exception e) {
            errorString = ErrorList.DEFAULT_ERROR_MESSAGE;
            return Action.ERROR;
        }
    }

    public String addOrUpdate() {
        try {
            Validation.validateCategoryName(category.getName());
            DaoFactory.getInstance().getCategoruDao().addOrUpdateCategory(category);
            return Action.SUCCESS;
        }
        catch (ValidationException e) {
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
            DaoFactory.getInstance().getCategoruDao().deleteCategory(id);
            return Action.SUCCESS;
        }
        catch (Exception e) {
            errorString = ErrorList.DEFAULT_ERROR_MESSAGE;
            return Action.ERROR;
        }
    }

}
