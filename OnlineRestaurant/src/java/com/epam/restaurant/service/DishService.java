package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.DishSqlDao;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;


/**
 * Perfor service operations with dish object.
 */
public class DishService {
    private static final Logger LOGGER = Logger.getLogger( DishService.class);

    private static DishSqlDao dishDao = (DishSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.DISH);

    private static DishService instance = new DishService();

    private DishService(){}

    public static DishService getInstance(){return instance;}

    public Dish getById(Long dishId) throws ServiceException{
        try {
            return dishDao.getByPK(dishId);
        } catch (DaoException e) {
            LOGGER.error("Can't do DishSqlDao in DishService",e);
            throw new ServiceException("DishServiceException",e);
        }
    }

    public Dish create(String name, String description, String ingredients,
                       BigDecimal price, int  quantity, Long categoryId, String image)
                        throws ServiceException{
        Dish dish = new Dish(name,description,ingredients,price,quantity,categoryId,image);
        try {
            return dishDao.persist(dish);
        } catch (DaoException e) {
            LOGGER.error("Can't do DishSqlDao in DishService",e);
            throw new ServiceException("DishServiceException",e);
        }
    }

    public void delete(Dish dish) throws ServiceException{
        try {
            dishDao.delete(dish);
        } catch (DaoException e) {
            LOGGER.error("Can't do DishSqlDao in DishService",e);
            throw new ServiceException("DishServiceException",e);
        }
    }

    public void update(Dish dish) throws ServiceException{
        try {
            dishDao.update(dish);
        } catch (DaoException e) {
            LOGGER.error("Can't do DishSqlDao in DishService",e);
            throw new ServiceException("DishServiceException",e);
        }
    }
}
