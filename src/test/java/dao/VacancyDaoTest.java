package dao;

import com.d1l.dao.VacancyDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.dao.impl.CategoryDaoImpl;
import com.d1l.dao.impl.VacancyDaoImpl;
import com.d1l.dao.impl.CompanyDaoImpl;
import com.d1l.model.Vacancy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;

public class VacancyDaoTest {
    private VacancyDao vacancyDao;
    private Vacancy vacancy;

    @Before
    public void setUp() throws Exception {
        vacancyDao = DaoFactory.getInstance().getVacancyDao();
        vacancy = new Vacancy();
        vacancy.setId(2);
        vacancy.setTitle("title");
        vacancy.setDescription("description");
        vacancy.setSkills("skills");
        vacancy.setEmail("email");
        vacancy.setPhone("phone");
        vacancy.setCompany(DaoFactory.getInstance().getCompanyDao().getCompanyById(1));
        vacancy.setCategory(DaoFactory.getInstance().getCategoruDao().getCategoryById(1));
        vacancy.setId(2);
    }

    @Test
    public void deleteItem() throws Exception {
        vacancyDao.deleteVacancy(2);
        assertNull(vacancyDao.getVacancyById(2));
    }

    @Test
    public void addOrUpdateItem() throws Exception {
        vacancyDao.addOrUpdateVacancy(vacancy);
        Vacancy newVacancy = vacancyDao.getVacancyById(vacancy.getId());
        assertEquals(newVacancy, vacancy);
    }

    @Test
    public void getItemTest() throws Exception {
        vacancyDao.addOrUpdateVacancy(vacancy);
        assertNotNull(vacancyDao.getVacancyList());
    }
}