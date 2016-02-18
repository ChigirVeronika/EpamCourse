package com.epam.restaurant.dao.factory;

import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.impl.CategorySqlDao;
import com.epam.restaurant.dao.impl.UserSqlDao;
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

    public GenericDao getDao(DaoType type){// throws DaoException
        switch (type){
            case DISH:
                //return DishSqlDao.getInstance();
                break;
            case USER:
                return UserSqlDao.getInstance();
            case CATEGORY:
                return CategorySqlDao.getInstance();
            case BILL:
                break;
        }
        return null;
    }

    public enum DaoType{
        DISH, USER, CATEGORY, BILL, ORDER
    }

}
