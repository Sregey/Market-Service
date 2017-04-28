package dao;

import com.d1l.dao.ResumeDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class ResumeDAOTest {
    private ResumeDao resumeDao;
    private Resume resume;
    @Before
    public void setUp() throws Exception {
        resumeDao = DaoFactory.getInstance().getResumeDao();
        resume = new Resume();
        resume.setId(1);
        resume.setAddress("sdf");
        resume.setFirstname("sdf");
        resume.setLastname("sdff");
        resume.setCustomer(DaoFactory.getInstance().getCustomerDao().getCustomerById(1));
        resume.setDescription("234");
        resume.setMiddlename("sdf");
        resume.setPhone("87263");
        resume.setSkills("sdf");
    }

    @Test
    public void deleteOrder() throws Exception {
        resumeDao.deleteResume(1);
        assertNull(resumeDao.getResumeById(1));
    }
    @Test
    public void addOrUpdateOrder() throws Exception {
        resumeDao.addOrUpdateResume(resume);
        Resume newFeedback = resumeDao.getResumeById(resume.getId());
        assertEquals(newFeedback, resume);
    }

    @Test
    public void getVisitList() throws Exception {
        resumeDao.addOrUpdateResume(resume);
        assertNotNull(resumeDao.getResumeList());
    }

}
