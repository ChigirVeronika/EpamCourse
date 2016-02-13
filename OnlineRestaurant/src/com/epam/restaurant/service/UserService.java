package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.UserSqlDao;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.exception.ServiceException;
import com.epam.restaurant.util.HashUtil;
import org.apache.log4j.Logger;

import com.epam.restaurant.dao.factory.DaoFactory;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Вероника on 05.02.2016.
 */
public class UserService {
    private static DaoFactory factory = SqlDaoFactory.getInstance();

    public User get(String login) throws ServiceException {
        UserSqlDao userDao = (UserSqlDao) factory.getDao(User.class);
        User user = null;
        try {
            user = userDao.getByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Exception",e);
        }

        return user;
    }

    public User login(String login, String password) throws ServiceException {
        User user = get(login);
        try {
            if (user != null && HashUtil.validatePassword(password.toCharArray(), user.getHash())) {
                return user;
            }
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            throw new ServiceException("Exception",e);
        } catch (InvalidKeySpecException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            throw new ServiceException("Exception",e);
        }
        return null;
    }

    public User create(String login, String password, String mail) throws ServiceException {
        UserSqlDao userDao = (UserSqlDao) factory.getDao(User.class);
        User user = null;
        try {
            user = new User(login, HashUtil.createHash(password), mail);
            return userDao.persist(user);
        }catch (DaoException e){
            throw new ServiceException("Exception",e);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            throw new ServiceException("Exception",e);
        } catch (InvalidKeySpecException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            throw new ServiceException("Exception",e);
        }
    }

    public void update(User user) throws ServiceException {
        UserSqlDao userDao = (UserSqlDao) factory.getDao(User.class);
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Exception",e);
        }
    }
}
