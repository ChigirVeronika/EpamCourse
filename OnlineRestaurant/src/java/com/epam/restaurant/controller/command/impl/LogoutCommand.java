package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Control user logout from site.
 * Handle "logout" button.
 */
public class LogoutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    public LogoutCommand(){}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession currentSession = request.getSession(false);
        if (currentSession != null)
            currentSession.invalidate();
        return JspPageName.INDEX_JSP;
    }
}
