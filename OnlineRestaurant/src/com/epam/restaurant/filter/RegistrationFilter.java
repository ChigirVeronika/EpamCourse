package com.epam.restaurant.filter;

import com.epam.restaurant.util.ResourceBundleUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Вероника on 23.02.2016.
 */
public class RegistrationFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String command = servletRequest.getParameter("command");

        if(command!=null && command.equals("register_comand")){
            HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
            String currentLanguage = (String) httpServletRequest.getSession().getAttribute("language");

            ResourceBundle rb = ResourceBundleUtil.getResourceBundle(currentLanguage);

            // get registration params
            String name = servletRequest.getParameter("name");
            String surname = servletRequest.getParameter("surname");
            String login = servletRequest.getParameter("login");
            String password = servletRequest.getParameter("password");
            String email = servletRequest.getParameter("email");
            String payCard = servletRequest.getParameter("pay_card");
            boolean isValid = true;

            // check validation of every param
            if  (name.length() < 2 || name.length() > 25) {
                servletRequest.setAttribute("login_message", rb.getString("register.login.error"));
                isValid = false;
            }

            if  (surname.length() < 2 || surname.length() > 25) {
                servletRequest.setAttribute("login_message", rb.getString("register.login.error"));
                isValid = false;
            }

            if  (login.length() < 3 || login.length() > 25) {
                servletRequest.setAttribute("login_message", rb.getString("register.login.error"));
                isValid = false;
            }

            if (email.length() < 3 && email.length() > 25 || email.indexOf('@') < 2) {
                servletRequest.setAttribute("mail_message", rb.getString("register.mail.error"));
                isValid = false;
            }

            if (password.length() < 5 || password.length() > 16) {
                servletRequest.setAttribute("password_message", rb.getString("register.password.error"));
                isValid = false;
            }

            if  (payCard.length() !=16) {
                servletRequest.setAttribute("login_message", rb.getString("register.login.error"));
                isValid = false;
            }


            if (isValid) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("register.jsp");
                requestDispatcher.forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
