package com.d1l.dao.impl;

import com.d1l.dao.StatusDao;
import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Status;

public class StatusDaoImpl implements StatusDao {

    public void addOrUpdateStatus(Status status) {
        DAOUtil.addOrUpdateEntity(status);
    }

    public void deleteStatus(int id) {
        DAOUtil.deleteEntity(Status.class, id);
    }

    public Status getStatusById(int id) {
        return DAOUtil.getEntityBy(Status.class, PropertyName.ID, id);
    }

    public Status getStatusByName(String name){
        return DAOUtil.getEntityBy(Status.class, PropertyName.NAME, name);
    }

    public Status getApprovedStatus() {
        return getStatusByName("Approved");
    }

    public Status getRejectedStatus() {
        return getStatusByName("Rejected");
    }

    public Status getDefaultStatus() {
        return getStatusByName("Default");
    }
}
