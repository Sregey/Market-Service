package com.d1l.controller.company;

import com.d1l.dao.*;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.*;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeedbacksController extends ActionSupport {

    private List<Feedback> feedbackList;
    private int id;
    private int companyId;

    @Override
    public String execute() throws Exception {
        return getFeedbacks();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId(){return companyId;}
    public void setCompanyId(int companyId){this.companyId = companyId;}

    public List<Feedback> getFeedbackList(){return feedbackList;}
    public void setFeedbackList(List<Feedback> feedbackList) {this.feedbackList = feedbackList;}

    private String getFeedbacks()
    {
        try {
            Map session = ActionContext.getContext().getSession();
            if(session.containsKey("id"))
            {
                companyId = Integer.parseInt(session.get("id").toString());
                User user = DaoFactory.getInstance().getUserDao().getUserById(companyId);
                Company company =  DaoFactory.getInstance().getCompanyDao().getCompanyByUser(user);
                feedbackList = DaoFactory.getInstance().getFeedbackDao().getFeedbackListByCompany(company);
            }
            return Action.SUCCESS;
        }
        catch (Exception exp)
        {
            return Action.SUCCESS;
        }
    }

    public String approve() {
        int id = getId();
        Feedback feedback = DaoFactory.getInstance().getFeedbackDao().getFeedbackById(id);
        Status status = DaoFactory.getInstance().getStatusDao().getApprovedStatus();
        feedback.setStatus(status);
        DaoFactory.getInstance().getFeedbackDao().addOrUpdateFeedback(feedback);
        getFeedbacks();
        return Action.SUCCESS;
    }

    public String reject() {
        int id = getId();
        Feedback feedback = DaoFactory.getInstance().getFeedbackDao().getFeedbackById(id);
        Status status = DaoFactory.getInstance().getStatusDao().getRejectedStatus();
        feedback.setStatus(status);
        DaoFactory.getInstance().getFeedbackDao().addOrUpdateFeedback(feedback);
        getFeedbacks();
        return Action.SUCCESS;
    }

}
