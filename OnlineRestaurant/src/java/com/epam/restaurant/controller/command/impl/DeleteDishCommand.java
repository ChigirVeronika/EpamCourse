package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.service.DishService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.restaurant.util.SessionUtil.*;
import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Delete dish from dish list by user with role ADMIN.
 */
public class DeleteDishCommand implements Command {

    private static final DishService dishService = DishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CONCRETE_CATEGORY_JSP;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }

        try {
            Long dishId = Long.parseLong(request.getParameter(DISH_ID));
            Dish dish = dishService.getById(dishId);

            if (dish != null) {
                result += dish.getCategoryId();
                dishService.delete(dish);
            } else {
                result = JspPageName.CONCRETE_MENU_JSP;
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute DeleteDishCommand", e);
        }

        return result;
    }
}
