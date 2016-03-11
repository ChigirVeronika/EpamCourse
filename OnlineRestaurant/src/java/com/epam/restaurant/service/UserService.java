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

    private static UserSqlDao userDao = (UserSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.USER);

    private static UserService instance = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return instance;
    }

    public User get(String login) throws ServiceException {
        try {
            User user = userDao.getByLogin(login);
            return user;

        } catch (DaoException e) {
            throw new ServiceException("UserService Exception");
        }
    }

    public User getById(Long id) throws ServiceException {
        try {
            User user = userDao.getByPK(id);
            return user;

        } catch (DaoException e) {
            throw new ServiceException("UserService Exception");
        }
    }

    public User login(String login, String password) throws ServiceException {
        User user = get(login);
        try {
            if (user != null && HashUtil.validatePassword(password.toCharArray(), user.getHash())) {
                return user;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("UserService Exception");
        } catch (InvalidKeySpecException e) {
            throw new ServiceException("UserService Exception");
        }
        return null;
    }

    public User create(String name, String surname, String email, String payCard, String login, String password) throws ServiceException {
        User user;
        try {
            user = new User(name, surname, email, payCard, login, HashUtil.createHash(password));
//            user = new User(CharsetUtil.StringToUtf8(name), CharsetUtil.StringToUtf8(surname),//todo
//                    email, payCard, CharsetUtil.StringToUtf8(login), HashUtil.createHash(password));
            if (ValidationUtil.userValid(user)) {
                return userDao.persist(user);
            } else {
                throw new ServiceException("UserService Exception");
            }
        } catch (DaoException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException("UserService Exception");
        }
    }

    public void update(User user) throws ServiceException {
        try {
            if (ValidationUtil.userValid(user)) {
                userDao.update(user);
            } else {
                throw new ServiceException("UserService Exception");
            }
        } catch (DaoException e) {
            throw new ServiceException("UserService Exception");
        }
    }
}
