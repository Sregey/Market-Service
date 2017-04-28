package dao;

import com.d1l.dao.UserDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.dao.impl.RoleDaoImpl;
import com.d1l.dao.impl.UserDaoImpl;
import com.d1l.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;

public class UserDaoTest {
    private UserDao userDao;
    private User user;

    @Before
    public void setUp() throws Exception {
        userDao = DaoFactory.getInstance().getUserDao();
        user = new User();
        user.setRole(DaoFactory.getInstance().getRoleDao().getRoleById(1));
        user.setLogin("asdasdsa");
        user.setPassword("sdadssadas");
    }

    @Test
    public void deleteUser() throws Exception {
        userDao.deleteUser(2);
        assertNull(userDao.getUserById(2));
    }

    @Test
    public void addOrUpdateUser() throws Exception {
        userDao.addOrUpdateUser(user);
        User newUser = userDao.getUserById(user.getId());
        assertEquals(newUser, user);
    }

    @Test
    public void getUserTest() throws Exception {
        userDao.addOrUpdateUser(user);
        assertNotNull(userDao.getUsersList());
    }
}