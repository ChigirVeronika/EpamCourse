package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.UserSqlDao;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.service.exception.ServiceException;
import com.epam.restaurant.util.HashUtil;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Perform service operations with user objects.
 */
public class UserService {

    private static UserService instance = new UserService();

    private UserService(){}

    public static UserService getInstance(){
        return instance;
    }

    private static UserSqlDao userDao = (UserSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.USER);

    public User get(String login) throws ServiceException{
        User user; //TODO ? он же налл, вот таким и должен остаться - это для сервиса
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

    public User create(String name, String surname, String email, String payCard, String login, String password) throws ServiceException {
        User user;
        try {
            user = new User(name, surname, email, payCard, login, HashUtil.createHash(password));
//            user = new User(CharsetUtil.StringToUtf8(name), CharsetUtil.StringToUtf8(surname),
//                    email, payCard, CharsetUtil.StringToUtf8(login), HashUtil.createHash(password));
            return  userDao.persist(user);
        }catch (DaoException|NoSuchAlgorithmException|InvalidKeySpecException  e){
            throw new ServiceException("Exception",e);
        }
    }

    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Exception",e);
        }
    }
}
