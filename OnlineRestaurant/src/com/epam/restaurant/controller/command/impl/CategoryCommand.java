package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Control all dishes in one category output.
 */
public class CategoryCommand implements Command{

    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CATEGORY_JSP;

        try{
            int categoryId = Integer.parseInt(request.getParameter("id"));

            String name = categoryService.getById(categoryId).getName();
            List<Category> categoryList = categoryService.getAllCategories();

        } catch (ServiceException e) {
            throw new CommandException("CategoryException",e);
        }
        return result;
    }
}
