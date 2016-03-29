package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.entity.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 *
 */
public class OrderSqlDaoTest {

    OrderSqlDao dao = (OrderSqlDao) OrderSqlDao.getInstance();
    ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");
    ConnectionPool pool = ConnectionPoolImpl.getInstance();
    Connection connection;

    @Test
    public void testGetSelectQuery() throws Exception {
        assertEquals("SELECT id,user_id,created_at,total,paid FROM restaurant.user_order", dao.getSelectQuery());
    }

    @Test
    public void testGetCreateQuery() throws Exception {
        assertEquals("INSERT INTO restaurant.user_order (user_id,created_at,total,paid) VALUES (?, ?, ?, ?)", dao.getCreateQuery());
    }

    @Test
    public void testGetUpdateQuery() throws Exception {
        assertEquals("UPDATE restaurant.user_order SET user_id=?,created_at=?,total=?,paid=? WHERE id = ?", dao.getUpdateQuery());
    }

    @Test
    public void testGetDeleteQuery() throws Exception {
        assertEquals("DELETE FROM restaurant.user_order WHERE id = ?", dao.getDeleteQuery());
    }

    @Test
    public void testParseResultSet() throws Exception {
        connection = pool.getConnection();
        List<Order> list;
        String sql = dbBundle.getString("ORDER.FROM_USER_ID");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, 26L);
        ResultSet rs = statement.executeQuery();

        list = dao.parseResultSet(rs);
        assertNotNull(list);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForInsert() throws Exception {
        connection = pool.getConnection();
        Order order = new Order(11l,11l,new Date(), new BigDecimal(10));
        String sql = dbBundle.getString("ORDER.INSERT");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForInsert(st, order);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForUpdate() throws Exception {
        connection = pool.getConnection();
        Order order = new Order(11l,11l,new Date(), new BigDecimal(10));
        String sql = dbBundle.getString("ORDER.UPDATE");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForUpdate(st, order);
        assertNotNull(st);
        pool.returnConnection(connection);
    }
}