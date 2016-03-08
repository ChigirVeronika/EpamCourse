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
import static com.epam.restaurant.util.SessionUtil.*;
/**
 * Delete category from category list by user with role ADMIN.
 */
public class DeleteCategoryCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( DeleteCategoryCommand.class);

    private static final CategoryService categoryService = CategoryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String result = JspPageName.CONCRETE_MENU_JSP;

        if(sessionExpired(request)){
            result=JspPageName.LOGIN_JSP;
            return result;
        }
        try{
            String name = request.getParameter("name");

            Category category = categoryService.getByName(name);

            if(category!=null){
                //if there dishes of this category
                List<Dish> dishList = categoryService.getAllFromCategory(category.getId());
                if(dishList!=null){
                    result=JspPageName.ERROR_JSP;
                    return result;
                }else{
                    categoryService.delete(category);
                }
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute EditCategoryCommand");
        }
        return result;
    }
}
