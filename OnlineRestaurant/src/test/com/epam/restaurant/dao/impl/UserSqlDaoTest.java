package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.User;
import com.epam.restaurant.util.HashUtil;
import com.epam.restaurant.util.ValidationUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * User Dao Test.
 */
public class UserSqlDaoTest {

    UserSqlDao dao = (UserSqlDao) UserSqlDao.getInstance();
    ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");
    ConnectionPool pool = ConnectionPoolImpl.getInstance();
    Connection connection;

    @Test
    public void testGetSelectQuery() throws Exception {
        assertEquals("SELECT id, name, surname, login, password, email, role, pay_card_id FROM restaurant.user",
                dao.getSelectQuery());
    }

    @Test
    public void testGetCreateQuery() throws Exception {
        assertEquals("INSERT INTO restaurant.user (name, surname, login, password, email, role, pay_card_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
                dao.getCreateQuery());
    }

    @Test
    public void testGetUpdateQuery() throws Exception {
        assertEquals("UPDATE restaurant.user SET name = ?, surname = ?, login = ?, password  = ?, email = ?, role = ?, pay_card_id = ? WHERE id = ?",
                dao.getUpdateQuery());
    }

    @Test
    public void testGetDeleteQuery() throws Exception {
        assertEquals("DELETE FROM restaurant.user WHERE id = ?", dao.getDeleteQuery());
    }

    User user = new User("TestName", "TestSurname", "TestEmail", "TestPayCard", "TestLogin", "TestPassword");

    @Test
    public void testParseResultSet() throws Exception {
        connection = pool.getConnection();
        List<User> list;
        String sql = dbBundle.getString("USER.WITH_LOGIN");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "vera");
        ResultSet rs = statement.executeQuery();

        list = dao.parseResultSet(rs);
        assertNotNull(list);
        pool.returnConnection(connection);

    }

    @Test
    public void testPrepareStatementForUpdate() throws Exception {
        connection = pool.getConnection();
        User user = new User("peter", "petrov", "peter@mail.ru", "3", "peter", "321");
        user.setId(17);
        String sql = dbBundle.getString("USER.UPDATE");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForUpdate(st, user);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForInsert() throws Exception {
        connection = pool.getConnection();
        User user = new User("peter", "petrov", "peter@mail.ru", "3", "peter", "321");
        String sql = dbBundle.getString("USER.INSERT");
        PreparedStatement st = connection.prepareStatement(sql);
        dao.prepareStatementForInsert(st, user);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testGetByLogin() throws Exception {
        User user = dao.getByLogin("veronika");
        assertNotNull(user);
        assertEquals("veronika", user.getName());
        assertEquals("chigir", user.getSurname());
        assertEquals("veronika", user.getLogin());
        assertEquals("vera@gmail.com",user.getEmail());
        assertEquals("ADMIN", user.getRole().toString());
        assertEquals("1234123456785678", user.getPayCard());
    }

    @Test
    public void testGetByPK() throws DaoException {
        Long l = new Long(26L);
        User user = dao.getByPK(l);
        assertNotNull(user);
        assertEquals("veronika", user.getName());
        assertEquals("chigir", user.getSurname());
        assertEquals("veronika", user.getLogin());
        assertEquals("vera@gmail.com",user.getEmail());
        assertEquals("ADMIN", user.getRole().toString());
        assertEquals("1234123456785678", user.getPayCard());
    }

    @Test
    public void testGetAll() throws DaoException {
        List<User> list = dao.getAll();
        assertNotNull(list);
    }
}