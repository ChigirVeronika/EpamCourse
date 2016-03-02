package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Вероника on 02.03.2016.
 */
public class LogoutCommandTest {

    @Test
    public void testExecute() throws Exception {
        LogoutCommand command = new LogoutCommand();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String result = JspPageName.INDEX_JSP;
        String real = command.execute(request, response);
        assertEquals(real,result);
    }
}