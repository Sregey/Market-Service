package com.d1l.dao.impl;

import com.d1l.dao.CustomerDao;
import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Customer;
import com.d1l.model.User;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    public void addOrUpdateCustomer(Customer customer) {
        DAOUtil.addOrUpdateEntity(customer);
    }

    public void deleteCustomer(int id) {
        DAOUtil.deleteEntity(Customer.class, id);
    }

    public Customer getCustomerById(int id) {
        return DAOUtil.getEntityBy(Customer.class, PropertyName.ID, id);
    }

    public Customer getCustomerByUser(User user) {
        return DAOUtil.getEntityBy(Customer.class, PropertyName.USER, user);
    }

    public List<Customer> getCustomersList() {
        return DAOUtil.getEntityList(Customer.class);
    }
}
