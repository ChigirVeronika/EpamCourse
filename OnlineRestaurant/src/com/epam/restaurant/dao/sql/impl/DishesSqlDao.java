package com.epam.restaurant.dao.sql.impl;

import com.epam.restaurant.dao.sql.SqlDao;
import com.epam.restaurant.dao.sql.SqlDaoException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вероника on 06.02.2016.
 */
public final class DishesSqlDao implements SqlDao {

    private static final Logger LOGGER = Logger.getLogger(DishesSqlDao.class);

    private final static UserSqlDao instance = new UserSqlDao();

    public static SqlDao getInstance(){
        return instance;
    }

    public List<Object> parse(String resourceName) throws SqlDaoException {

        List<Object> list = new ArrayList<Object>();

        LOGGER.info("list in dao created");
        return list;
    }
}
