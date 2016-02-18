package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.UserService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Change user's role to BLOCKED
 * Handle 'Ban' button on the ADMIN user page
 */
public class BanCommand implements Command {
    private static final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.USERS_JSP;

        try {
            String userLogin = request.getParameter(LOGIN);
            User user = userService.get(userLogin);
            if(user!=null){
                user.setRole(User.Role.BLOCKED);
                userService.update(user);
            }
        } catch (ServiceException e) {
            throw new CommandException("BanCommand Exception");
        }

        return result;
    }
}
