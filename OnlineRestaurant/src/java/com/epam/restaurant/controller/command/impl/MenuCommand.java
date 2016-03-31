package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Control menu output.
 */
public class MenuCommand implements Command {

    /**
     * Service provides work with database (category table)
     */
    private static final CategoryService categoryService = CategoryService.getInstance();

    /**
     * Set all categories to request.
     * If everything is fine, return menu page value.
     *
     * @return page to forward to
     * @throws CommandException if can't get all categories
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page = JspPageName.MENU_JSP;
        try {
            List<Category> categoryList = categoryService.getAllCategories();
            if (categoryList != null) {
                request.setAttribute(CATEGORIES, categoryList);
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute MenuCommand", e);
        }
        return page;
    }
}
