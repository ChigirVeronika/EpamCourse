package com.epam.restaurant.dao.factory;

import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.impl.*;
import org.apache.log4j.Logger;

/**
 * Factory pattern realization in dao layer.
 */
public class SqlDaoFactory{
    private static final Logger LOGGER = Logger.getLogger(SqlDaoFactory.class);

    private final static SqlDaoFactory instance = new SqlDaoFactory();

    public static SqlDaoFactory getInstance(){
        return  instance;
    }

    public GenericDao getDao(DaoType type){
        switch (type){
            case DISH:
                return DishSqlDao.getInstance();
            case USER:
                return UserSqlDao.getInstance();
            case CATEGORY:
                return CategorySqlDao.getInstance();
            case ORDER:
                return OrderSqlDao.getInstance();
            case ORDERDISH:
                return OrderDishSqlDao.getInstance();
            case NEWS:
                return NewsSqlDao.getInstance();
        }
        LOGGER.info("No such dao type.");
        return null;
    }

    public enum DaoType{
        DISH, USER, CATEGORY, ORDER, ORDERDISH, NEWS
    }

}
