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

/**
 * Change dish parameters by user with role ADMIN.
 */
public class EditDishCommand implements Command {

    private static final DishService dishService  = DishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CONCRETE_CATEGORY_JSP;

        try{
            System.out.println("try to edit");
            Long dishId = Long.parseLong(request.getParameter("dish_id"));
            Dish dish = dishService.getById(dishId);
            System.out.println(dish.toString());

            if(dish!=null){
                System.out.println("not null!");
                result+=dish.getCategoryId();
                System.out.println(result);
                String dishName = request.getParameter("name");
                System.out.println(dishName);
                String dishDescription = request.getParameter("description");
                System.out.println(dishDescription);
                String dishIngredients = request.getParameter("ingredients");
                System.out.println(dishIngredients);
                BigDecimal dishPrice = new BigDecimal(request.getParameter("price"));
                System.out.println(dishPrice);
                //Integer dishQuantity = Integer.parseInt(request.getParameter("quantity"));
                Integer dishQuantity = 200;//todo!!!
                String dishImage = request.getParameter("image");
                System.out.println(dishImage);

                dish.setName(dishName);
                dish.setDescription(dishDescription);
                dish.setIngredients(dishIngredients);
                dish.setPrice(dishPrice);
                dish.setQuantity(dishQuantity);
                dish.setImage(dishImage);
                System.out.println("in if! "+dish.toString());

                dishService.update(dish);
            }else {
                result=JspPageName.CONCRETE_MENU_JSP;
            }
        } catch (ServiceException e) {
            throw new CommandException("");
        }

        return result;
    }
}
