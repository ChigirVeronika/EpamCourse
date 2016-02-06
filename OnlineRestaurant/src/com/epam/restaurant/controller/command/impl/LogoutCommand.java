package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.command.CommandException;
import com.epam.restaurant.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Вероника on 04.02.2016.
 */
public class LogoutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        //todo

        return JspPageName.LOGOUT_JSP;
    }
}
