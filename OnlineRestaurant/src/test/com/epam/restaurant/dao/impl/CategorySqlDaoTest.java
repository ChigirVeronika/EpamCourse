package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.entity.Category;
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
public class CategorySqlDaoTest {

    CategorySqlDao dao = (CategorySqlDao) CategorySqlDao.getInstance();
    ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");
    ConnectionPool pool = ConnectionPoolImpl.getInstance();
    Connection connection;

    @Test
    public void testGetSelectQuery() throws Exception {
        assertEquals("SELECT id,name,description FROM restaurant.category", dao.getSelectQuery());
    }

    @Test
    public void testGetCreateQuery() throws Exception {
        assertEquals("INSERT INTO restaurant.category (name,description) VALUES (?, ?)", dao.getCreateQuery());
    }

    @Test
    public void testGetUpdateQuery() throws Exception {
        assertEquals("UPDATE restaurant.category SET name = ?,description=? WHERE id = ?", dao.getUpdateQuery());
    }

    @Test
    public void testGetDeleteQuery() throws Exception {
        assertEquals("DELETE FROM restaurant.category WHERE id = ?", dao.getDeleteQuery());
    }

    @Test
    public void testParseResultSet() throws Exception {
        connection = pool.getConnection();
        List<Category> list;
        String sql = dbBundle.getString("CATEGORY.WITH_NAME");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, 26L);
        ResultSet rs = statement.executeQuery();

        list = dao.parseResultSet(rs);
        assertNotNull(list);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForUpdate() throws Exception {
        connection = pool.getConnection();
        Category category = new Category();
        String sql = dbBundle.getString("CATEGORY.UPDATE");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForUpdate(st, category);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForInsert() throws Exception {
        connection = pool.getConnection();
        Category category = new Category();
        String sql = dbBundle.getString("CATEGORY.INSERT");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForInsert(st, category);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testGetByName() throws Exception {
        Category c = dao.getByName("Soups");
        assertNotNull(c);
    }
}