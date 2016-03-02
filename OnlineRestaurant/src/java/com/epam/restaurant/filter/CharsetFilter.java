package com.epam.restaurant.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Вероника on 23.02.2016.
 */
public class CharsetFilter implements Filter {
    private String encoding;
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding=filterConfig.getInitParameter("characterEncoding");
        context=filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);

//        context.log("Charset was set successfully.");
       // System.out.println("Charset was set successfully.");

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
