package com.d1l.controller.adminpanel;

import com.d1l.dao.RoleDao;
import com.d1l.dao.UserDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Role;
import com.d1l.model.User;
import com.d1l.util.SecurityService;
import com.d1l.util.Validation;
import com.d1l.util.ErrorList;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.ValidationException;

public class AddAdminController extends ActionSupport {

    private User user;
    private String repeatPassword;
    private String errorString;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getErrorString() {
        return errorString;
    }
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    public String add() {
        try {
            Validation.validateNewUser(user.getLogin(), user.getPassword(), repeatPassword);

            Role adminRole = DaoFactory.getInstance().getRoleDao().getRoleByName("Admin");
            user.setRole(adminRole);
            String encryptPass = SecurityService.md5(user.getPassword());
            user.setPassword(encryptPass);
            DaoFactory.getInstance().getUserDao().addOrUpdateUser(user);

            return Action.SUCCESS;
        }
        catch (ValidationException e) {
            errorString = e.getMessage();
            return Action.ERROR;
        }
        catch (Exception ex) {
            errorString = ErrorList.DEFAULT_ERROR_MESSAGE;
            return Action.ERROR;
        }
    }

}
