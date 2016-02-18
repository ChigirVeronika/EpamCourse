package com.epam.restaurant.service;

import com.epam.restaurant.entity.Order;
import com.epam.restaurant.service.exception.ServiceException;

/**
 * Perform service operations with order object.
 */
public class OrderService {
    public Order getByUserId(int userId) throws ServiceException{
        //order dao todo
        Order order = new Order();

        //todo set to order

        return order;
    }
}
