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

/**
 * Control menu output.
 */
public class MenuCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger( MenuCommand.class);
    private static final CategoryService categoryService = CategoryService.getInstance();

    public MenuCommand(){}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page=JspPageName.DISHES_JSP;
        try{
            List<Category> categoryList = categoryService.getAllCategories();
            if(categoryList!=null){
                request.setAttribute("categories",categoryList);
            }
        }catch (ServiceException e){
            LOGGER.error("Can't do CategoryService in MenuCommand ",e);
            throw new CommandException("MenuCommandException ",e);
        }
        return page;
    }
}
