package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.DishService;
import com.epam.restaurant.service.OrderDishService;
import com.epam.restaurant.service.OrderService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.epam.restaurant.util.SessionUtil.*;
import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Add dish to users order whose role USER.
 */
public class AddToOrderCommand implements Command {

    /**
     * Service provides work with database (order table)
     */
    private static final OrderService orderService = OrderService.getInstance();

    /**
     * Service provides work with database (dish table)
     */
    private static final DishService dishService = DishService.getInstance();

    /**
     * Service provides work with database (order_dish table)
     */
    private static final OrderDishService orderDishService = OrderDishService.getInstance();

    /**
     * At first, check session expiration. If it's expired, return login page.
     * If not, get from request user and his order. Create order if it still doesn't exist.
     * Get dish id from request and add corresponding dish to order.
     * If everything is fine, return concrete order page value.
     *
     * @return page to forward to
     * @throws CommandException if can't add to order
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.ORDER_JSP;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }

        User currentUser = (User) request.getSession().getAttribute(USER);
        Order currentOrder = (Order) request.getSession().getAttribute(ORDER);

        try {
            long dishId = Long.parseLong(request.getParameter(DISH_ID));
            if (currentOrder == null) {
                currentOrder = orderService.create(currentUser.getId(), new Date());
            }
            OrderDish orderDish = orderDishService.create(dishId, currentOrder.getId(), 1);
            Dish dish = dishService.getById(dishId);
            orderDish.setDish(dish);
            currentOrder.getOrderDishes().add(orderDish);
            request.getSession().setAttribute(ORDER, currentOrder);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new CommandException("Cant't execute AddToOrderCommand", e);
        }
        return result;
    }
}
