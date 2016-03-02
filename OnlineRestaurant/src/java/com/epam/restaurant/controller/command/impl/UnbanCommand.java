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

/**
 * Change user's role to BLOCKED from USER
 * Handle 'Unban' button on ADMIN user page
 */
public class UnbanCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( UnbanCommand.class);

    private static final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.USERS_JSP;

        String userLogin = request.getParameter(LOGIN);
        try{
            User user = userService.get(userLogin);
            if(user!=null){
                user.setRole(User.Role.USER);
                userService.update(user);
            }
        } catch (ServiceException e) {
            LOGGER.error("Can't do UserService in UndanCommand ",e);
            throw new CommandException("UnbanCommand Exception",e);
        }

        return result;
    }
}
