package com.d1l.dao.impl;

import com.d1l.dao.FeedbackVacancyDao;
import com.d1l.dao.util.DAOUtil;
import com.d1l.model.FeedbackVacancy;

import java.util.List;

public class FeedbackVacancyDaoImpl implements FeedbackVacancyDao{

    public void addOrUpdateFeedbackVacancy(FeedbackVacancy feedbackVacancy) {
        DAOUtil.addOrUpdateEntity(feedbackVacancy);
    }

    public void deleteFeedbackVacancy(int id) {
        DAOUtil.deleteEntity(FeedbackVacancy.class, id);
    }

    public List<FeedbackVacancy> getFeedbackVacancysList() {
        return DAOUtil.getEntityList(FeedbackVacancy.class);
    }
}
