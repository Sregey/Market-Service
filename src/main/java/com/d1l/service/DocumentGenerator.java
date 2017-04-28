package com.d1l.service;

import com.d1l.dao.factory.DaoFactory;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import com.d1l.dao.*;
import com.d1l.model.*;
import org.apache.logging.log4j.Marker;
import org.apache.poi.hssf.usermodel.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DocumentGenerator {

    private static final Font FONT_FOR_OBJECT_NAME = FontFactory.getFont(FontFactory.HELVETICA, 20,
            Font.BOLD);
    private static final Font COMMON_FONT = FontFactory.getFont(FontFactory.HELVETICA, 20);

    public static void addWaterMark(PdfWriter writer) {
        Phrase watermark = new Phrase("Autoparts", FontFactory.getFont(FontFactory.HELVETICA, 40,
                Font.BOLD, Color.LIGHT_GRAY));
        Rectangle pageSize = writer.getPageSize();
        float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
        float y = (pageSize.getTop() + pageSize.getBottom()) / 2;
        PdfContentByte canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, x, y, 45);
    }

    public static List<String> setFeedbackRow(Feedback feedback){
        List<String> feedbackRow = new LinkedList<String>();
        //List<OrderVacancy> orderVacancies = OrderItemDao.getOrderItemsByOrderId(feedback.getId());
        Resume resume = feedback.getResume();
        Vacancy vacancy = feedback.getVacancy();
        Status status = feedback.getStatus();
        feedbackRow.add(String.format("%d", feedback.getId()));
        feedbackRow.add(String.format("%s ", resume.getFirstname()));
        feedbackRow.add(String.format("%s ", resume.getLastname()));
        feedbackRow.add(String.format("%s ", resume.getMiddlename()));
        feedbackRow.add(String.format("%s ", resume.getDescription()));
        feedbackRow.add(String.format("%s ", resume.getSkills()));
        feedbackRow.add(String.format("%s ", feedback.getMessage()));
        feedbackRow.add(String.format("%s", feedback.getDate()));
        feedbackRow.add(String.format("%s ", vacancy.getTitle()));
        feedbackRow.add(String.format("%s ", vacancy.getDescription()));
        feedbackRow.add(String.format("%s ", vacancy.getCategory().getName()));
        feedbackRow.add(String.format("%s ", vacancy.getSkills()));
        feedbackRow.add(String.format("%s ", vacancy.getSalary()));
        feedbackRow.add(String.format("%s ", feedback.getStatus().getName()));
        return feedbackRow;
    }

    public static List<String> setCategoriesRow(Category category) {
        List<String> categoriesRow = new LinkedList<String>();

        categoriesRow.add(String.format("%d", category.getId()));
        categoriesRow.add(String.format("%s ", category.getName()));

        return categoriesRow;
    }

    public static List<String> setVacancyRow(Vacancy vacancy) {
        List<String> ItemsRow = new LinkedList<String>();

        ItemsRow.add(String.format("%d", vacancy.getId()));
        ItemsRow.add(String.format("%s ", vacancy.getTitle()));
        ItemsRow.add(String.format("%s ", vacancy.getCompany().getCompanyName()));
        ItemsRow.add(String.format("%s ", vacancy.getCategory().getName()));
        ItemsRow.add(String.format("%s ", vacancy.getDescription()));
        ItemsRow.add(String.format("%s ", vacancy.getEmail()));
        ItemsRow.add(String.format("%s ", vacancy.getPhone()));
        ItemsRow.add(String.format("%s ", vacancy.getSkills()));
        ItemsRow.add(String.format("%s ", vacancy.getSalary()));

        return ItemsRow;
    }

    public static List<String> setResumeRow(Resume resume) {
        List<String> resumeRow = new LinkedList<String>();
        resumeRow.add(String.format("%d", resume.getId()));
        resumeRow.add(String.format("%s ", resume.getFirstname()));
        resumeRow.add(String.format("%s ", resume.getLastname()));
        resumeRow.add(String.format("%s ", resume.getMiddlename()));
        resumeRow.add(String.format("%s ", resume.getAddress()));
        resumeRow.add(String.format("%s ", resume.getDescription()));
        resumeRow.add(String.format("%s ", resume.getSkills()));
        resumeRow.add(String.format("%s ", resume.getPhone()));
        //resumeRow.add(String.format("%s ", resume.getBirthdate()));

        return resumeRow;
    }

    public static List<String> setUsersRow(User user) {
        List<String> usersRow = new LinkedList<String>();

        usersRow.add(String.format("%d", user.getId()));
        usersRow.add(String.format("%s ", user.getLogin()));
        usersRow.add(String.format("%s ", user.getRole().getName()));

        return usersRow;
    }

    public static ByteArrayOutputStream generateFeedbackInPDFById(int id) {
        Document document = new Document(PageSize.A6, 20, 20, 20, 20);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            Feedback feedback = DaoFactory.getInstance().getFeedbackDao().getFeedbackById(id);
            List<String> feedbackRow = setFeedbackRow(feedback);
            Paragraph orderNumber = new Paragraph();
            orderNumber.add(new Chunk("Feedback #", FONT_FOR_OBJECT_NAME));
            orderNumber.add(new Chunk(feedbackRow.get(0), COMMON_FONT));
            orderNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(orderNumber);
            document.add(Chunk.NEWLINE);
            Paragraph firstname = new Paragraph();
            firstname.add(new Chunk("First name: ", FONT_FOR_OBJECT_NAME));
            firstname.add(new Chunk(feedbackRow.get(1), COMMON_FONT));
            document.add(firstname);
            document.add(Chunk.NEWLINE);
            Paragraph lastname = new Paragraph();
            lastname.add(new Chunk("Last name: ", FONT_FOR_OBJECT_NAME));
            lastname.add(new Chunk(feedbackRow.get(2), COMMON_FONT));
            document.add(lastname);
            document.add(Chunk.NEWLINE);
            Paragraph middlename = new Paragraph();
            middlename.add(new Chunk("Middle name: ", FONT_FOR_OBJECT_NAME));
            middlename.add(new Chunk(feedbackRow.get(3), COMMON_FONT));
            document.add(middlename);
            document.add(Chunk.NEWLINE);
            Paragraph description = new Paragraph();
            description.add(new Chunk("Client description: ", FONT_FOR_OBJECT_NAME));
            description.add(new Chunk(feedbackRow.get(4), COMMON_FONT));
            document.add(description);
            document.add(Chunk.NEWLINE);
            Paragraph skills = new Paragraph();
            skills.add(new Chunk("Client skills: ", FONT_FOR_OBJECT_NAME));
            skills.add(new Chunk(feedbackRow.get(5), COMMON_FONT));
            document.add(skills);
            document.add(Chunk.NEWLINE);
            Paragraph message = new Paragraph();
            message.add(new Chunk("Client message: ", FONT_FOR_OBJECT_NAME));
            message.add(new Chunk(feedbackRow.get(6), COMMON_FONT));
            document.add(message);
            document.add(Chunk.NEWLINE);
            Paragraph time = new Paragraph();
            time.add(new Chunk("Date: ", FONT_FOR_OBJECT_NAME));
            time.add(new Chunk(feedbackRow.get(7), COMMON_FONT));
            document.add(time);
            document.add(Chunk.NEWLINE);

            Paragraph title = new Paragraph();
            title.add(new Chunk("Vacancy title: ", FONT_FOR_OBJECT_NAME));
            title.add(new Chunk(feedbackRow.get(8), COMMON_FONT));
            document.add(title);
            document.add(Chunk.NEWLINE);
            Paragraph vacancyDescription = new Paragraph();
            vacancyDescription.add(new Chunk("Vacancy description: ", FONT_FOR_OBJECT_NAME));
            vacancyDescription.add(new Chunk(feedbackRow.get(9), COMMON_FONT));
            document.add(vacancyDescription);
            document.add(Chunk.NEWLINE);
            Paragraph category = new Paragraph();
            category.add(new Chunk("Vacancy category: ", FONT_FOR_OBJECT_NAME));
            category.add(new Chunk(feedbackRow.get(10), COMMON_FONT));
            document.add(category);
            document.add(Chunk.NEWLINE);
            Paragraph vacancySkills = new Paragraph();
            vacancySkills.add(new Chunk("Vacancy skills: ", FONT_FOR_OBJECT_NAME));
            vacancySkills.add(new Chunk(feedbackRow.get(11), COMMON_FONT));
            document.add(vacancySkills);
            document.add(Chunk.NEWLINE);
            Paragraph salary = new Paragraph();
            salary.add(new Chunk("Vacancy salary: ", FONT_FOR_OBJECT_NAME));
            salary.add(new Chunk(feedbackRow.get(12), COMMON_FONT));
            document.add(salary);
            document.add(Chunk.NEWLINE);

            Paragraph status = new Paragraph();
            status.add(new Chunk("Status: ", FONT_FOR_OBJECT_NAME));
            status.add(new Chunk(feedbackRow.get(13), COMMON_FONT));
            document.add(status);
            document.add(Chunk.NEWLINE);


            /*Paragraph Items = new Paragraph();
            Items.add(new Chunk("Items: ", FONT_FOR_OBJECT_NAME));
            document.add(Items);
            document.add(Chunk.NEWLINE);
            for (int i = 3; i < ordersRow.size(); i++) {
                Paragraph Item = new Paragraph();
                Item.add(new Chunk("Vacancy #" + (i - 2) + ": ", FONT_FOR_OBJECT_NAME));
                Item.add(new Chunk(ordersRow.get(i), COMMON_FONT));
                document.add(Item);
                document.add(Chunk.NEWLINE);
            }*/

            document.addAuthor("Job Service Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateFeedbacksInXLS(List<Feedback> feedbackList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("feedback");
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont boldFont = workbook.createFont();
        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Feedback Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Client first name"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Client last name"));
        cell = row.createCell(3);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Client middle name"));
        cell = row.createCell(4);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Client description"));
        cell = row.createCell(5);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Client skills"));
        cell = row.createCell(6);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Client message"));
        cell = row.createCell(7);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Date"));

        cell = row.createCell(8);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Vacancy title"));
        cell = row.createCell(9);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Vacancy description"));
        cell = row.createCell(10);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Vacancy category"));
        cell = row.createCell(11);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Vacancy skills"));
        cell = row.createCell(12);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Vacancy salary"));
        cell = row.createCell(13);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Status"));

        /*cell = row.createCell(3);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Items"));*/

        sheet.autoSizeColumn(0);
        headerCellStyle.setWrapText(true);
        style.setWrapText(true);
        int[] columnWidths = {20, 20, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }

        List<String> feedbackRow = setFeedbackRow(feedbackList.get(0));
        for (int j = 0; j < feedbackRow.size(); j ++) {
            cell = row.createCell(j);
            cell.setCellStyle(style);
            HSSFRichTextString feedbackCellValue = new HSSFRichTextString(feedbackRow.get(j));
            cell.setCellValue(feedbackCellValue);
            sheet.autoSizeColumn(j);
            //sheet.setColumnWidth(j, columnWidths[j]);
        }

        /*for (int i = 0; i < feedbackList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> feedbackRow = setFeedbackRow(feedbackList.get(i));
            for (int j = 0; j < 4; j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString orderNumberCellValue;
                if (j != 3)
                    orderNumberCellValue = new HSSFRichTextString(ordersRow.get(j));
                else {
                    String result = "";
                    for (int k = 3; k < ordersRow.size(); k++) {
                        result += ordersRow.get(k) + "\n";
                    }
                    orderNumberCellValue = new HSSFRichTextString(result);
                }
                sheet.autoSizeColumn(j);
                cell.setCellValue(orderNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);
            }

        }*/

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateFeedbacksInCSV(List<Feedback> feedbackList) throws IOException {
        String[] fileHeader = {"Feedback Number", "Client first name", "Client las name", "Client middle name",
                "Client description", "Client skills", "Client message", "Date", "Vacancy title", "Vacancy description",
                "Vacancy category", "Vacancy skills", "Vacancy salary", "Status"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> feedbacksInString = new LinkedList<String[]>();
        for (int i = 0; i < feedbackList.size(); i++) {
            List<String> feedbackRow = setFeedbackRow(feedbackList.get(i));
            String[] tempArray = {feedbackRow.get(0), feedbackRow.get(1), feedbackRow.get(2),
                    feedbackRow.get(3), feedbackRow.get(4), feedbackRow.get(5),
                    feedbackRow.get(6), feedbackRow.get(7), feedbackRow.get(8),
                    feedbackRow.get(9), feedbackRow.get(10), feedbackRow.get(11),
                    feedbackRow.get(12), feedbackRow.get(13)};
            feedbacksInString.add(tempArray);
        }
        /*for (int i = 0; i < feedbackList.size(); i++) {
            List<String> feedbackRow = setFeedbackRow(feedbackList.get(i));

            String result = "";
            for (int k = 3; k < feedbackRow.size(); k++) {
                result += feedbackRow.get(k) + "\n";
            }

            String[] tempArray = {feedbackRow.get(0), feedbackRow.get(1), feedbackRow.get(2), result};
            feedbacksInString.add(tempArray);
        }*/

        writer.writeAll(feedbacksInString);
        writer.close();
        return stream;
    }

    public static ByteArrayOutputStream generateCategoriesInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            Category category = DaoFactory.getInstance().getCategoruDao().getCategoryById(id);
            List<String> categoriesRow = setCategoriesRow(category);

            Paragraph categoryNumber = new Paragraph();
            categoryNumber.add(new Chunk("Category #", FONT_FOR_OBJECT_NAME));
            categoryNumber.add(new Chunk(categoriesRow.get(0), COMMON_FONT));
            categoryNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(categoryNumber);
            document.add(Chunk.NEWLINE);
            Paragraph name = new Paragraph();
            name.add(new Chunk("Name: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(categoriesRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);
            document.addAuthor("Job Service Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateCategoriesInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("category");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Category Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Name"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<Category> categoriesList = DaoFactory.getInstance().getCategoruDao().getCategoriesList();
        for (int i = 0; i < categoriesList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> carsRow = setCategoriesRow(categoriesList.get(i));
            for (int j = 0; j < carsRow.size(); j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString orderNumberCellValue = new HSSFRichTextString(carsRow.get(j));

                cell.setCellValue(orderNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateCategoriesInCSV() throws IOException {
        String[] fileHeader = {"Category Number", "Name"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> categoriesInString = new LinkedList<String[]>();
        List<Category> categoriesList = DaoFactory.getInstance().getCategoruDao().getCategoriesList();
        for (int i = 0; i < categoriesList.size(); i++) {
            List<String> categoriesRow = setCategoriesRow(categoriesList.get(i));
            String[] tempArray = {categoriesRow.get(0), categoriesRow.get(1)};
            categoriesInString.add(tempArray);
        }

        writer.writeAll(categoriesInString);
        writer.close();
        return stream;
    }

    public static ByteArrayOutputStream generateVacancyInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            Vacancy Vacancy = DaoFactory.getInstance().getVacancyDao().getVacancyById(id);
            List<String> vacancyRow = setVacancyRow(Vacancy);

            Paragraph vacancyNumber = new Paragraph();
            vacancyNumber.add(new Chunk("Vacancy #", FONT_FOR_OBJECT_NAME));
            vacancyNumber.add(new Chunk(vacancyRow.get(0), COMMON_FONT));
            vacancyNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(vacancyNumber);
            document.add(Chunk.NEWLINE);

            Paragraph name = new Paragraph();
            name.add(new Chunk("Title: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(vacancyRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);

            Paragraph supplierName = new Paragraph();
            supplierName.add(new Chunk("Company name: ", FONT_FOR_OBJECT_NAME));
            supplierName.add(new Chunk(vacancyRow.get(2), COMMON_FONT));
            document.add(supplierName);
            document.add(Chunk.NEWLINE);

            Paragraph categoryName = new Paragraph();
            categoryName.add(new Chunk("Category name: ", FONT_FOR_OBJECT_NAME));
            categoryName.add(new Chunk(vacancyRow.get(3), COMMON_FONT));
            document.add(categoryName);
            document.add(Chunk.NEWLINE);

            Paragraph description = new Paragraph();
            description.add(new Chunk("Description: ", FONT_FOR_OBJECT_NAME));
            description.add(new Chunk(vacancyRow.get(4), COMMON_FONT));
            document.add(description);
            document.add(Chunk.NEWLINE);

            Paragraph email = new Paragraph();
            email.add(new Chunk("Email: ", FONT_FOR_OBJECT_NAME));
            email.add(new Chunk(vacancyRow.get(5), COMMON_FONT));
            document.add(email);
            document.add(Chunk.NEWLINE);

            Paragraph phone = new Paragraph();
            phone.add(new Chunk("Phone: ", FONT_FOR_OBJECT_NAME));
            phone.add(new Chunk(vacancyRow.get(6), COMMON_FONT));
            document.add(phone);
            document.add(Chunk.NEWLINE);

            Paragraph skills = new Paragraph();
            skills.add(new Chunk("Skills: ", FONT_FOR_OBJECT_NAME));
            skills.add(new Chunk(vacancyRow.get(7), COMMON_FONT));
            document.add(skills);
            document.add(Chunk.NEWLINE);

            Paragraph salary = new Paragraph();
            salary.add(new Chunk("Salary: ", FONT_FOR_OBJECT_NAME));
            salary.add(new Chunk(vacancyRow.get(8), COMMON_FONT));
            document.add(salary);
            document.add(Chunk.NEWLINE);

            document.addAuthor("Job Service Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateVacancysInXLS(List<Vacancy> vacancyList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Vacancy");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Vacancy Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Title"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Company name"));
        cell = row.createCell(3);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Category name"));
        cell = row.createCell(4);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Description"));
        cell = row.createCell(5);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Email"));
        cell = row.createCell(6);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Phone"));
        cell = row.createCell(7);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Skills"));
        cell = row.createCell(8);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Salary"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }

        for (int i = 0; i < vacancyList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> ItemsRow = setVacancyRow(vacancyList.get(i));
            for (int j = 0; j < ItemsRow.size(); j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString ItemNumberCellValue = new HSSFRichTextString(ItemsRow.get(j));

                cell.setCellValue(ItemNumberCellValue);
                sheet.autoSizeColumn(j);

                //sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateVacancysInCSV(List<Vacancy> vacancyList) throws IOException {
        String[] fileHeader = {"Vacancy Number", "Title", "Company name", "Category name", "Description",
                "Email", "Phone", "Skills", "Salary"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> vacancyInString = new LinkedList<String[]>();
        for (int i = 0; i < vacancyList.size(); i++) {
            List<String> ItemsRow = setVacancyRow(vacancyList.get(i));
            String[] tempArray = {ItemsRow.get(0), ItemsRow.get(1), ItemsRow.get(2), ItemsRow.get(3),
                    ItemsRow.get(4), ItemsRow.get(5), ItemsRow.get(6), ItemsRow.get(7), ItemsRow.get(8)};
            vacancyInString.add(tempArray);
        }

        writer.writeAll(vacancyInString);
        writer.close();
        return stream;
    }

    public static ByteArrayOutputStream generateUsersInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            User user = DaoFactory.getInstance().getUserDao().getUserById(id);
            List<String> usersRow = setUsersRow(user);

            Paragraph userNumber = new Paragraph();
            userNumber.add(new Chunk("User #", FONT_FOR_OBJECT_NAME));
            userNumber.add(new Chunk(usersRow.get(0), COMMON_FONT));
            userNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(userNumber);
            document.add(Chunk.NEWLINE);
            Paragraph name = new Paragraph();
            name.add(new Chunk("Login: ", FONT_FOR_OBJECT_NAME));
            name.add(new Chunk(usersRow.get(1), COMMON_FONT));
            document.add(name);
            document.add(Chunk.NEWLINE);
            Paragraph role = new Paragraph();
            role.add(new Chunk("Role: ", FONT_FOR_OBJECT_NAME));
            role.add(new Chunk(usersRow.get(2), COMMON_FONT));
            document.add(role);
            document.add(Chunk.NEWLINE);
            document.addAuthor("Job Service Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateUsersInXLS() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("user");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("User Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Login"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Role"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }
        List<User> userList = DaoFactory.getInstance().getUserDao().getUsersList();
        for (int i = 0; i < userList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> usersRow = setUsersRow(userList.get(i));
            for (int j = 0; j < usersRow.size(); j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString userNumberCellValue = new HSSFRichTextString(usersRow.get(j));

                cell.setCellValue(userNumberCellValue);
                sheet.autoSizeColumn(j);

                sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateUsersInCSV() throws IOException {
        String[] fileHeader = {"User Number", "Login", "Role"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> usersInString = new LinkedList<String[]>();
        List<User> userList = DaoFactory.getInstance().getUserDao().getUsersList();
        for (int i = 0; i < userList.size(); i++) {
            List<String> usersRow = setUsersRow(userList.get(i));
            String[] tempArray = {usersRow.get(0), usersRow.get(1), usersRow.get(2)};
            usersInString.add(tempArray);
        }

        writer.writeAll(usersInString);
        writer.close();
        return stream;
    }

    public static ByteArrayOutputStream generateResumeInPDFById(int id) {
        Document document = new Document(PageSize.A6, 30, 20, 20, 30);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = PdfWriter.getInstance(document, stream);
            pdfWriter.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
            document.open();
            addWaterMark(pdfWriter);
            Resume resume = DaoFactory.getInstance().getResumeDao().getResumeById(id);
            List<String> resumeRow = setResumeRow(resume);

            Paragraph resumeNumber = new Paragraph();
            resumeNumber.add(new Chunk("User #", FONT_FOR_OBJECT_NAME));
            resumeNumber.add(new Chunk(resumeRow.get(0), COMMON_FONT));
            resumeNumber.setAlignment(Element.ALIGN_CENTER);
            document.add(resumeNumber);
            document.add(Chunk.NEWLINE);
            Paragraph firstname = new Paragraph();
            firstname.add(new Chunk("First name: ", FONT_FOR_OBJECT_NAME));
            firstname.add(new Chunk(resumeRow.get(1), COMMON_FONT));
            document.add(firstname);
            document.add(Chunk.NEWLINE);

            Paragraph lastname = new Paragraph();
            lastname.add(new Chunk("Last name: ", FONT_FOR_OBJECT_NAME));
            lastname.add(new Chunk(resumeRow.get(2), COMMON_FONT));
            document.add(lastname);
            document.add(Chunk.NEWLINE);

            Paragraph middlename = new Paragraph();
            middlename.add(new Chunk("Middle name: ", FONT_FOR_OBJECT_NAME));
            middlename.add(new Chunk(resumeRow.get(3), COMMON_FONT));
            document.add(middlename);
            document.add(Chunk.NEWLINE);

            Paragraph address = new Paragraph();
            address.add(new Chunk("Address: ", FONT_FOR_OBJECT_NAME));
            address.add(new Chunk(resumeRow.get(4), COMMON_FONT));
            document.add(address);
            document.add(Chunk.NEWLINE);

            Paragraph description = new Paragraph();
            description.add(new Chunk("Description: ", FONT_FOR_OBJECT_NAME));
            description.add(new Chunk(resumeRow.get(5), COMMON_FONT));
            document.add(description);
            document.add(Chunk.NEWLINE);

            Paragraph skills = new Paragraph();
            skills.add(new Chunk("Skills: ", FONT_FOR_OBJECT_NAME));
            skills.add(new Chunk(resumeRow.get(6), COMMON_FONT));
            document.add(skills);
            document.add(Chunk.NEWLINE);

            Paragraph phone = new Paragraph();
            phone.add(new Chunk("Phone: ", FONT_FOR_OBJECT_NAME));
            phone.add(new Chunk(resumeRow.get(7), COMMON_FONT));
            document.add(phone);
            document.add(Chunk.NEWLINE);

            document.addAuthor("Job Service Systems");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            if (pdfWriter != null) {
                document.close();
            }
        }
        return stream;
    }

    public static ByteArrayOutputStream generateResumeInXLS(int id) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("user");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Resume Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("First name"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Last name"));
        cell = row.createCell(3);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Middle name"));
        cell = row.createCell(4);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Address"));
        cell = row.createCell(5);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Description"));
        cell = row.createCell(6);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Skills"));
        cell = row.createCell(7);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Phone"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }

        Resume resume = DaoFactory.getInstance().getResumeDao().getResumeById(id);
            row = sheet.createRow(1);
            row.setRowStyle(style);
            List<String> resumeRow = setResumeRow(resume);
            for (int j = 0; j < resumeRow.size(); j ++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString userNumberCellValue = new HSSFRichTextString(resumeRow.get(j));

                cell.setCellValue(userNumberCellValue);
                sheet.autoSizeColumn(j);

                //sheet.setColumnWidth(j, columnWidths[j]);
            }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateResumesInXLS(List<Resume> resumeList) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Resume");

        HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();

        HSSFFont boldFont = workbook.createFont();

        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Resume Number"));
        cell = row.createCell(1);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("First name"));
        cell = row.createCell(2);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Last name"));
        cell = row.createCell(3);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Middle name"));
        cell = row.createCell(4);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Address"));
        cell = row.createCell(5);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Description"));
        cell = row.createCell(6);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Skills"));
        cell = row.createCell(7);
        cell.setCellStyle(headerCellStyle);
        cell.setCellValue(new HSSFRichTextString("Phone"));
        sheet.autoSizeColumn(0);
        style.setWrapText(true);
        headerCellStyle.setWrapText(true);
        int[] columnWidths = {17, 10, 10, 20, 20};
        for (int i = 0; i < columnWidths.length; i++) {
            columnWidths[i] = columnWidths[i] * 256;
        }

        for (int i = 0; i < resumeList.size(); i++ ) {
            row = sheet.createRow(i+1);
            row.setRowStyle(style);
            List<String> ItemsRow = setResumeRow(resumeList.get(i));
            for (int j = 0; j < ItemsRow.size(); j++) {
                cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString ItemNumberCellValue = new HSSFRichTextString(ItemsRow.get(j));

                cell.setCellValue(ItemNumberCellValue);
                sheet.autoSizeColumn(j);

                //sheet.setColumnWidth(j, columnWidths[j]);

            }

        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        return  stream;
    }

    public static ByteArrayOutputStream generateResumeInCSV(int id) throws IOException {
        String[] fileHeader = {"User Number", "First name", "Last name", "Middle name", "Address", "Descriptiob", "Skills", "Phone"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);
        List<String[]> resumeInString = new LinkedList<String[]>();
        Resume resume = DaoFactory.getInstance().getResumeDao().getResumeById(id);
            List<String> resumeRow = setResumeRow(resume);
            String[] tempArray = {resumeRow.get(0), resumeRow.get(1), resumeRow.get(2), resumeRow.get(3),
            resumeRow.get(4), resumeRow.get(5), resumeRow.get(6), resumeRow.get(7)};
        resumeInString.add(tempArray);

        writer.writeAll(resumeInString);
        writer.close();
        return stream;
    }

    public static ByteArrayOutputStream generateResumesInCSV(List<Resume> resumeList) throws IOException {
        String[] fileHeader = {"User Number", "First name", "Last name", "Middle name", "Address", "Description", "Skills", "Phone"};
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(stream, Charset.forName("UTF-8")), ',');
        writer.writeNext(fileHeader);

        List<String[]> resumeInString = new LinkedList<String[]>();
        for (int i = 0; i < resumeList.size(); i++) {
            List<String> ItemsRow = setResumeRow(resumeList.get(i));
            String[] tempArray = {ItemsRow.get(0), ItemsRow.get(1), ItemsRow.get(2), ItemsRow.get(3),
                    ItemsRow.get(4), ItemsRow.get(5), ItemsRow.get(6), ItemsRow.get(7)};
            resumeInString.add(tempArray);
        }

        writer.writeAll(resumeInString);
        writer.close();
        return stream;
    }

}

