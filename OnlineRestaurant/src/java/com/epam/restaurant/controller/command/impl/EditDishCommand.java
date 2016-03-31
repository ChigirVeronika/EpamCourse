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

import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Change dish parameters by user with role ADMIN.
 */
public class EditDishCommand implements Command {

    /**
     * Service provides work with database (dish table)
     */
    private static final DishService dishService = DishService.getInstance();

    /**
     * At first, check session expiration. If it's expired, return login page.
     * If not, get from request dish id and other parameters.
     * Get current dish abd update it.
     * If everything is fine, return current category page value.
     *
     * @return page to forward to
     * @throws CommandException if can't update dish
     */
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
                String dishName = request.getParameter(DISH_NAME);
                String dishDescription = request.getParameter(DISH_DESCRIPTION);
                String dishIngredients = request.getParameter(DISH_INGREDIENTS);
                BigDecimal dishPrice = new BigDecimal(request.getParameter(DISH_PRICE));
                Integer dishQuantity = Integer.parseInt(request.getParameter(DISH_QUANTITY));
                String dishImage = request.getParameter(DISH_IMAGE);

                dish.setName(dishName);
                dish.setDescription(dishDescription);
                dish.setIngredients(dishIngredients);
                dish.setPrice(dishPrice);
                dish.setQuantity(dishQuantity);
                dish.setImage(dishImage);

                dishService.update(dish);
            } else {
                result = JspPageName.CONCRETE_MENU_JSP;
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute EditDishCommand", e);
        }

        return result;
    }
}
