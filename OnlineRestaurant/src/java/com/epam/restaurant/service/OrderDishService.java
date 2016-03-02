package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.OrderDishSqlDao;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.service.exception.ServiceException;

import java.util.List;

/**
 *
 */
public class OrderDishService {
    private static final OrderDishSqlDao orderDishDao = (OrderDishSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.ORDERDISH);

    private static final OrderDishService instance = new OrderDishService();

    private OrderDishService(){}

    public static  OrderDishService getInstance(){
        return instance;
    }

    public OrderDish getById(Long dishId) throws ServiceException {
        try {
            return orderDishDao.getByPK(dishId);
        } catch (DaoException e) {
            throw new ServiceException("DishServiceException",e);
        }
    }

    public OrderDish  create(long dishId,long orderId, int quantity)
            throws ServiceException{
        OrderDish orderDish = new OrderDish (dishId,orderId,quantity);
        try {
            return orderDishDao.persist(orderDish);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException",e);
        }
    }

    public void delete(OrderDish orderDish) throws ServiceException{
        try {
            orderDishDao.delete(orderDish);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException",e);
        }
    }

    public void update(OrderDish orderDish) throws ServiceException{
        try {
            orderDishDao.update(orderDish);
        } catch (DaoException e) {
            throw new ServiceException("OrderServiceException",e);
        }
    }

    public List<OrderDish> getAllFromOrder(Long orderId) throws ServiceException{
        try {
            return orderDishDao.getAllFromOrder(orderId);
        } catch (DaoException e) {
            throw new ServiceException("");
        }
    }
}
