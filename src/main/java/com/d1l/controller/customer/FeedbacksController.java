package com.d1l.controller.customer;

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
    private int customerId;

    public int getCustomerId(){return customerId;}
    public void setCustomerId(int customerId){this.customerId = customerId;}

    @Override
    public String execute() throws Exception
    {
        try
        {
            Map session = ActionContext.getContext().getSession();
            if(session.containsKey("id"))
            {
                customerId = Integer.parseInt(session.get("id").toString());
                User user = DaoFactory.getInstance().getUserDao().getUserById(customerId);
                Customer customer = DaoFactory.getInstance().getCustomerDao().getCustomerByUser(user);
                feedbackList = DaoFactory.getInstance().getFeedbackDao().getFeedbackListByCustomer(customer);
            }
            return Action.SUCCESS;
        }
        catch (Exception exp)
        {
            return Action.SUCCESS;
        }
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }
    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }
}
