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

    /**
     * Dao for category dao objects
     */
    private static final CategorySqlDao categoryDao = (CategorySqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.CATEGORY);

    /**
     * Dao for dish dao objects
     */
    private static final DishSqlDao dishDao = (DishSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.DISH);

    private static CategoryService instance = new CategoryService();

    private CategoryService() {
    }

    public static CategoryService getInstance() {
        return instance;
    }

    /**
     * Get all categories from data source
     *
     * @return list of all categories from data source
     * @throws ServiceException
     */
    public List<Category> getAllCategories() throws ServiceException {
        try {
            return categoryDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("CategoryServiceException", e);
        }
    }

    /**
     * Get category from data source with specific id
     *
     * @param id category id
     * @return category with this id
     * @throws ServiceException
     */
    public Category getById(Long id) throws ServiceException {
        try {
            return categoryDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

    /**
     * Get all dishes from current category
     *
     * @param categoryId category id
     * @return dishes from with category
     * @throws ServiceException
     */
    public List<Dish> getAllFromCategory(long categoryId) throws ServiceException {
        try {
            return dishDao.getAllFromCategory(categoryId);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

    /**
     * Get category from data source with specific name
     *
     * @param name category name
     * @return category
     * @throws ServiceException
     */
    public Category getByName(String name) throws ServiceException {
        try {
            return categoryDao.getByName(name);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

    /**
     * Create new record in data source with specific params
     *
     * @param name category name
     * @param description category description
     * @return created category object
     * @throws ServiceException
     */
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

    /**
     * Update record in data source for specific category
     *
     * @param category category to update
     * @throws ServiceException
     */
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

    /**
     * Remove record from data source for specific category
     *
     * @param category category to remove
     * @throws ServiceException
     */
    public void delete(Category category) throws ServiceException {
        try {
            categoryDao.delete(category);
        } catch (DaoException e) {
            throw new ServiceException("CategoryService Exception", e);
        }
    }

}
