package com.d1l.controller.adminpanel;

import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Resume;
import com.d1l.dao.ResumeDao;
import com.d1l.util.Validation;
import com.d1l.util.ErrorList;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.ValidationException;

import java.util.List;

public class ResumesController extends ActionSupport {

    private Resume resume;
    private List<Resume> resumeList;
    private String errorString;

    public Resume getResume() {
        return resume;
    }
    public void setResume(Resume resume){
        this.resume = resume;
    }

    public List<Resume> getResumeList() {
        return resumeList;
    }
    public void setResumeList(List<Resume> resumeList){
        this.resumeList = resumeList;
    }

    public String getErrorString() {
        return errorString;
    }
    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    @Override
    public String execute() throws Exception
    {
        try {
            resumeList = DaoFactory.getInstance().getResumeDao().getResumeList();
            return Action.SUCCESS;
        }
        catch (Exception exp) {
            return Action.SUCCESS;
        }
    }

    public String update() {
        try {
            Validation.validateResume(resume);
            DaoFactory.getInstance().getResumeDao().addOrUpdateResume(resume);
            return Action.SUCCESS;
        }
        catch (ValidationException e){
            errorString = e.getMessage();
            return Action.ERROR;
        }
        catch (Exception e) {
            errorString = ErrorList.DEFAULT_ERROR_MESSAGE;
            return Action.ERROR;
        }
    }
}
