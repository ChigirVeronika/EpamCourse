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
 * Change user's role to BLOCKED from USER
 * Handle 'Unban' button on ADMIN user page
 */
public class UnbanCommand implements Command {

    /**
     * Service provides work with database (user table)
     */
    private static final UserService userService = UserService.getInstance();

    /**
     * At first, check session expiration. If it's expired, return login page.
     * If not, get from request user and unblock it.
     * If everything is fine, return users page value.
     *
     * @return page to forward to
     * @throws CommandException if can't unban user
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.USERS_JSP;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }

        String userLogin = request.getParameter(LOGIN);
        try {
            User user = userService.get(userLogin);
            if (user != null) {
                user.setRole(User.Role.USER);
                userService.update(user);
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute UnbanCommand", e);
        }

        return result;
    }
}
