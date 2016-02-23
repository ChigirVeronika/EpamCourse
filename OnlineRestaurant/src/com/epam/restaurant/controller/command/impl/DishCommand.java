package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return null;
    }
}
