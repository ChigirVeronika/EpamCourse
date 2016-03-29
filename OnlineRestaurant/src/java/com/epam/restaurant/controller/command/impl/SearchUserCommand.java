package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.UserService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.restaurant.util.SessionUtil.*;
import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Search user by login for user with role ADMIN.
 */
public class SearchUserCommand implements Command {

    private static final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.USERS_JSP;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }

        String login = request.getParameter(LOGIN);
        try {
            User user = userService.get(login);
            if (user != null) {
                request.setAttribute(FOUNDED_USER, user);
            } else {
                request.setAttribute(MESSAGE, NOT_FOUND);
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute SearchUserCommand", e);
        }

        return result;
    }
}
