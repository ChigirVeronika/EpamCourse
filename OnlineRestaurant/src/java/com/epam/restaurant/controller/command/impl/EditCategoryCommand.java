package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.restaurant.util.SessionUtil.*;
import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Change category parameters by user with role ADMIN.
 */
public class EditCategoryCommand implements Command {

    /**
     * Service provides work with database (category table)
     */
    private static final CategoryService categoryService = CategoryService.getInstance();

    /**
     * At first, check session expiration. If it's expired, return login page.
     * If not, get from request old and new names and description.
     * Get current category abd update it.
     * If everything is fine, return menu page value.
     *
     * @return page to forward to
     * @throws CommandException if can't update category
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String result = JspPageName.CONCRETE_MENU_JSP;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }

        try {
            String name = request.getParameter(OLD_NAME);
            String newName = request.getParameter(NAME);
            String newDescription = request.getParameter(DESCRIPTION);

            Category category = categoryService.getByName(name);

            if (category != null) {
                category.setName(newName);
                category.setDescription(newDescription);
                categoryService.update(category);
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute EditCategoryCommand", e);
        }
        return result;
    }
}
