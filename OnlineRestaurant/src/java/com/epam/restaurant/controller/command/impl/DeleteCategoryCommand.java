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

/**
 * Created by Вероника on 21.02.2016.
 */
public class DeleteCategoryCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( DeleteCategoryCommand.class);

    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String result = JspPageName.MENU_JSP;
        try{
            String name = request.getParameter("name");

            Category category = categoryService.getByName(name);

            if(category!=null){
                categoryService.delete(category);
            }
        } catch (ServiceException e) {
            throw new CommandException("EditCategoryCommand Exception",e);
        }
        return result;
    }
}
