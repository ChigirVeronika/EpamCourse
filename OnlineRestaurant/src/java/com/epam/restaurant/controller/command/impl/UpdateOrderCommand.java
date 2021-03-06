package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.service.OrderDishService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.restaurant.util.SessionUtil.*;
import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Update order dish (with new quantity) in order.
 * Handle 'Update' button on the order page.
 */
public class UpdateOrderCommand implements Command {

    /**
     * Service provides work with database (order_dish table)
     */
    private static final OrderDishService orderDishService = OrderDishService.getInstance();

    /**
     * At first, check session expiration. If it's expired, return login page.
     * If not, get from request order and update it.
     * If everything is fine, return order page value.
     *
     * @return page to forward to
     * @throws CommandException if can't update order
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.ORDER_JSP;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }

        Order currentOrder = (Order) request.getSession().getAttribute(ORDER);

        try {
            Long orderDishId = Long.parseLong(request.getParameter(ITEM_ID));
            Integer newQuantity = Integer.parseInt(request.getParameter(DISH_QUANTITY));

            for (OrderDish od : currentOrder.getOrderDishes()) {
                if (od.getId() == orderDishId) {
                    od.setQuantity(newQuantity);
                    orderDishService.update(od);
                    break;
                }
            }
        } catch (ServiceException e) {
            throw new CommandException("UpdateOrderCommand Exception", e);
        }

        return result;
    }
}
