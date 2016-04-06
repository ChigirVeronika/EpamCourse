package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.OrderDishSqlDao;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.service.exception.ServiceException;

import java.util.List;

/**
 * Perform service operations with orderDish object.
 */
public class OrderDishService {

    /**
     * Dao for orderDish dao objects
     */
    private static final OrderDishSqlDao orderDishDao = (OrderDishSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.ORDERDISH);

    private static final OrderDishService instance = new OrderDishService();

    private OrderDishService() {
    }

    public static OrderDishService getInstance() {
        return instance;
    }

    /**
     * Get concrete order dish by id from data source
     *
     * @param dishId
     * @return order dish
     * @throws ServiceException
     */
    public OrderDish getById(Long dishId) throws ServiceException {
        try {
            return orderDishDao.getByPK(dishId);
        } catch (DaoException e) {
            throw new ServiceException("DishServiceException", e);
        }
    }

    /**
     * Create new record in data source with specific params
     *
     * @param dishId
     * @param orderId
     * @param quantity
     * @return
     * @throws ServiceException
     */
    public OrderDish create(long dishId, long orderId, int quantity)
            throws ServiceException {
        OrderDish orderDish = new OrderDish(dishId, orderId, quantity);
        try {
            return orderDishDao.persist(orderDish);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException", e);
        }
    }

    /**
     * Delete record from data source
     *
     * @param orderDish
     * @throws ServiceException
     */
    public void delete(OrderDish orderDish) throws ServiceException {
        try {
            orderDishDao.delete(orderDish);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException", e);
        }
    }

    /**
     * Update record in data source
     *
     * @param orderDish
     * @throws ServiceException
     */
    public void update(OrderDish orderDish) throws ServiceException {
        try {
            orderDishDao.update(orderDish);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException", e);
        }
    }

    /**
     * Get all items from concrete order
     *
     * @param orderId
     * @return list of order dishes
     * @throws ServiceException
     */
    public List<OrderDish> getAllFromOrder(Long orderId) throws ServiceException {
        try {
            return orderDishDao.getAllFromOrder(orderId);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException", e);
        }
    }
}
