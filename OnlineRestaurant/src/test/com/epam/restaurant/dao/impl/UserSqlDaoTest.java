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

    UserSqlDao userSqlDao = (UserSqlDao) UserSqlDao.getInstance();
    ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");
    ConnectionPool pool = ConnectionPoolImpl.getInstance();
    Connection connection;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetSelectQuery() throws Exception {
        assertEquals("SELECT id, name, surname, login, password, email, role, pay_card_id FROM restaurant.user", dbBundle.getString("USER.SELECT"));
    }

    @Test
    public void testGetCreateQuery() throws Exception {
        assertEquals("INSERT INTO restaurant.user (name, surname, login, password, email, role, pay_card_id) VALUES (?, ?, ?, ?, ?, ?, ?)", dbBundle.getString("USER.INSERT"));
    }

    @Test
    public void testGetUpdateQuery() throws Exception {
        assertEquals("UPDATE restaurant.user SET name = ?, surname = ?, login = ?, password  = ?, email = ?, role = ?, pay_card_id = ? WHERE id = ?", dbBundle.getString("USER.UPDATE"));
    }

    @Test
    public void testGetDeleteQuery() throws Exception {
        assertEquals("DELETE FROM restaurant.user WHERE id = ?", dbBundle.getString("USER.DELETE"));
    }

    User user = new User("TestName", "TestSurname", "TestEmail", "TestPayCard", "TestLogin", "TestPassword");

    @Test
    public void testCreate() throws DaoException, InvalidKeySpecException, NoSuchAlgorithmException {
        userSqlDao.persist(user);
        assertNotNull(user);
        assertEquals("TestName", user.getName());
        assertEquals("TestSurname", user.getSurname());
        assertEquals("TestEmail", user.getEmail());
    }

    @Test
    public void testParseResultSet() throws Exception {
        connection = pool.getConnection();
        List<User> list;
        String sql = dbBundle.getString("USER.WITH_LOGIN");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "vera");
        ResultSet rs = statement.executeQuery();

        list = userSqlDao.parseResultSet(rs);
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
        userSqlDao.prepareStatementForUpdate(st, user);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForInsert() throws Exception {
        connection = pool.getConnection();
        User user = new User("peter", "petrov", "peter@mail.ru", "3", "peter", "321");
        String sql = dbBundle.getString("USER.INSERT");
        PreparedStatement st = connection.prepareStatement(sql);
        userSqlDao.prepareStatementForInsert(st, user);
        assertNotNull(st);
        pool.returnConnection(connection);
    }

    @Test
    public void testGetByLogin() throws Exception {
        User user = userSqlDao.getByLogin("veronika");
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
        Long l = new Long(5L);
        User user = userSqlDao.getByPK(l);
        assertNotNull(user);
        assertEquals("vera", user.getName());
        assertEquals("chigir", user.getSurname());
        assertEquals("vera", user.getLogin());
        assertEquals("123", user.getHash());
        assertEquals("test", user.getEmail());
        assertEquals("ADMIN", user.getRole().toString());
        assertEquals("1", user.getPayCard());
    }

    @Test
    public void testGetAll() throws DaoException {
        List<User> list = userSqlDao.getAll();
        assertNotNull(list);
    }
}