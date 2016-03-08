package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.DishService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import static com.epam.restaurant.util.SessionUtil.*;
/**
 * Add new dish to menu by user whose role ADMIN.
 */
public class AddDishCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger( AddDishCommand.class);

    private static final CategoryService categoryService = CategoryService.getInstance();

    private static final DishService dishService = DishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CONCRETE_CATEGORY_JSP;

        if(sessionExpired(request)){
            result=JspPageName.LOGIN_JSP;
            return result;
        }

        try{
            String categoryName=request.getParameter("category");
            Category category = categoryService.getByName(categoryName);
            if(category!=null){
                result+=category.getId();
                String dishName = request.getParameter("name");
                String dishDescription = request.getParameter("description");
                String dishIngredients = request.getParameter("ingredients");
                BigDecimal dishPrice = new BigDecimal(request.getParameter("price"));
                Integer dishQuantity = Integer.parseInt(request.getParameter("quantity"));
                String dishImage = request.getParameter("image");

                dishService.create(dishName,dishDescription,dishIngredients,
                        dishPrice,dishQuantity,category.getId(),dishImage);
            }else {
                result=JspPageName.CONCRETE_MENU_JSP;
            }

        } catch (ServiceException e) {
            throw new CommandException("Cant't execute AddDishCommand");
        }

        return result;
    }
}
