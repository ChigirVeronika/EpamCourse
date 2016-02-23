package com.epam.restaurant.filter;

import com.epam.restaurant.util.ResourceBundleUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Вероника on 23.02.2016.
 */
public class SessionFilter implements Filter {
    private String expiredUrl;

    private ServletContext context;
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        context=filterConfig.getServletContext();
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String command = servletRequest.getParameter("command");

        if(command!=null){
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            String currentLanguage = (String) httpServletRequest.getSession().getAttribute("language");

            ResourceBundle rb = ResourceBundleUtil.getResourceBundle(currentLanguage);

            boolean isValid = true;

            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

            HttpSession session = httpRequest.getSession(false);
            if(session==null && httpRequest.getRequestedSessionId()!=null &&
                    !httpRequest.isRequestedSessionIdValid()){

                servletRequest.setAttribute("sessionMessage", rb.getString("login.session.error"));
                isValid = false;

                //String targetUrl = httpRequest.getContextPath()+expiredUrl;
//                httpResponse.sendRedirect(httpResponse.encodeRedirectURL("/login.jsp"));//targetUrl
            }

            if (isValid) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("login.jsp");
                if(requestDispatcher!=null){
                    requestDispatcher.forward(servletRequest, servletResponse);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }

    public void setExpiredUrl(String expiredUrl) {
        this.expiredUrl = expiredUrl;
    }
}
