package com.d1l.controller.customer;

import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Customer;
import com.d1l.model.Resume;
import com.d1l.dao.*;
import com.d1l.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

public class ResumeController extends ActionSupport {

    private Resume resume;

    @Override
    public String execute() throws Exception
    {
        try
        {
            Map session = ActionContext.getContext().getSession();
            if(session.containsKey("id"))
            {
                int customerId = Integer.parseInt(session.get("id").toString());
                User user = DaoFactory.getInstance().getUserDao().getUserById(customerId);
                Customer customer = DaoFactory.getInstance().getCustomerDao().getCustomerByUser(user);
                resume = DaoFactory.getInstance().getResumeDao().getResumeByCustomer(customer);
            }
            return Action.SUCCESS;
        }
        catch (Exception exp)
        {
            return Action.SUCCESS;
        }
    }

    public Resume getResume() {return resume;}
    public void setResume(Resume resume){this.resume = resume; }
}
