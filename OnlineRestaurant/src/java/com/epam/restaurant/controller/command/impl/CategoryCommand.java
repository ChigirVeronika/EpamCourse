package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Control all dishes in one category output.
 */
public class CategoryCommand implements Command {

    /**
     * Service provides work with database (category table)
     */
    private static final CategoryService categoryService = CategoryService.getInstance();

    /**
     * Get from request category id. Get all categories and all dishes from concrete category.
     * Set them as attributes to request.
     * If everything is fine, return category page value.
     *
     * @return page to forward to
     * @throws CommandException if can't get categories ar dishes
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CATEGORY_JSP;

        try {
            Long categoryId = Long.parseLong(request.getParameter(ID));

            String name = categoryService.getById(categoryId).getName();
            List<Category> categoryList = categoryService.getAllCategories();
            List<Dish> dishList = categoryService.getAllFromCategory(categoryId);

            request.setAttribute(CATEGORIES, categoryList);

            if (dishList != null) {
                request.setAttribute(DISHES, dishList);
                request.setAttribute(NAME, name);
            }
        } catch (ServiceException e) {
            throw new CommandException("CategoryCommandException", e);
        }
        return result;
    }
}
