package com.d1l.dao;

import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Role;

import java.util.List;

public class RoleDao {

    public static void addOrUpdateRole(Role role) {
        DAOUtil.addOrUpdateEntity(role);
    }

    public static void deleteRole(int id) {
        DAOUtil.deleteEntity(Role.class, id);
    }

    public static List<Role> getRolesList() {
        return DAOUtil.getEntityList(Role.class);
    }

    public static Role getRoleById(int id) {
        return DAOUtil.getEntityBy(Role.class, PropertyName.ID, id);
    }

    public static Role getRoleByName(String name) {
        return DAOUtil.getEntityBy(Role.class, PropertyName.NAME, name);
    }

}

