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
 * Change category parameters by user with role ADMIN.
 */
public class EditCategoryCommand  implements Command {
    private static final Logger LOGGER = Logger.getLogger( EditCategoryCommand.class);

    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String result = JspPageName.CONCRETE_MENU_JSP;
        try{
            String name = request.getParameter("old_name");
            System.out.println(name);
            String newName = request.getParameter("name");
            System.out.println(newName);
            String newDescription = request.getParameter("description");
            System.out.println(newDescription);

            Category category = categoryService.getByName(name);

            if(category!=null){
                category.setName(newName);
                category.setDescription(newDescription);
                categoryService.update(category);
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute EditCategoryCommand");
        }
        return result;
    }
}
