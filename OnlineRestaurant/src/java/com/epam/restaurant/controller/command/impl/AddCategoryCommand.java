package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Add new category to menu by user whose role ADMIN.
 */
public class AddCategoryCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddCategoryCommand.class);

    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String result = JspPageName.CONCRETE_MENU_JSP;

        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            categoryService.create(name, description);
        } catch (ServiceException e) {
            throw new CommandException("");
        }

        return result;
    }

}
