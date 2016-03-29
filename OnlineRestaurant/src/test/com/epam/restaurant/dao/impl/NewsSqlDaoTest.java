package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.entity.News;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Вероника on 13.03.2016.
 */
public class NewsSqlDaoTest {

    NewsSqlDao dao = (NewsSqlDao) NewsSqlDao.getInstance();
    ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");
    ConnectionPool pool = ConnectionPoolImpl.getInstance();
    Connection connection;

    @Test
    public void testGetSelectQuery() throws Exception {
        assertEquals("SELECT id,name,date, content, image FROM restaurant.news", dao.getSelectQuery());
    }

    @Test
    public void testGetCreateQuery() throws Exception {
        assertEquals("INSERT INTO restaurant.news (name,date, content, image) VALUES (?,?,?,?)", dao.getCreateQuery());
    }

    @Test
    public void testGetUpdateQuery() throws Exception {
        assertEquals("UPDATE restaurant.news SET  name=?,date=?, content=?, image=? WHERE id=?", dao.getUpdateQuery());
    }

    @Test
    public void testGetDeleteQuery() throws Exception {
        assertEquals("DELETE FROM restaurant.news WHERE id=?", dao.getDeleteQuery());
    }

    @Test
    public void testPrepareStatementForInsert() throws Exception {
        connection = pool.getConnection();
        News news = new News("name",new Date(),"content","image");
        String sql = dbBundle.getString("NEWS.INSERT");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForInsert(st, news);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForUpdate() throws Exception {
        connection = pool.getConnection();
        News news = new News("name",new Date(),"content","image");
        String sql = dbBundle.getString("NEWS.UPDATE");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForUpdate(st, news);
        assertNotNull(st);
        pool.returnConnection(connection);
    }
}