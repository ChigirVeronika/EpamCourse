package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.entity.User;
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
 * Created by Вероника on 13.03.2016.
 */
public class OrderDishSqlDaoTest {

    OrderDishSqlDao dao = (OrderDishSqlDao) OrderDishSqlDao.getInstance();
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
    public void testParseResultSet() throws Exception {
        connection = pool.getConnection();
        List<OrderDish> list;
        String sql = dbBundle.getString("ORDER_DISH.FROM_ORDER");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "vera");
        ResultSet rs = statement.executeQuery();

        list = dao.parseResultSet(rs);
        assertNotNull(list);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForInsert() throws Exception {
        connection = pool.getConnection();
        OrderDish orderDish = new OrderDish(20l,20l,20);
        String sql = dbBundle.getString("ORDER_DISH.INSERT");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForInsert(st, orderDish);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForUpdate() throws Exception {
        connection = pool.getConnection();
        OrderDish orderDish = new OrderDish(20l,20l,10);
        String sql = dbBundle.getString("ORDER_DISH.UPDATE");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForUpdate(st, orderDish);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testGetAllFromOrder() throws Exception {
        List<OrderDish> list = dao.getAllFromOrder(1l);
        assertNotNull(list);
    }
}