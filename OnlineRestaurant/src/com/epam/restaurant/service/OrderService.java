package com.epam.restaurant.service;

import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.service.exception.ServiceException;

/**
 * Perform service operations with order object.
 */
public class OrderService {

    private static OrderService instance = new OrderService();

    private OrderService(){}

    public static OrderService getInstance(){
        return instance;
    }

    private static GenericDao orderDao = SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.ORDER);

    public Order getByUserId(long userId) throws ServiceException{
        //order dao todo
        Order order = new Order();

        //todo set to order

        return order;
    }
}
