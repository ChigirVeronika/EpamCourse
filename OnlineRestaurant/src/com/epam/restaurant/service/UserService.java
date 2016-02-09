package com.epam.restaurant.service;

import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.UserSqlDao;
import com.epam.restaurant.entity.User;
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

    public User get(String login) throws SQLException {
        UserSqlDao userDao = (UserSqlDao) factory.getDao(User.class);
        User user = userDao.getByLogin(login);

        return user;
    }

    public User login(String login, String password) throws SQLException {
        User user = get(login);
        try {
            if (user != null && HashUtil.validatePassword(password.toCharArray(), user.getHash())) {
                return user;
            }
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public User create(String login, String password, String mail) throws SQLException {
        UserSqlDao userDao = (UserSqlDao) factory.getDao(User.class);
        User user = null;
        try {
            user = new User(login, HashUtil.createHash(password), mail);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            Logger.getLogger(getClass()).error(e.getMessage());
            e.printStackTrace();
        }
        return userDao.persist(user);
    }

    public void update(User user) throws SQLException {
        UserSqlDao userDao = (UserSqlDao) factory.getDao(User.class);
        userDao.update(user);
    }
}
