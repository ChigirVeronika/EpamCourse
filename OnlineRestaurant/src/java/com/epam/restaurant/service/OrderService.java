package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.OrderSqlDao;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * Perform service operations with order object.
 */
public class OrderService {

    /**
     * Dao for order dao objects
     */
    private static OrderSqlDao orderDao = (OrderSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.ORDER);

    private static OrderService instance = new OrderService();

    private OrderService() {
    }

    public static OrderService getInstance() {
        return instance;
    }

    /**
     * Create new record in data source with specific params
     *
     * @param userId
     * @param createdAt
     * @return
     * @throws ServiceException
     */
    public Order create(Long userId, Date createdAt) throws ServiceException {
        Order order = new Order(userId, createdAt);
        try {
            return orderDao.persist(order);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException", e);
        }
    }

    /**
     * Delete record from data source
     *
     * @param order
     * @throws ServiceException
     */
    public void delete(Order order) throws ServiceException {
        try {
            orderDao.delete(order);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException", e);
        }
    }

    /**
     * Update order status in data source
     *
     * @param order
     * @throws ServiceException
     */
    public void updateStatus(Order order) throws ServiceException {
        try {
            orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException", e);
        }
    }

    /**
     * Get concrete order from data source by user id
     *
     * @param userId
     * @return order
     * @throws ServiceException
     */
    public Order getByUserId(long userId) throws ServiceException {
        Order order;
        try {
            order = orderDao.getByUserId(userId);
            if (order != null) {
                OrderDishService orderDishService = OrderDishService.getInstance();
                List<OrderDish> orderDishList = orderDishService.getAllFromOrder(order.getId());
                if (orderDishList.size() > 0) {
                    DishService dishService = DishService.getInstance();
                    for (OrderDish od : orderDishList) {
                        od.setDish(dishService.getById(od.getDishId()));
                    }
                }
                order.setOrderDishes(orderDishList);
            }
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException", e);
        }

        return order;
    }
}
