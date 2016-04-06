package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.UserSqlDao;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.exception.ServiceException;
import com.epam.restaurant.util.HashUtil;
import com.epam.restaurant.util.ValidationUtil;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Perform service operations with user objects.
 */
public class UserService {

    /**
     * Dao for user dao objects
     */
    private static UserSqlDao userDao = (UserSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.USER);

    private static UserService instance = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance;
    }

    /**
     * Get user by login
     *
     * @param login user login
     * @return user
     * @throws ServiceException
     */
    public User get(String login) throws ServiceException {
        try {
            User user = userDao.getByLogin(login);
            return user;

        } catch (DaoException e) {
            throw new ServiceException("UserService Exception", e);
        }
    }

    /**
     * Get user by his id
     *
     * @param id user id
     * @return user
     * @throws ServiceException
     */
    public User getById(Long id) throws ServiceException {
        try {
            User user = userDao.getByPK(id);
            return user;

        } catch (DaoException e) {
            throw new ServiceException("UserService Exception", e);
        }
    }

    /**
     * Login user to application
     *
     * @param login user login
     * @param password user password hash
     * @return user
     * @throws ServiceException
     */
    public User login(String login, String password) throws ServiceException {
        User user = get(login);
        try {
            if (user != null && HashUtil.validatePassword(password.toCharArray(), user.getHash())) {
                return user;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("UserService Exception", e);
        } catch (InvalidKeySpecException e) {
            throw new ServiceException("UserService Exception", e);
        }
        return null;
    }

    /**
     * Create new record in data source with specific params
     *
     * @param name user name
     * @param surname user surname
     * @param email user email
     * @param payCard user pay card number
     * @param login user login
     * @param password user password
     * @return created user
     * @throws ServiceException
     */
    public User create(String name, String surname, String email, String payCard, String login, String password) throws ServiceException {
        User user;
        try {
            user = new User(name, surname, email, payCard, login, HashUtil.createHash(password));
            if (ValidationUtil.userValid(user)) {
                return userDao.persist(user);
            } else {
                throw new ServiceException("UserService Exception");
            }
        } catch (DaoException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException("UserService Exception", e);
        }
    }

    /**
     * Update record in data source
     *
     * @param user user to update
     * @throws ServiceException
     */
    public void update(User user) throws ServiceException {
        try {
                userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("UserService Exception", e);
        }
    }
}
