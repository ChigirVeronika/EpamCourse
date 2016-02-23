package com.epam.restaurant.filter;

import com.epam.restaurant.util.ResourceBundleUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Filter to check fields on login page before execute login command
 * If the field are filled correctly - execute command
 * If not - returns error message to user (to jsp)
 */
public class LoginFilter implements Filter{
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //get current command
        String command = servletRequest.getParameter("command");

        if(command!=null && command.equals("login_command")){

            // get resource bundle for current language
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            String currentLanguage = (String) httpServletRequest.getSession().getAttribute("language");
            ResourceBundle rb = ResourceBundleUtil.getResourceBundle(currentLanguage);

            // get login params
            String login = servletRequest.getParameter("login");
            String password = servletRequest.getParameter("password");

            if(login == null || login.length()==0 || password==null || password.length()==0){
                servletRequest.setAttribute("message",rb.getString("login.message"));
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("login.jsp");
                if(requestDispatcher!=null){
                    requestDispatcher.forward(servletRequest,servletResponse);
                    return;
                } //else{}
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }

        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
