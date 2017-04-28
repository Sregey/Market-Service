package com.d1l.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "marketservice.feedback")
public class Feedback {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "message")
    private String message;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name="resume_id")
    private Resume resume;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Resume getResume() {
        return resume;
    }
    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {return message;}
    public void setMessage(String message){this.message = message;}

    public Vacancy getVacancy() {return vacancy;}
    public void setVacancy(Vacancy vacancy){this.vacancy = vacancy;}

    public Status getStatus(){return status;}
    public void setStatus(Status status){this.status = status;}
}
