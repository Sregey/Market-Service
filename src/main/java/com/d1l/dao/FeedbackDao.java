package com.d1l.dao;

import com.d1l.model.*;

import java.util.List;

public interface FeedbackDao {
    void addOrUpdateFeedback(Feedback feedback);
    void deleteFeedback(int id);
    Feedback getFeedbackById(int id);
    List<Feedback> getFeedbackListByCustomer(Customer customer);
    Feedback getFeedbackByResumeAndVacancy(Resume resume, Vacancy vacancy);
    List<Feedback> getFeedbackListByCompany(Company company);
    Feedback getFeedbackByCustomerAndLastDate(Customer customer);
    List<Feedback> getFeedbacksList();
}
