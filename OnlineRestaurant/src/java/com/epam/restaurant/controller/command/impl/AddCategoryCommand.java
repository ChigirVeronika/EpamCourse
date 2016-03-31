package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.name.RequestParameterName;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.restaurant.util.SessionUtil.*;

/**
 * Add new category to menu by user whose role ADMIN.
 */
public class AddCategoryCommand implements Command {

    /**
     * Service provides work with database (category table)
     */
    private static final CategoryService categoryService = CategoryService.getInstance();

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }

        result = JspPageName.CONCRETE_MENU_JSP;

        try {
            String name = request.getParameter(RequestParameterName.NAME);
            String description = request.getParameter(RequestParameterName.DESCRIPTION);

            categoryService.create(name, description);
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute AddCategoryCommand", e);
        }

        return result;
    }

}
