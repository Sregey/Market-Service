package com.d1l.controller;

import com.d1l.dao.*;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.*;
import com.opensymphony.xwork2.ActionSupport;
import com.d1l.service.DocumentGenerator;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class DocumentController extends ActionSupport implements ServletResponseAware {

    private HttpServletResponse response;
    private int id;
    private int companyId;
    private int customerId;

    public int getCustomerId(){return customerId;}
    public void setCustomerId(int customerId){this.customerId = customerId;}

    public int getCompanyId(){return companyId;}
    public void setCompanyId(int companyId){this.companyId = companyId;}

    private void makeResponse(ByteArrayOutputStream stream, String contentType, String fileName) throws IOException {
        try {
        response.setContentType(contentType);
        response.setHeader("Content-Disposition",
                "inline; filename=" + fileName);
        response.setContentLength(stream.size());

        OutputStream os = response.getOutputStream();
        os.write(stream.toByteArray());
        os.flush();
        os.close();
        stream.reset();} catch (Exception exp){}
    }
    @Override
    public String execute() throws Exception {

        return super.execute();
    }

    public String getFeedbackPDF() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateFeedbackInPDFById(getId()), "application/pdf", "feedback.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    private List<Feedback> getFeedbacksByCompanyId(int id){
        User user = DaoFactory.getInstance().getUserDao().getUserById(id);
        Company company = DaoFactory.getInstance().getCompanyDao().getCompanyByUser(user);

       List<Feedback> feedbackList = DaoFactory.getInstance().getFeedbackDao().getFeedbackListByCompany(company);
        return feedbackList;
    }

    private List<Feedback> getFeedbacksByCustomerId(int id){
        User user = DaoFactory.getInstance().getUserDao().getUserById(id);
        Customer customer = DaoFactory.getInstance().getCustomerDao().getCustomerByUser(user);

        List<Feedback> feedbackList = DaoFactory.getInstance().getFeedbackDao().getFeedbackListByCustomer(customer);
        return feedbackList;
    }

    public String getFeedbacksXLSByCompanyId() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateFeedbacksInXLS(getFeedbacksByCompanyId(getCompanyId())), "application/vnd.ms-excel", "feedbacks.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getFeedbacksCSVByCompanyId() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateFeedbacksInCSV(getFeedbacksByCompanyId(getCompanyId())), "text/csv", "feedbacks.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getFeedbacksXLSByCustomerId() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateFeedbacksInXLS(getFeedbacksByCustomerId(getCustomerId())), "application/vnd.ms-excel", "feedbacks.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getFeedbacksCSVByCustomerId() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateFeedbacksInCSV(getFeedbacksByCustomerId(getCustomerId())), "text/csv", "feedbacks.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getFeedbacksXLS() throws IOException {
        try {
            List<Feedback> feedbacks = DaoFactory.getInstance().getFeedbackDao().getFeedbacksList();
            makeResponse(DocumentGenerator.generateFeedbacksInXLS(feedbacks),
                    "application/vnd.ms-excel",
                    "feedbacks.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getFeedbacksCSV() throws IOException {
        try {
            List<Feedback> feedbacks = DaoFactory.getInstance().getFeedbackDao().getFeedbacksList();
            makeResponse(DocumentGenerator.generateFeedbacksInCSV(feedbacks),
                    "text/csv",
                    "feedbacks.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getCategoryPDF() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateCategoriesInPDFById(getId()), "application/pdf", "category.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getCategoriesXLS() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateCategoriesInXLS(), "application/vnd.ms-excel", "categories.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getCategoriesCSV() throws IOException {
        try {
        makeResponse(DocumentGenerator.generateCategoriesInCSV(), "text/csv", "categories.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getUserPDF() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateUsersInPDFById(getId()), "application/pdf", "user.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getUsersXLS() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateUsersInXLS(), "application/vnd.ms-excel", "users.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getUsersCSV() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateUsersInCSV(), "text/csv", "users.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getVacancyPDF() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateVacancyInPDFById(getId()), "application/pdf", "vacancy.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    private List<Vacancy> getVacancysByCompanyId(int id){
        User user = DaoFactory.getInstance().getUserDao().getUserById(id);
        Company company = DaoFactory.getInstance().getCompanyDao().getCompanyByUser(user);

        List<Vacancy> vacancyList = DaoFactory.getInstance().getVacancyDao().getVacancyByCompany(company);
        return vacancyList;
    }

    public String getVacancysXLSByCompanyId() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateVacancysInXLS(getVacancysByCompanyId(getCompanyId())), "application/vnd.ms-excel", "vacancys.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getVacancysCSVByCompanyId() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateVacancysInCSV(getVacancysByCompanyId(getCompanyId())), "text/csv", "vacancys.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getVacancysXLS() throws IOException {
        try {
            List<Vacancy> vacancies = DaoFactory.getInstance().getVacancyDao().getVacancyList();
            makeResponse(DocumentGenerator.generateVacancysInXLS(vacancies),
                    "application/vnd.ms-excel",
                    "vacancys.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }
    public String getVacancysCSV() throws IOException {
        try {
            List<Vacancy> vacancies = DaoFactory.getInstance().getVacancyDao().getVacancyList();
            makeResponse(DocumentGenerator.generateVacancysInCSV(vacancies),
                    "text/csv",
                    "vacancys.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getResumePdf() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateResumeInPDFById(getId()), "application/pdf", "resume.pdf");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getResumeXLS() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateResumeInXLS(getId()), "application/vnd.ms-excel", "resume.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getResumeListXLS() throws IOException {
        try {
            List<Resume> resumes = DaoFactory.getInstance().getResumeDao().getResumeList();
            makeResponse(DocumentGenerator.generateResumesInXLS(resumes),
                    "application/vnd.ms-excel",
                    "resumes.xls");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getResumeCSV() throws IOException {
        try {
            makeResponse(DocumentGenerator.generateResumeInCSV(getId()), "text/csv", "resume.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }

    public String getResumeListCSV() throws IOException {
        try {
            List<Resume> resumes = DaoFactory.getInstance().getResumeDao().getResumeList();
            makeResponse(DocumentGenerator.generateResumesInCSV(resumes),
                    "text/csv",
                    "resume.csv");
        } catch (Exception ex) {

        }
        return NONE;
    }
}
