package com.d1l.dao;

import com.d1l.model.User;

import java.util.List;

public interface UserDao {
    void addOrUpdateUser(User user);
    void deleteUser(int id);
    User getUserById(int id);
    User getUserByLogin(String login);
    List<User> getUsersList();
}
