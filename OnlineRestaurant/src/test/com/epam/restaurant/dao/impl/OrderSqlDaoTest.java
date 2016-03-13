package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Вероника on 02.03.2016.
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
        assertEquals("INSERT INTO restaurant.user_order (user_id,created_at,total,paid) VALUES (?, ?, ?, ?)", dbBundle.getString("ORDER.INSERT"));
    }

    @Test
    public void testGetUpdateQuery() throws Exception {
        assertEquals("UPDATE restaurant.user_order SET user_id=?,created_at=?,total=?,paid=? WHERE id = ?", dbBundle.getString("ORDER.UPDATE"));
    }

    @Test
    public void testGetDeleteQuery() throws Exception {
        assertEquals("DELETE FROM restaurant.user_order WHERE id = ?", dbBundle.getString("ORDER.DELETE"));
    }

    @Test
    public void testParseResultSet() throws Exception {
        connection = pool.getConnection();
        List<Order> list;
        String sql = dbBundle.getString("ORDER.FROM_USER_ID");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "vera");
        ResultSet rs = statement.executeQuery();

        list = dao.parseResultSet(rs);
        assertNotNull(list);
        pool.returnConnection(connection);
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
    public void testGetByUserId() throws Exception {

    }
}