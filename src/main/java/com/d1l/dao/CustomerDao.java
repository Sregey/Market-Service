package com.d1l.dao;

import com.d1l.model.Customer;
import com.d1l.model.User;

import java.util.List;

public interface CustomerDao {
    void addOrUpdateCustomer(Customer customer);
    void deleteCustomer(int id);
    Customer getCustomerById(int id);
    Customer getCustomerByUser(User user);
    List<Customer> getCustomersList();
}
