package com.epam.restaurant.controller.servlet;

import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.command.CommandHelper;
import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.name.RequestParameterName;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main HTTP servlet control all actions in system.
 */
public class MainController extends HttpServlet {
    public static final long serialVersionUID = 1;

    public MainController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handle post and get http requests.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(RequestParameterName.UTF8);
        String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);

        Command command = CommandHelper.getInstance().getCommand(commandName);

        String page;
        try {
            page = command.execute(request, response);
        } catch (CommandException e) {
            page = JspPageName.ERROR_JSP;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);

        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        }
    }
}
