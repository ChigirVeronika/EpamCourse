package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.name.RequestParameterName;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.OrderService;
import com.epam.restaurant.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Controls login user to site.
 * Handle "login" button.
 */
public class LoginCommand implements Command {

    /**
     * Provides work with database users table.
     */
    private static final UserService userService = UserService.getInstance();

    /**
     * Provides work with database orders table.
     */
    private static final OrderService orderService = OrderService.getInstance();

    /**
     * Get from request login and password.
     * If user is not blocked or null, login him and get his order
     * and return hello page.
     * Else return login page again.
     *
     * @return page to forward to
     * @throws CommandException if can't login
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        String result = JspPageName.LOGIN_JSP;

        try {
            User user = userService.login(login, password);
            if (user != null && user.getRole() != User.Role.BLOCKED) {
                result = JspPageName.HELLO_JSP;
                request.getSession().setAttribute(USER, user);

                Order order = orderService.getByUserId(user.getId());
                request.getSession().setAttribute(ORDER, order);
            } else {
                String path = RequestParameterName.I18N;
                String curLan = (String) request.getSession().getAttribute(LANGUAGE);
                if (curLan != null && !curLan.equals(EN))
                    path += UNDERLINE + curLan;
                ResourceBundle rb = ResourceBundle.getBundle(path);
                if (user != null && user.getRole() == User.Role.BLOCKED) { // if user is blocked
                    request.setAttribute(MESSAGE, rb.getString(LOGIN_BLOCKED));
                } else {
                    request.setAttribute(MESSAGE, rb.getString(LOGIN_WRONG));
                }

            }
        } catch (Exception e) {
            throw new CommandException("Cant't execute LoginCommand", e);
        }
        return result;
    }
}
