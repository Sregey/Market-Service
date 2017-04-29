package controller;

import com.d1l.controller.customer.VacancysController;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VacancysControllerTest {
    private VacancysController controller;

    @Before
    public void setUp() throws Exception {

        controller = new VacancysController();
        controller.setVacancyId("2");
    }

    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS,controller.execute());
    }

}