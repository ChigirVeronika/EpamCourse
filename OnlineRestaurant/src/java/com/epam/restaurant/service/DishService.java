package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.DishSqlDao;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.service.exception.ServiceException;
import com.epam.restaurant.util.ValidationUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

/**
 * Perform service operations with dish object.
 */
public class DishService {

    /**
     * Dao for dish dao objects
     */
    private static DishSqlDao dishDao = (DishSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.DISH);

    private static DishService instance = new DishService();

    private DishService() {
    }

    public static DishService getInstance() {
        return instance;
    }

    /**
     * Get dish from data source by id
     *
     * @param dishId dish id
     * @return dish
     * @throws ServiceException
     */
    public Dish getById(Long dishId) throws ServiceException {
        try {
            return dishDao.getByPK(dishId);
        } catch (DaoException e) {
            throw new ServiceException("DishServiceException", e);
        }
    }

    /**
     * Create new record in data source with specific params
     *
     * @param name
     * @param description
     * @param ingredients
     * @param price
     * @param quantity
     * @param categoryId
     * @param image
     * @return created dish
     * @throws ServiceException
     */
    public Dish create(String name, String description, String ingredients,
                       BigDecimal price, int quantity, Long categoryId, String image)
            throws ServiceException {
        Dish dish = new Dish(name, description, ingredients, price, quantity, categoryId, image);
        try {
            if (ValidationUtil.dishValid(dish)) {
                return dishDao.persist(dish);
            } else {
                throw new ServiceException("DishServiceException");
            }
        } catch (DaoException e) {
            throw new ServiceException("DishServiceException", e);
        }
    }

    /**
     * Delete record from data source
     *
     * @param dish
     * @throws ServiceException
     */
    public void delete(Dish dish) throws ServiceException {
        try {
            dishDao.delete(dish);
        } catch (DaoException e) {
            throw new ServiceException("DishServiceException", e);
        }
    }

    /**
     * Update record in data source
     *
     * @param dish
     * @throws ServiceException
     */
    public void update(Dish dish) throws ServiceException {
        try {
            if (ValidationUtil.dishValid(dish)) {
                dishDao.update(dish);
            } else {
                throw new ServiceException("DishServiceException");
            }
        } catch (DaoException e) {
            throw new ServiceException("DishServiceException", e);
        }
    }
}
