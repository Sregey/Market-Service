package com.d1l.dao;

import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Customer;
import com.d1l.model.User;

import java.util.List;

public class CustomerDao {

    public static void addOrUpdateCustomer(Customer customer) {
        DAOUtil.addOrUpdateEntity(customer);
    }

    public static void deleteCustomer(int id) {
        DAOUtil.deleteEntity(Customer.class, id);
    }

    public static Customer getCustomerById(int id) {
        return DAOUtil.getEntityBy(Customer.class, PropertyName.ID, id);
    }

    public static Customer getCustomerByUser(User user) {
        return DAOUtil.getEntityBy(Customer.class, PropertyName.USER, user);
    }

    public static List<Customer> getCustomersList() {
        return DAOUtil.getEntityList(Customer.class);
    }
}
