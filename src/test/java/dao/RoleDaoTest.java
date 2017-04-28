package dao;

import com.d1l.dao.RoleDao;
import com.d1l.dao.factory.DaoFactory;
import com.d1l.model.Role;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;

public class RoleDaoTest {
    private RoleDao roleDao;
    private Role role;

    private static final int ROLE_ID = 1;
    private static final String ROLE_NAME = "bla1234123412341234";

    @Before
    public void setUp() throws Exception {
        roleDao = DaoFactory.getInstance().getRoleDao();
        role = new Role();
        role.setId(ROLE_ID);
        role.setName(ROLE_NAME);
    }

    @Test
    public void getRoleList() throws Exception {
        roleDao.addOrUpdateRole(role);
        assertNotNull(roleDao.getRolesList());
    }

    @Test
    public void deleteRole() throws Exception {
        roleDao.deleteRole(ROLE_ID);
        assertNull(roleDao.getRoleById(ROLE_ID));
    }

    @Test
    public void addOrUpdateRole() throws Exception {
        roleDao.addOrUpdateRole(role);
        Role newRole = roleDao.getRoleById(role.getId());
        assertEquals(newRole, role);
    }

}