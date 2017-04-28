package com.d1l.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "marketservice.feedback_vacancy")
public class FeedbackVacancy {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "feedback_id")
    private int feedbackId;

    @Column(name = "vacancy_id")
    private int vacancyId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getVacancyId() {
        return vacancyId;
    }
    public void setVacancyId(int itemId) {
        this.vacancyId = itemId;
    }
}
