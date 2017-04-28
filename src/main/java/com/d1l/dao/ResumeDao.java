package com.d1l.dao;

import com.d1l.model.Customer;
import com.d1l.model.Resume;

import java.util.List;

public interface ResumeDao {
    void addOrUpdateResume(Resume resume);
    void deleteResume(int id);
    Resume getResumeById(int id);
    Resume getResumeByCustomer(Customer customer);
    List<Resume> getResumeList();
}
