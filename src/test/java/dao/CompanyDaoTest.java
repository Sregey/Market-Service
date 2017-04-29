package dao;

import com.d1l.dao.CompanyDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Company;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CompanyDaoTest {
    private static final String COMPANY_NAME = "testCompany";
    private static final int COMPANY_ID = 105111;
    private static final int USER_ID = 1;

    private CompanyDao companyDao;
    private Company company;

    @Before
    public void setUp() throws Exception {
        companyDao = DaoFactory.getInstance().getCompanyDao();
        company = new Company();
        company.setId(COMPANY_ID);
        company.setCompanyName(COMPANY_NAME);
        company.setUser(DaoFactory.getInstance().getUserDao().getUserById(USER_ID));
    }

    @Test
    public void deleteSupplier() throws Exception {
        companyDao.deleteCompany(COMPANY_ID);
        assertNull(companyDao.getCompanyById(COMPANY_ID));
    }

    @Test
    public void addOrUpdateSupplier() throws Exception {
        companyDao.addOrUpdateCompany(company);
        Company newCompany = companyDao.getCompanyById(COMPANY_ID);
        assertEquals(newCompany, company);
    }

    @Test
    public void getSupplierTest() throws Exception {
        companyDao.addOrUpdateCompany(company);
        assertNotNull(companyDao.getCompanyList());
    }
}