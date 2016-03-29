package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.DishService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Show page with concrete dish.
 */
public class DishCommand implements Command {
    private static final DishService dishService = DishService.getInstance();

    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.DISH_JSP;

        long dishId = Long.parseLong(request.getParameter(ID));
        try {
            List<Category> categoryList = categoryService.getAllCategories();
            Dish dish = dishService.getById(dishId);

            request.setAttribute(DISH, dish);
            request.setAttribute(CATEGORIES, categoryList);

        } catch (ServiceException e) {
            throw new CommandException("Cant't execute DishCommand", e);
        }

        return result;
    }
}
