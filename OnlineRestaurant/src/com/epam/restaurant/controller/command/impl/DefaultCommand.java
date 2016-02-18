package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Control unhandled situations.
 */
public class DefaultCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return JspPageName.INDEX_JSP;

        //TODO изменить на error.jsp
    }
}