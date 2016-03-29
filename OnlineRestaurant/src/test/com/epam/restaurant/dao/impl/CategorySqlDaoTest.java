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
public class CategorySqlDaoTest {

    CategorySqlDao dao = (CategorySqlDao) CategorySqlDao.getInstance();
    ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");
    ConnectionPool pool = ConnectionPoolImpl.getInstance();
    Connection connection;

    @Test
    public void testGetSelectQuery() throws Exception {
        assertEquals("SELECT id,dish_id,order_id,quantity FROM restaurant.order_dish", dao.getSelectQuery());
    }

    @Test
    public void testGetCreateQuery() throws Exception {
        assertEquals("INSERT INTO restaurant.order_dish (dish_id,order_id,quantity) VALUES (?,?,?)", dao.getCreateQuery());
    }

    @Test
    public void testGetUpdateQuery() throws Exception {
        assertEquals("UPDATE restaurant.order_dish SET dish_id=?,order_id=?,quantity=? WHERE id = ?", dao.getUpdateQuery());
    }

    @Test
    public void testGetDeleteQuery() throws Exception {
        assertEquals("DELETE FROM restaurant.order_dish WHERE id = ?", dao.getDeleteQuery());
    }

    @Test
    public void testCreate() throws Exception {

    }

    @Test
    public void testParseResultSet() throws Exception {

    }

    @Test
    public void testPrepareStatementForUpdate() throws Exception {

    }

    @Test
    public void testPrepareStatementForInsert() throws Exception {

    }

    @Test
    public void testGetByName() throws Exception {

    }
}