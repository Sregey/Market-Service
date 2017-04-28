package com.d1l.dao;

import com.d1l.model.Role;

import java.util.List;

public interface RoleDao {
    void addOrUpdateRole(Role role);
    void deleteRole(int id);
    List<Role> getRolesList();
    Role getRoleById(int id);
    Role getRoleByName(String name);
}
