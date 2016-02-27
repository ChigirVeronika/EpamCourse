package com.epam.restaurant.service;

import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

/**
 * .
 */
public class CategoryService {

    private static GenericDao categoryDao = SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.CATEGORY);
    private static GenericDao dishDao = SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.DISH);

    private static CategoryService instance = new CategoryService();

    private CategoryService(){}

    public static CategoryService getInstance(){return instance;}

    public List<Category> getAllCategories() throws ServiceException {
        try {
            return categoryDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("CategoryServiceException",e);
        }
    }

    public Category getById(long id)throws ServiceException{
        try {
            return (Category) categoryDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception",e);
        }
    }

    public List<Dish> getAllFromCategory(long categoryId) throws ServiceException {
        try {
            return dishDao.getAllFromRecord(categoryId);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception",e);
        }
    }
}
