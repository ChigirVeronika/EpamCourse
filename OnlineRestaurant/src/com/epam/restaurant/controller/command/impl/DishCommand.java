package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.DishService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Вероника on 21.02.2016.
 */
public class DishCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( DishCommand.class);

    private static final DishService dishService = DishService.getInstance();

    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.DISH_JSP;
        return "";
    }
}
