package com.d1l.service;

import com.d1l.dao.UserDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.User;
import com.d1l.util.ErrorList;
import com.d1l.util.SecurityService;
import com.d1l.util.HttpURLConnectionExample;
import com.d1l.util.Validation;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.validator.ValidationException;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Authorisation extends ActionSupport implements SessionAware {

    private String login;
    private String password;
    private String errorString;
    private SessionMap<String, Object> session;

    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public String getErrorString() {
        return errorString;
    }
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public void setSession(Map<String, Object> map) {
        this.session = (SessionMap<String, Object>) map;
    }

    @Override
    public String execute() throws Exception {
        return Action.SUCCESS;
    }

    public String logout() throws Exception {
        session.invalidate();
        return Action.SUCCESS;
    }

    public String login() throws Exception {
        try {
            User user = DaoFactory.getInstance().getUserDao().getUserByLogin(login);
            Validation.validateLoginUser(user, login, password);
            session.put("id", user.getId());
            session.put("login", user.getLogin());
            session.put("role", user.getRole().getName());
            return Action.SUCCESS;
        }
        catch (ValidationException e) {
            errorString = e.getMessage();
            return Action.ERROR;
        }
        catch (Exception e) {
            errorString = ErrorList.DEFAULT_ERROR_MESSAGE;
            return Action.LOGIN;
        }
    }

    public String sendPasswordToEmail() throws Exception {
    /*    try {
            User user = UserDao.getUserByLogin(this.login);
            if (user == null) {
                SetErrorOnThePage("Please, check the username. Such user is not exists.");
                return Action.ERROR;
            }
            if (user.getEmail() == null || user.getEmail().equals("")) {
                SetErrorOnThePage("Sorry, such user's email is not exists.");
                return Action.ERROR;
            } else {
                user.setPassword(SecurityService.md5(SecurityService.GenerateRandomPassword()));
                UserDao.addOrUpdateUser(user);
                String textToSend =
                        "Hi!\nYour password: " +
                        user.getPassword() +
                        "\nThanks for using our service!";
                String subjectToSend = "SPP labs: hello, dear " + user.getLogin() + "!";
                String emailToSend = user.getEmail();

                new HttpURLConnectionExample().sendPost(
                        textToSend,
                        subjectToSend,
                        emailToSend
                );

                SetInfoOnThePage("Check your password on email!");

                return Action.SUCCESS;
            }
        }
        catch (Exception exp) {
            SetErrorOnThePage("Something go wrong. Please, try one more time.");
            return Action.ERROR;
        }*/
        return Action.SUCCESS;
    }

}

