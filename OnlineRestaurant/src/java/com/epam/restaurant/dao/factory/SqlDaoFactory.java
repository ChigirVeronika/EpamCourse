package com.epam.restaurant.dao.factory;

import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.impl.*;
import org.apache.log4j.Logger;

/**
 * Created by Вероника on 22.01.2016.
 */
public class SqlDaoFactory{
    private static final Logger LOGGER = Logger.getLogger(SqlDaoFactory.class);

    private final static SqlDaoFactory instance = new SqlDaoFactory();

    public static SqlDaoFactory getInstance(){
        return  instance;
    }

    public GenericDao getDao(DaoType type){// throws DaoException TODO???
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
        return null;
    }

    public enum DaoType{
        DISH, USER, CATEGORY, ORDER, ORDERDISH, NEWS
    }

}
