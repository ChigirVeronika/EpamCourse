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
 * Delete item from order by user with role USER.
 */
public class DeleteItemCommand implements Command {

    /**
     * Service provides work with database (order_dish table)
     */
    private static final OrderDishService orderDishService = OrderDishService.getInstance();

    /**
     * At first, check session expiration. If it's expired, return login page.
     * If not, get from request item id and delete it.
     * If everything is fine, return order page value.
     *
     * @return page to forward to
     * @throws CommandException if can't delete order item
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = JspPageName.ORDER_JSP;

        if(sessionExpired(request)){
            result=JspPageName.LOGIN_JSP;
            return result;
        }

        try{
            Long orderDishId = Long.parseLong(request.getParameter(ITEM_ID));

            Order order = (Order) request.getSession().getAttribute(ORDER);

            for(OrderDish od: order.getOrderDishes()){
                if(od.getId()==orderDishId){//find item we need to delete
                    order.getOrderDishes().remove(od);
                    orderDishService.delete(od);
                    break;
                }
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute DeleteItemCommand", e);
        }

        return result;
    }
}
