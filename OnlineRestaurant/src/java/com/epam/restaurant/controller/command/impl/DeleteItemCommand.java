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
/**
 * Delete item from order by user with role USER.
 */
public class DeleteItemCommand implements Command {

    private static final OrderDishService orderDishService = OrderDishService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.ORDER_JSP;

        if(sessionExpired(request)){
            result=JspPageName.LOGIN_JSP;
            return result;
        }

        try{
            Long orderDishId = Long.parseLong(request.getParameter("item_id"));

            Order order = (Order) request.getSession().getAttribute("order");

            for(OrderDish od: order.getOrderDishes()){
                if(od.getId()==orderDishId){//find item we need to delete
                    order.getOrderDishes().remove(od);
                    orderDishService.delete(od);
                    break;
                }
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute DeleteItemCommand");
        }

        return result;
    }
}
