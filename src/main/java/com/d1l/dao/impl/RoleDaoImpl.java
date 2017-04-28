package com.d1l.dao.impl;

import com.d1l.dao.RoleDao;
import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Role;

import java.util.List;

public class RoleDaoImpl implements RoleDao{

    public void addOrUpdateRole(Role role) {
        DAOUtil.addOrUpdateEntity(role);
    }

    public void deleteRole(int id) {
        DAOUtil.deleteEntity(Role.class, id);
    }

    public List<Role> getRolesList() {
        return DAOUtil.getEntityList(Role.class);
    }

    public Role getRoleById(int id) {
        return DAOUtil.getEntityBy(Role.class, PropertyName.ID, id);
    }

    public Role getRoleByName(String name) {
        return DAOUtil.getEntityBy(Role.class, PropertyName.NAME, name);
    }

}

