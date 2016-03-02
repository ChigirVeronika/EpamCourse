package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.service.OrderDishService;
import com.epam.restaurant.service.OrderService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Checkout USER order.
 */
public class CheckoutCommand implements Command {
    private static final OrderService orderService = OrderService.getInstance();

    private static final OrderDishService orderDishSerice = OrderDishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CHECKOUT_JSP;

        try{
            Order order = (Order)request.getSession().getAttribute("order");
            for(OrderDish od: order.getOrderDishes()){
                orderDishSerice.delete(od);
            }
            orderService.delete(order);
            request.getSession().removeAttribute("order");
        } catch (ServiceException e) {
            throw new CommandException("");
        }

        return result;
    }
}
