
package com.d1l.service;

import com.d1l.dao.*;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.*;
import com.d1l.util.ErrorList;
import com.d1l.util.SecurityService;
import com.d1l.util.Validation;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.ValidationException;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//package com.d1l.service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import java.util.ArrayList;
import java.util.HashMap;

public class Registration extends ActionSupport implements SessionAware {
    private String login;
    private String password;
    private String email;
    private String repeatpass;
    private String firstname;
    private String middlename;
    private String lastname;
    private String companyName;
    private String address;
    private Date birthdate;
    private String skills;
    private String phone;
    private String description;
    private String message;
    private String name;
    private String errorString;
    private SessionMap<String, Object> session;

    private String errorMessage = "It happened something bad... Please, try later.";
    private ArrayList<String> arrayListOfErrorMessagesForCostumer = new ArrayList<String>();
    private ArrayList<String> arrayListOfErrorMessagesForCompany = new ArrayList<String>();

    public SessionMap<String, Object> getSession() {
        return session;
    }
    public void setSession(SessionMap<String, Object> session) {
        this.session = session;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatpass() {
        return repeatpass;
    }
    public void setRepeatpass(String repeatpass) {
        this.repeatpass = repeatpass;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthdate;
    }
    public void setBirthDate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public String singupAsCustomer() throws Exception {
        try {
            Validation.validateCustomer(login, password, repeatpass, firstname, lastname, middlename);

            User user = new User();
            Customer customer = new Customer();
            Resume resume = new Resume();

            user.setLogin(this.login);
            user.setPassword(SecurityService.md5(this.password));
            user.setEmail(this.email);
            user.setRole(DaoFactory.getInstance().getRoleDao().getRoleByName("Customer"));
            DaoFactory.getInstance().getUserDao().addOrUpdateUser(user);

            customer.setFirstname(this.firstname);
            customer.setLastname(this.lastname);
            customer.setMiddlename(this.middlename);
            User userDao = DaoFactory.getInstance().getUserDao().getUserByLogin(this.login);
            customer.setUser(userDao);
            DaoFactory.getInstance().getCustomerDao().addOrUpdateCustomer(customer);

            resume.setFirstname(this.firstname);
            resume.setLastname(this.lastname);
            resume.setMiddlename(this.middlename);
            resume.setAddress(this.address);
            resume.setPhone(this.phone);
            resume.setDescription(this.description);
            resume.setSkills(this.skills);
            //DateFormat format = new SimpleDateFormat("dd.mm.yyyy");
            //Date date = format.parse(this.birthdate);

            resume.setBirthdate(this.birthdate);
            resume.setCustomer(DaoFactory.getInstance().getCustomerDao().getCustomerByUser(userDao));
            DaoFactory.getInstance().getResumeDao().addOrUpdateResume(resume);

            session.put("id", customer.getUser().getId());
            session.put("login", user.getLogin());
            session.put("role", user.getRole().getName());

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

    public String singupAsCompany() throws Exception {
        try {
            Validation.validateCompany(login, password, repeatpass, companyName);

            User user = new User();
            Company company = new Company();

            user.setLogin(this.login);
            user.setPassword(SecurityService.md5(this.password));
            user.setEmail(this.email);
            user.setRole(DaoFactory.getInstance().getRoleDao().getRoleByName("Company"));
            Role role = new Role();
            role.setName("Company");
            //user.setRole(role);
            DaoFactory.getInstance().getUserDao().addOrUpdateUser(user);

            company.setCompanyName(this.companyName);
            company.setUser(DaoFactory.getInstance().getUserDao().getUserByLogin(this.login));
            DaoFactory.getInstance().getCompanyDao().addOrUpdateCompany(company);

            session.put("id", company.getUser().getId());
            session.put("login", user.getLogin());
            session.put("role", user.getRole().getName());

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
