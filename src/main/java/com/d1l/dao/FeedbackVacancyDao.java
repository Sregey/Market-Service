package com.d1l.dao;

import com.d1l.model.FeedbackVacancy;

import java.util.List;

public interface FeedbackVacancyDao {
    void addOrUpdateFeedbackVacancy(FeedbackVacancy feedbackVacancy);
    void deleteFeedbackVacancy(int id);
    List<FeedbackVacancy> getFeedbackVacancysList();
}
