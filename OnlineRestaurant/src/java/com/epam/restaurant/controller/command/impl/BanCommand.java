package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.UserService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.restaurant.controller.name.RequestParameterName.*;
import static com.epam.restaurant.util.SessionUtil.*;

/**
 * Change user's role to BLOCKED
 * Handle 'Ban' button on the ADMIN user page
 */
public class BanCommand implements Command {

    /**
     * Service provides work with database (user table)
     */
    private static final UserService userService = UserService.getInstance();

    /**
     * At first, check session expiration. If it's expired, return login page.
     * If not, get from request user and block it.
     * If everything is fine, return users page value.
     *
     * @return page to forward to
     * @throws CommandException if can't ban user
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.USERS_JSP;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }

        try {
            String userLogin = request.getParameter(LOGIN);
            User user = userService.get(userLogin);
            if (user != null) {
                System.out.println(user.toString());
                user.setRole(User.Role.BLOCKED);
                userService.update(user);
            }
        } catch (ServiceException e) {
            throw new CommandException("BanCommand Exception", e);
        }

        return result;
    }
}
