package com.d1l.controller.adminpanel;

import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.User;
import com.d1l.dao.UserDao;
import com.d1l.util.ErrorList;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class UsersController extends ActionSupport {

    private int id;
    private List<User> usersList;
    private String errorString;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUsersList() {
        return usersList;
    }
    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
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
            usersList = DaoFactory.getInstance().getUserDao().getUsersList();
            return Action.SUCCESS;
        }
        catch (Exception e) {
            errorString = ErrorList.DEFAULT_ERROR_MESSAGE;
            return Action.ERROR;
        }
    }

    public String delete() {
        try {
            String login = DaoFactory.getInstance().getUserDao().getUserById(id).getLogin();
            if (login.equals("Admin"))
                return Action.SUCCESS;

            DaoFactory.getInstance().getUserDao().deleteUser(id);
            return Action.SUCCESS;
        }
        catch (Exception exp) {
            errorString = "User doesn't exist.";
            return Action.SUCCESS;
        }
    }

}
