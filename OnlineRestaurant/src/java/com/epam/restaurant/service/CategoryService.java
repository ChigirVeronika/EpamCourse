package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.CategorySqlDao;
import com.epam.restaurant.dao.impl.DishSqlDao;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.service.exception.ServiceException;
import com.epam.restaurant.util.ValidationUtil;

import java.util.List;

/**
 * Perform service operations with category object.
 */
public class CategoryService {

    private static final CategorySqlDao categoryDao = (CategorySqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.CATEGORY);
    private static final DishSqlDao dishDao = (DishSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.DISH);

    private static CategoryService instance = new CategoryService();

    private CategoryService() {
    }

    public static CategoryService getInstance() {
        return instance;
    }

    public List<Category> getAllCategories() throws ServiceException {
        try {
            return categoryDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("CategoryServiceException", e);
        }
    }

    public Category getById(Long id) throws ServiceException {
        try {
            return categoryDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

    public List<Dish> getAllFromCategory(long categoryId) throws ServiceException {
        try {
            return dishDao.getAllFromCategory(categoryId);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

    public Category getByName(String name) throws ServiceException {
        try {
            return categoryDao.getByName(name);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

    public Category create(String name, String description) throws ServiceException {
        Category category = new Category(name, description);
        try {
            if (ValidationUtil.categoryValid(category)) {
                return categoryDao.persist(category);
            } else {
                throw new ServiceException("CategoryService Exception");
            }
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

    public void update(Category category) throws ServiceException {
        try {
            if (ValidationUtil.categoryValid(category)) {
                categoryDao.update(category);
            } else {
                throw new ServiceException("CategoryService Exception");
            }
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

    public void delete(Category category) throws ServiceException {
        try {
            categoryDao.delete(category);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

}
