package com.epam.restaurant.filter;

import org.junit.Test;
import org.testng.annotations.BeforeMethod;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by Вероника on 01.03.2016.
 */
public class CharsetFilterTest {


    @BeforeMethod
    public void setUp() throws Exception {

    }

    @Test
    public void testDoFilter() throws Exception {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        // mock the getRequestURI() response
        when(httpServletRequest.getRequestURI()).thenReturn("/index.jsp");

        CharsetFilter filter = new CharsetFilter();
        filter.doFilter(httpServletRequest, httpServletResponse,
                filterChain);

        // verify if a sendRedirect() was performed with the expected value
        //verify(httpServletResponse).sendRedirect("/menu.jsp");


    }


}