package com.d1l.util;

import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Resume;
import com.d1l.model.User;
import com.d1l.model.Vacancy;

import com.opensymphony.xwork2.validator.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private Validation() {}

    public static void validateNewUser(String login, String password, String repeatPassword) throws ValidationException {

        validateLogin(login);
        validatePassword(password);

        if (DaoFactory.getInstance().getUserDao().getUserByLogin(login) != null) {
            throw new ValidationException(ErrorList.LOGIN_EXISTS_MESSAGE);
        }

        if (!password.equals(repeatPassword)) {
            throw new ValidationException(ErrorList.PASSWORD_REPEAT_MESSAGE);
        }
    }

    public static void validateCategoryName(String categoryName) throws ValidationException {
        if (!validateStringByPattern(categoryName, "^[A-Za-z\\s]{1,100}$")) {
            throw new ValidationException(ErrorList.CATEGORY_NAME_INVALID_MESSAGE);
        }
    }

    public static void validateVacancy(Vacancy vacancy) throws ValidationException {
        if (!validateStringByPattern(vacancy.getTitle(), "^[A-Za-z\\s]{1,100}$")) {
            throw new ValidationException(ErrorList.VACANCY_TITLE_INVALID_MESSAGE);
        }
        if (vacancy.getSalary() < 0) {
            throw new ValidationException(ErrorList.VACANCY_SALARY_INVALID_MESSAGE);
        }
    }

    public static void validateResume(Resume resume) throws ValidationException {
        if (!validateStringByPattern(resume.getFirstname(), "^[A-Za-z\\s]{1,100}$")) {
            throw new ValidationException(ErrorList.RESUME_FIRSTNAME_INVALID_MESSAGE);
        }
        if (!validateStringByPattern(resume.getLastname(), "^[A-Za-z\\s]{1,100}$")) {
            throw new ValidationException(ErrorList.RESUME_LASTNAME_INVALID_MESSAGE);
        }
        if (!validateStringByPattern(resume.getMiddlename(), "^[A-Za-z\\s]{1,100}$")) {
            throw new ValidationException(ErrorList.RESUME_MIDDLENAME_INVALID_MESSAGE);
        }
    }

    public static void validateLoginUser(User user, String login, String password) throws ValidationException {
        if (user == null) {
            throw new ValidationException(ErrorList.LOGIN_NOT_EXISTS_MESSAGE);
        }
        if (!SecurityService.md5(password).equals(user.getPassword())) {
            throw new ValidationException(ErrorList.PASSWORD_INVALID_MESSAGE);
        }
    }

    public static void validateCustomer(String login, String password, String repeatPassword,
                                        String firstName, String lastName, String middleName)
                                        throws ValidationException {
        validateNewUser(login, password, repeatPassword);
        Resume resume = new Resume();
        resume.setFirstname(firstName);
        resume.setLastname(lastName);
        resume.setMiddlename(middleName);
        validateResume(resume);
    }

    public static void validateCompany(String login, String password,
                                       String repeatPassword, String companyName) throws ValidationException {
        validateNewUser(login, password, repeatPassword);

        if (!validateStringByPattern(companyName, "^[A-Za-z0-9\\s]{1,60}$")) {
            throw new ValidationException(ErrorList.COMPANY_NAME_INVALID_MESSAGE);
        }
    }

    private static boolean validateStringByPattern(String target, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    private static void validateLogin(String login) throws ValidationException {
        if (!validateStringByPattern(login, "^[A-Za-z0-9]{5,30}$")) {
            throw new ValidationException(ErrorList.LOGIN_INVALID_MESSAGE);
        }
    }

    private static void validatePassword(String password) throws ValidationException {
        if (!validateStringByPattern(password, "^[A-Za-z0-9@#$%*]{8,60}$")) {
            throw new ValidationException(ErrorList.PASSWORD_INVALID_MESSAGE);
        }
    }

}
