package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.DishService;
import com.epam.restaurant.service.OrderDishService;
import com.epam.restaurant.service.OrderService;
import com.epam.restaurant.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import static com.epam.restaurant.util.SessionUtil.*;
/**
 * Add dish to users order whose role USER.
 */
public class AddToOrderCommand implements Command {

    private static final OrderService orderService = OrderService.getInstance();

    private static final DishService dishService = DishService.getInstance();

    private static final OrderDishService orderDishService = OrderDishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.ORDER_JSP;

        if(sessionExpired(request)){
            result=JspPageName.LOGIN_JSP;
            return result;
        }

        User currentUser = (User) request.getSession().getAttribute("user");
        Order currentOrder = (Order) request.getSession().getAttribute("order");

        try{
            long dishId = Long.parseLong(request.getParameter("dish_id"));
            if(currentOrder == null){
                currentOrder = orderService.create(currentUser.getId(),new Date());
            }
            OrderDish orderDish = orderDishService.create(dishId,currentOrder.getId(),1);
            Dish dish = dishService.getById(dishId);
            orderDish.setDish(dish);
            currentOrder.getOrderDishes().add(orderDish);
            request.getSession().setAttribute("order",currentOrder);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new CommandException("Cant't execute AddToOrderCommand");
        }
        return result;
    }
}
