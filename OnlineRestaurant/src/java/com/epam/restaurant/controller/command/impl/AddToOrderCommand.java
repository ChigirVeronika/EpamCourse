package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.DishService;
import com.epam.restaurant.service.OrderDishService;
import com.epam.restaurant.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Вероника on 28.02.2016.
 */
public class AddToOrderCommand implements Command {

    private static final OrderService orderService = OrderService.getInstance();

    private static final DishService dishService = DishService.getInstance();

    private static final OrderDishService orderDishService = OrderDishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.ORDER_JSP;

        User currentUser = (User) request.getSession().getAttribute("user");
        Order currentOrder = (Order) request.getSession().getAttribute("order");

        try{
            long dishId = Long.parseLong(request.getParameter("dish_id"));
            if(currentOrder == null){
                currentOrder = orderService
            }
        }

        return result;
    }
}
