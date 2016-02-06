package com.epam.restaurant.dao.sql.impl;

import com.epam.restaurant.dao.sql.SqlDao;
import com.epam.restaurant.dao.sql.SqlDaoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вероника on 05.02.2016.
 */
public final class UserSqlDao implements SqlDao {
    private final static UserSqlDao instance = new UserSqlDao();

    public static SqlDao getInstance(){
        return instance;
    }

    public List<Object> parse(String resourceName) throws SqlDaoException {
        List<Object> list = new ArrayList<Object>();
        list.add("user1");
        list.add("user2");
        return list;
    }
}
