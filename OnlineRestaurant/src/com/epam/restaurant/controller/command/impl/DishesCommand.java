package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.UserService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Control menu output.
 */
public class DishesCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger( DishesCommand.class);
    private static final CategoryService categoryService = CategoryService.getInstance();

    public DishesCommand(){}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String page=JspPageName.DISHES_JSP;
        try{
            List<Category> categoryList = categoryService.getAllCategories();
            if(categoryList!=null){
                request.setAttribute("categories",categoryList);
            }
        }catch (ServiceException e){
            LOGGER.error("DishesCommand Exception",e);
            throw new CommandException("DishesCommandException",e);
        }
        return page;
    }
}
