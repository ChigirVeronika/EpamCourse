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
/**
 *
 */
public class SearchUserCommand implements Command {

    private static final UserService userService = UserService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.USERS_JSP;

        if(sessionExpired(request)){
            result=JspPageName.LOGIN_JSP;
            return result;
        }

        String login = request.getParameter("login");
        try{
            User user = userService.get(login);
            if(user!=null){
                request.setAttribute("founded_user",user);
            }else {
                request.setAttribute("message","not found");
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute SearchUserCommand");
        }

        return result;
    }
}
