package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.command.CommandException;
import com.epam.restaurant.controller.command.ICommand;
import com.epam.restaurant.service.OrderService;
import com.epam.restaurant.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controls login user to site.
 */
public class LoginCommand implements ICommand {

    private static final String LOGIN= "login";
    private static final String PASSWORD= "password";

    //todo менеджеры не написаны

    /**
     * Provides work with database users table.
     */
    private UserService userService = new UserService();

    /**
     * Provides work with database orders table.
     */
    private OrderService orderService = new OrderService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        String result = JspPageName.LOGIN_JSP;

        //try {
          //  User user = userService.login
        //}
        return JspPageName.HELLO_JSP;
    }
}
