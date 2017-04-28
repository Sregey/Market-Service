package com.d1l.controller.customer;

import com.d1l.dao.*;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VacancysController extends ActionSupport {

    private List<Vacancy> vacancyList;
    private String vacancyId;
    private String message;

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }
    public String getVacancyId() {
        return vacancyId;
    }
    public String getMessage(){return message;}
    public void setMessage(String message){this.message = message;}

    @Override
    public String execute() throws Exception {
        vacancyList = DaoFactory.getInstance().getVacancyDao().getVacancyList();
        return Action.SUCCESS;
    }

    public String makeFeedback() throws Exception
    {
        try
        {
            Map session = ActionContext.getContext().getSession();

            int id = Integer.parseInt(session.get("id").toString());
            User user = DaoFactory.getInstance().getUserDao().getUserById(id);
            Customer customer = DaoFactory.getInstance().getCustomerDao().getCustomerByUser(user);
            Resume resume = DaoFactory.getInstance().getResumeDao().getResumeByCustomer(customer);
            Vacancy vacancy = DaoFactory.getInstance().getVacancyDao().getVacancyById(Integer.parseInt(vacancyId));

            FeedbackDao feedbackDao = DaoFactory.getInstance().getFeedbackDao();
            Feedback exists = feedbackDao.getFeedbackByResumeAndVacancy(resume, vacancy);
            if(exists == null) {
                Feedback feedback = new Feedback();
                feedback.setResume(resume);
                feedback.setVacancy(vacancy);
                feedback.setDate(new Date());
                Status status = DaoFactory.getInstance().getStatusDao().getDefaultStatus();
                feedback.setStatus(status);
                feedback.setMessage(getMessage());
                feedbackDao.addOrUpdateFeedback(feedback);

                FeedbackVacancy feedbackVacancy = new FeedbackVacancy();
                feedbackVacancy.setFeedbackId(feedbackDao.getFeedbackByResumeAndVacancy(resume, vacancy).getId());
                feedbackVacancy.setVacancyId(vacancy.getId());
                DaoFactory.getInstance().getFeedbackVacancyDao().addOrUpdateFeedbackVacancy(feedbackVacancy);
            }
        }
        catch (Exception exp) {}

        return Action.SUCCESS;
    }

    public List<Vacancy> getVacancyList() {
        return vacancyList;
    }
    public void setVacancyList(List<Vacancy> vacancyList) {
        this.vacancyList = vacancyList;
    }

}
