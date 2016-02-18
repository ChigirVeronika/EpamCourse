package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Control menu output.
 */
public class DishesCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger( DishesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
//        List<Object> info = DishService.getAllDishes(request.getParameter(RequestParameterName.FILE_NAME));
//        request.setAttribute(RequestParameterName.SIMPLE_INFO, info);
        String page= JspPageName.DISHES_JSP;
//        LOGGER.info("dishes command executed");
        return page;
    }
}
