package com.epam.restaurant.dao.factory;

import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.impl.CategorySqlDao;
import com.epam.restaurant.dao.impl.UserSqlDao;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.User;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Вероника on 09.02.2016.
 */
public class SqlDaoFactory implements DaoFactory {

    private static final Logger LOGGER = Logger.getLogger(SqlDaoFactory.class);

    private final static SqlDaoFactory instance = new SqlDaoFactory();

    public static SqlDaoFactory getInstance(){
        return  instance;
    }

    private Map<Class, DaoCreator> creators;

    /**
     * Constructor (fill map)
     */
    private SqlDaoFactory() {

        creators = new HashMap();

        creators.put(User.class, new DaoCreator() {
            public GenericDao create(DaoFactory factory) {
                return new UserSqlDao(factory);
            }
        });
        creators.put(Category.class, new DaoCreator() {
            public GenericDao create(DaoFactory factory) {
                return new CategorySqlDao(factory);
            }
        });
    }

    @Override
    public GenericDao getDao(Class daoClass) throws IllegalArgumentException {
        DaoCreator creator = creators.get(daoClass);
        if (creator == null) {
            throw new IllegalArgumentException("Dao object for " + daoClass + " not found.");
        }
        return creator.create(this);
    }
}
