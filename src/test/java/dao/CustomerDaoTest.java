package dao;

import com.d1l.dao.CustomerDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.dao.impl.CustomerDaoImpl;
import com.d1l.dao.impl.UserDaoImpl;
import com.d1l.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CustomerDaoTest {
    private Customer customer;
    private CustomerDao customerDao;

    private static final String CUSTOMER_FIRSTNAME = "testFName";
    private static final String CUSTOMER_MIDDLENAME = "testMNamme";
    private static final String CUSTOMER_LASTNAME = "testLName";
    private static final int CUSTOMER_ID = 101112;
    private static final int USER_ID = 1;

    @Before
    public void setUp() throws Exception {
        customerDao = DaoFactory.getInstance().getCustomerDao();
        customer = new Customer();
        customer.setFirstname(CUSTOMER_FIRSTNAME);
        customer.setMiddlename(CUSTOMER_MIDDLENAME);
        customer.setLastname(CUSTOMER_LASTNAME);
        customer.setUser(DaoFactory.getInstance().getUserDao().getUserById(USER_ID));
    }

    @Test
    public void deleteCustomer() throws Exception {
        customerDao.deleteCustomer(CUSTOMER_ID);
        assertNull(customerDao.getCustomerById(CUSTOMER_ID));
    }

    @Test
    public void addOrUpdateCustomer() throws Exception {
        customerDao.addOrUpdateCustomer(customer);
        Customer newCustomer = customerDao.getCustomerById(CUSTOMER_ID);
        assertEquals(newCustomer, customer);
    }

    @Test
    public void getCustomerTest() throws Exception {
        customerDao.addOrUpdateCustomer(customer);
        assertNotNull(customerDao.getCustomersList());
    }
}