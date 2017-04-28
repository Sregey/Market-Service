package com.d1l.dao.impl;

import com.d1l.dao.ResumeDao;
import com.d1l.dao.util.DAOUtil;
import com.d1l.dao.util.PropertyName;
import com.d1l.model.Customer;
import com.d1l.model.Resume;
import java.util.List;

public class ResumeDaoImpl implements ResumeDao{

    public void addOrUpdateResume(Resume resume) {
        DAOUtil.addOrUpdateEntity(resume);
    }

    public void deleteResume(int id) {
        DAOUtil.deleteEntity(Resume.class, id);
    }

    public Resume getResumeById(int id) {
        return DAOUtil.getEntityBy(Resume.class, PropertyName.ID, id);
    }

    public Resume getResumeByCustomer(Customer customer) {
        return DAOUtil.getEntityBy(Resume.class, PropertyName.CUSTOMER, customer);
    }

    public List<Resume> getResumeList() {
        return DAOUtil.getEntityList(Resume.class);
    }
}
