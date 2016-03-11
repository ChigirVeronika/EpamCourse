package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.OrderDishService;
import com.epam.restaurant.service.OrderService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;

import static com.epam.restaurant.util.SessionUtil.*;
import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Checkout USER order.
 */
public class CheckoutCommand implements Command {
    private static final OrderService orderService = OrderService.getInstance();

    private static final OrderDishService orderDishService = OrderDishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CHECKOUT_JSP;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }

        User currentUser = (User) request.getSession().getAttribute(USER);
        try {
            Order currentOrder = (Order) request.getSession().getAttribute(ORDER);
            for (OrderDish od : currentOrder.getOrderDishes()) {
                orderDishService.delete(od);
            }

            currentOrder.setPaid(true);
            BigDecimal total = new BigDecimal(request.getParameter(TOTAL));
            currentOrder.setTotal(total);
            orderService.updateStatus(currentOrder);


            request.getSession().removeAttribute(ORDER);

            Order newOrder = orderService.create(currentUser.getId(), new Date());
            request.getSession().setAttribute(ORDER, newOrder);
        } catch (ServiceException e) {
            throw new CommandException("Cant't executeCheckoutCommand");
        }

        return result;
    }
}
