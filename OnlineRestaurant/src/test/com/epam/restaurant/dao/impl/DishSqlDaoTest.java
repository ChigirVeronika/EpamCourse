package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import org.junit.Test;

import java.sql.Connection;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Вероника on 13.03.2016.
 */
public class DishSqlDaoTest {

    UserSqlDao userSqlDao = (UserSqlDao) UserSqlDao.getInstance();
    ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");
    ConnectionPool pool = ConnectionPoolImpl.getInstance();
    Connection connection;

    @Test
    public void testGetSelectQuery() throws Exception {

    }

    @Test
    public void testGetCreateQuery() throws Exception {

    }

    @Test
    public void testGetUpdateQuery() throws Exception {

    }

    @Test
    public void testGetDeleteQuery() throws Exception {

    }

    @Test
    public void testParseResultSet() throws Exception {

    }

    @Test
    public void testPrepareStatementForInsert() throws Exception {

    }

    @Test
    public void testPrepareStatementForUpdate() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {

    }

    @Test
    public void testGetAllFromCategory() throws Exception {

    }
}