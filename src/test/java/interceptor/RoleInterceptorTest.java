package interceptor;

import com.d1l.interceptor.RoleInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RoleInterceptorTest {
    private RoleInterceptor interceptor;
    private String listRole;

    @Before
    public void setUp() throws Exception {
        interceptor = new RoleInterceptor();
        listRole = "Admin,Customer";
    }

    @Test
    public void handleRejection() throws Exception {
        assertEquals("invalidAdminAccess", interceptor.handleRejection(null,null));
    }

    @Test
    public void stringToList() throws Exception {
        assertNotEquals(Collections.emptyList(), interceptor.stringToList(listRole));
        assertEquals(Collections.emptyList(), interceptor.stringToList(null));
        assertEquals(Collections.emptyList(), interceptor.stringToList(""));
    }

}