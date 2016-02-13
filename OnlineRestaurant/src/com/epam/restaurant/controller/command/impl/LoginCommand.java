package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.OrderService;
import com.epam.restaurant.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controls login user to site.
 */
public class LoginCommand implements Command {

    private static final String LOGIN= "login";
    private static final String PASSWORD= "password";
    private static final String USER= "user";
    private static final String ORDER= "order";

    private static final Logger LOGGER = Logger.getLogger( LoginCommand.class);

    //todo менеджеры не написаны

    /**
     * Provides work with database users table.
     */
    private UserService userService = new UserService();

    /**
     * Provides work with database orders table.
     */
    private OrderService orderService = new OrderService();//todo


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        String result = JspPageName.LOGIN_JSP;

        try {
            User user = userService.login(login,password);
            if(user!=null && user.getRole()!=User.Role.BLOCKED){
                result=JspPageName.HELLO_JSP;
                request.getSession().setAttribute(USER,user);

                Order order = orderService.getByUserId(user.getId());
                request.getSession().setAttribute(ORDER,order);
            }else{
                String path = "i18n.webstore";
                String curLan = (String) request.getSession().getAttribute("language");
                if (curLan != null && !curLan.equals("en"))
                    path += "_" + curLan;
                ResourceBundle rb = ResourceBundle.getBundle(path);
                if (user != null && user.getRole() == User.Role.BLOCKED) { // if user is blocked
                request.setAttribute("message", rb.getString("login.blocked"));
            } else {
                request.setAttribute("message", rb.getString("login.wrong"));
            }

            }
        } catch (Exception e) {
            throw new CommandException("Exception",e);
        }
        return JspPageName.HELLO_JSP;
    }
}
