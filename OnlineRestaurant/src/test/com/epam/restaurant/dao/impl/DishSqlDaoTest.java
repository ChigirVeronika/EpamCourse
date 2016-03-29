package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.Dish;
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
 * Created by Вероника on 13.03.2016.
 */
public class DishSqlDaoTest {

    DishSqlDao dao = (DishSqlDao) DishSqlDao.getInstance();
    ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");
    ConnectionPool pool = ConnectionPoolImpl.getInstance();
    Connection connection;

    @Test
    public void testGetSelectQuery() throws Exception {
        assertEquals("SELECT id,name,description,ingredients,price,quantity,category_id,image FROM restaurant.dish", dao.getSelectQuery());
    }

    @Test
    public void testGetCreateQuery() throws Exception {
        assertEquals("INSERT INTO restaurant.dish (name,description,ingredients,price,quantity,category_id,image) VALUES (?,?,?,?,?,?,?)", dao.getCreateQuery());
    }

    @Test
    public void testGetUpdateQuery() throws Exception {
        assertEquals("UPDATE restaurant.dish SET name = ?,description = ?,ingredients = ?,price = ?,quantity = ?,category_id = ?,image = ? WHERE id = ?",
                dao.getUpdateQuery());
    }

    @Test
    public void testGetDeleteQuery() throws Exception {
        assertEquals("DELETE FROM restaurant.dish WHERE id=?", dao.getDeleteQuery());
    }

    @Test
    public void testParseResultSet() throws Exception {
        connection = pool.getConnection();
        List<Dish> list;
        String sql = dbBundle.getString("DISH.FROM_CATEGORY");
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
        Dish dish = new Dish();
        String sql = dbBundle.getString("DISH.INSERT");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForInsert(st, dish);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForUpdate() throws Exception {
        connection = pool.getConnection();
        Dish dish = new Dish();
        String sql = dbBundle.getString("DISH.UPDATE");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForUpdate(st, dish);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testGetAllFromCategory() throws Exception {
        List<Dish> list = dao.getAllFromCategory(1l);
        assertNotNull(list);
    }
}