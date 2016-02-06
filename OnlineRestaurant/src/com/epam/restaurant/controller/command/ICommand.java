package com.epam.restaurant.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for business logic classes.
 */
public interface ICommand {
    String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException;
}
