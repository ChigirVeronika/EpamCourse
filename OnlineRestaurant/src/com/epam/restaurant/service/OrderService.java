package com.epam.restaurant.service;

import com.epam.restaurant.entity.Order;
import com.epam.restaurant.service.exception.ServiceException;

import java.sql.SQLException;

/**
 * Created by Вероника on 04.02.2016.
 */
public class OrderService {
    public Order getByUserId(int userId) throws ServiceException{
        //orderdao todo
        Order order = new Order();

        //todo set to order

        return order;
    }
}
