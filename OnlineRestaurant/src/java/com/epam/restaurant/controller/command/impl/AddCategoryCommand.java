package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Add new category to menu by user whose role ADMIN.
 */
public class AddCategoryCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( AddCategoryCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        return null;
    }

}
