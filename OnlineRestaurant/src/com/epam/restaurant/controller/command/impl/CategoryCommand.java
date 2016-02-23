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

/**
 * Control all dishes in one category output.
 */
public class CategoryCommand implements Command{

    private static final CategoryService categoryService = CategoryService.getInstance();
    private static final Logger LOGGER = Logger.getLogger( CategoryCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CATEGORY_JSP;

        try{
            int categoryId = Integer.parseInt(request.getParameter("id"));

            String name = categoryService.getById(categoryId).getName();
            LOGGER.info(name);
            List<Category> categoryList = categoryService.getAllCategories();
            LOGGER.info(categoryList);
            List<Dish> dishList = categoryService.getAllFromCategory(categoryId);
            LOGGER.info(dishList);

            request.setAttribute("categories",categoryList);

            if(dishList!=null){
                request.setAttribute("dishes",dishList);
                request.setAttribute("name",name);
            }
        } catch (ServiceException e) {
            LOGGER.error("Can't do CategoryService in CategoryCommand",e);
            throw new CommandException("CategoryCommandException",e);
        }
        return result;
    }
}
