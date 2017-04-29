package dao;

import com.d1l.dao.StatusDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ananas on 28.04.2017.
 */
public class StatusDaoTest {
    private StatusDao statusDao;
    private Status status;
    @Before
    public void setUp() throws Exception {
        statusDao = DaoFactory.getInstance().getStatusDao();
        status = new Status();
        status.setName("testStatus");
    }

    @Test
    public void addStatus() throws Exception {
        statusDao.addOrUpdateStatus(status);
        Status addedStatus = statusDao.getStatusByName(status.getName());
        assertEquals(addedStatus, status);
    }
}
