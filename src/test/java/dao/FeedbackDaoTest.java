package dao;

import com.d1l.dao.FeedbackDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Feedback;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FeedbackDaoTest {
    private FeedbackDao feedbackDao;
    private Feedback feedback;

    @Before
    public void setUp() throws Exception {
        feedbackDao = DaoFactory.getInstance().getFeedbackDao();
        feedback = new Feedback();
        feedback.setId(2);
        feedback.setResume(DaoFactory.getInstance().getResumeDao().getResumeById(1));
        feedback.setDate(new Date());
    }

    @Test
    public void deleteOrder() throws Exception {
        feedbackDao.deleteFeedback(2);
        assertNull(feedbackDao.getFeedbackById(2));
    }
    @Test
    public void addOrUpdateOrder() throws Exception {
        feedbackDao.addOrUpdateFeedback(feedback);
        Feedback newFeedback = feedbackDao.getFeedbackById(feedback.getId());
        assertEquals(newFeedback, feedback);
    }

    @Test
    public void getVisitList() throws Exception {
        feedbackDao.addOrUpdateFeedback(feedback);
        assertNotNull(feedbackDao.getFeedbacksList());
    }
}