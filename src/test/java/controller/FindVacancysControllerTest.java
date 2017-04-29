package controller;

import com.d1l.controller.FindVacancysContoller;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FindVacancysControllerTest {

    private FindVacancysContoller controller;

    @Before
    public void setUp() throws Exception {
        controller = new FindVacancysContoller();
    }


    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS,controller.execute());
    }

}