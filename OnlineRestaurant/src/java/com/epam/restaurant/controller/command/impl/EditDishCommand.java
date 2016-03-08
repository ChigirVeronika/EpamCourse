package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.service.DishService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import static com.epam.restaurant.util.SessionUtil.*;
/**
 * Change dish parameters by user with role ADMIN.
 */
public class EditDishCommand implements Command {

    private static final DishService dishService  = DishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CONCRETE_CATEGORY_JSP;

        if(sessionExpired(request)){
            result=JspPageName.LOGIN_JSP;
            return result;
        }

        try{
            Long dishId = Long.parseLong(request.getParameter("dish_id"));
            Dish dish = dishService.getById(dishId);

            if(dish!=null){
                result+=dish.getCategoryId();
                String dishName = request.getParameter("name");
                String dishDescription = request.getParameter("description");
                String dishIngredients = request.getParameter("ingredients");
                BigDecimal dishPrice = new BigDecimal(request.getParameter("price"));
                Integer dishQuantity = Integer.parseInt(request.getParameter("quantity"));
                String dishImage = request.getParameter("image");

                dish.setName(dishName);
                dish.setDescription(dishDescription);
                dish.setIngredients(dishIngredients);
                dish.setPrice(dishPrice);
                dish.setQuantity(dishQuantity);
                dish.setImage(dishImage);

                dishService.update(dish);
            }else {
                result=JspPageName.CONCRETE_MENU_JSP;
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute EditDishCommand");
        }

        return result;
    }
}
