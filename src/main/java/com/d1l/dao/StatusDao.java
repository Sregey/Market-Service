package com.d1l.dao;

import com.d1l.model.Status;

public interface StatusDao {
    void addOrUpdateStatus(Status status);
    void deleteStatus(int id);
    Status getStatusById(int id);
    Status getStatusByName(String name);
    Status getApprovedStatus();
    Status getRejectedStatus();
    Status getDefaultStatus();
}
