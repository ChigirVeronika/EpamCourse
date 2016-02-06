package com.epam.restaurant.controller;

import com.epam.restaurant.controller.command.CommandException;
import com.epam.restaurant.controller.command.CommandHelper;
import com.epam.restaurant.controller.command.ICommand;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.name.RequestParameterName;
import com.epam.restaurant.service.ServiceException;
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
public class MainController extends HttpServlet{
    public static final long serialVersionUID = 1;

    public MainController(){
        super();
    }

    private static final Logger LOGGER = Logger.getLogger( MainController.class);

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
        String commandName=request.getParameter(RequestParameterName.COMMAND_NAME);

        ICommand command = CommandHelper.getInstance().getCommand(commandName);

        String page;
        try {
            page = command.execute(request, response);
            //todo logger
            LOGGER.info("page: " + page);
        }catch (CommandException e){
            LOGGER.error(e);
            page = JspPageName.ERROR_JSP;
        }catch (Exception e){
            LOGGER.error(e);
            page = JspPageName.ERROR_JSP;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request,response);
    }
}
