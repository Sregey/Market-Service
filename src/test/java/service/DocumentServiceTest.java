package service;

import com.d1l.controller.DocumentController;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.dao.impl.VacancyDaoImpl;
import com.d1l.dao.impl.FeedbackDaoImpl;
import com.d1l.dao.impl.UserDaoImpl;
import com.d1l.model.*;
import com.d1l.service.DocumentGenerator;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class DocumentServiceTest {

    DocumentGenerator documentGenerator;
    DocumentController documentController;

    @Before
    public void setUp() throws Exception {

        documentGenerator = new DocumentGenerator();
        documentController = new DocumentController();
        documentController.setId(1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addWaterMark() throws Exception {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, stream);
        pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        document.open();
        documentGenerator.addWaterMark(pdfWriter);
    }

    @Test
    public void setCategoriesRow() throws Exception {
        documentGenerator.setCategoriesRow(new Category());
    }

    @Test
    public void setOrdersRow() throws Exception {
        documentGenerator.setFeedbackRow(DaoFactory.getInstance().getFeedbackDao().getFeedbackById(1));
    }

    @Test
    public void setItemsRow() throws Exception {
        documentGenerator.setVacancyRow(DaoFactory.getInstance().getVacancyDao().getVacancyById(1));
    }

    @Test
    public void setUsersRow() throws Exception {
        documentGenerator.setUsersRow(DaoFactory.getInstance().getUserDao().getUserById(1));
    }

    @Test
    public void getUsersInPdf() throws Exception {
        documentGenerator.generateUsersInPDFById(1);
    }

    @Test
    public void getUsersInXls() throws Exception {
        documentGenerator.generateUsersInXLS();
    }

    @Test
    public void getUsersInCsv() throws Exception {
        documentGenerator.generateUsersInCSV();
    }

    @Test
    public void getCategoriesInXls() throws Exception {
        documentGenerator.generateCategoriesInXLS();
    }

    @Test
    public void getCategoriesInCsv() throws Exception {
        documentGenerator.generateCategoriesInCSV();
    }

    @Test
    public void getCategoriesInPdf() throws Exception {
        documentGenerator.generateCategoriesInPDFById(1);
    }



    @Test
    public void getOrdersInXls() throws Exception {
        documentGenerator.generateFeedbacksInXLS(DaoFactory.getInstance().getFeedbackDao().getFeedbacksList());
    }

    @Test
    public void getOrdersInCsv() throws Exception {
        documentGenerator.generateFeedbacksInCSV(DaoFactory.getInstance().getFeedbackDao().getFeedbacksList());
    }

    @Test
    public void getOrdersInPdf() throws Exception {
        documentGenerator.generateFeedbackInPDFById(1);
    }

    @Test
    public void getItemsInXls() throws Exception {
        documentGenerator.generateVacancysInXLS(DaoFactory.getInstance().getVacancyDao().getVacancyList());
    }

    @Test
    public void getItemsInCsv() throws Exception {
        documentGenerator.generateVacancysInCSV(DaoFactory.getInstance().getVacancyDao().getVacancyList());
    }

    @Test
     public void getItemsInPdf() throws Exception {
        documentGenerator.generateVacancyInPDFById(1);
    }

    @Test
    public void getItemsINPdf() throws Exception {
        documentGenerator.generateVacancyInPDFById(1);
    }

    @Test
    public void getOrdersInCsvController() throws Exception {
        documentController.getFeedbacksCSV();
    }

    @Test
    public void getOrdersInPdfController() throws Exception {
        documentController.getFeedbackPDF();
    }

    @Test
    public void getOrdersInXlsController() throws Exception {
        documentController.getFeedbacksXLS();
    }

    @Test
    public void getItemsInCsvController() throws Exception {
        documentController.getVacancysCSV();
    }

    @Test
    public void getItemsInPdfController() throws Exception {
        documentController.getVacancyPDF();
    }

    @Test
    public void getItemsInXlsController() throws Exception {
        documentController.getVacancysXLS();
    }

    @Test
    public void getUsersInPdfController() throws Exception {
        documentController.getUserPDF();
    }

    @Test
    public void getUsersInXlsController() throws Exception {
        documentController.getUsersXLS();
    }

    @Test
    public void getUsersInCsvController() throws Exception {
        documentController.getUsersCSV();
    }

    @Test
    public void getCategoriesInPdfController() throws Exception {
        documentController.getCategoryPDF();
    }

    @Test
    public void getCategoriesInCsvController() throws Exception {
        documentController.getCategoriesCSV();
    }

    @Test
    public void getCategoriesInXlsController() throws Exception {
        documentController.getCategoriesXLS();
    }





    @Test
    public void makeResponse() throws Exception {
        documentController.execute();
    }

}
