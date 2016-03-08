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

/**
 * Checkout USER order.
 */
public class CheckoutCommand implements Command {
    private static final OrderService orderService = OrderService.getInstance();

    private static final OrderDishService orderDishService = OrderDishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.CHECKOUT_JSP;

        User currentUser = (User) request.getSession().getAttribute("user");
        try{
            Order currentOrder = (Order)request.getSession().getAttribute("order");
            for(OrderDish od: currentOrder.getOrderDishes()){
                orderDishService.delete(od);
            }

            //todo тут еще сервис, чтоб обновить статус оплачено и цену забить:)
            currentOrder.setPaid(true);
            BigDecimal total = new BigDecimal(request.getParameter("total"));
            currentOrder.setTotal(total);
            orderService.updateStatus(currentOrder);


            request.getSession().removeAttribute("order");

            Order newOrder = orderService.create(currentUser.getId(),new Date());
            request.getSession().setAttribute("order",newOrder);
        } catch (ServiceException e) {
            throw new CommandException("Cant't executeCheckoutCommand");
        }

        return result;
    }
}
