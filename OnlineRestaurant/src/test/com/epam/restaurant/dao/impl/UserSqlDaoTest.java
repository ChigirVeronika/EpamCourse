package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals(dbBundle.getString("USER.SELECT"),"SELECT id, name, surname, login, password, email, role, pay_card_id FROM restaurant.user");
    }

    @Test
    public void testGetCreateQuery() throws Exception {
        assertEquals(dbBundle.getString("USER.INSERT"),"INSERT INTO restaurant.user (name, surname, login, password, email, role, pay_card_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
    }

    @Test
    public void testGetUpdateQuery() throws Exception {
        assertEquals(dbBundle.getString("USER.UPDATE"),"UPDATE restaurant.user SET name = ?, surname = ?, login = ?, password  = ?, email = ?, role = ?, pay_card_id = ? WHERE id = ?");
    }

    @Test
    public void testGetDeleteQuery() throws Exception {
        assertEquals(dbBundle.getString("USER.DELETE"),"DELETE FROM restaurant.user WHERE id = ?");
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
        User user=new User("peter","petrov","peter@mail.ru","3","peter","321");
        user.setId(17);
        String sql = dbBundle.getString("USER.UPDATE");
        PreparedStatement st = connection.prepareStatement(sql);
        userSqlDao.prepareStatementForUpdate(st,user);
        assertNotNull(st);
        pool.returnConnection(connection);


        connection = pool.getConnection();
        //not included address
        String s1=st.toString().substring(46);
        String sql2 = "UPDATE restaurant.user SET name = 'peter', surname = 'petrov', login = 'peter', password  = '321', email = 'peter@mail.ru', role = 'USER', pay_card_id = '3' WHERE id = 17";
        PreparedStatement st2 = connection.prepareStatement(sql2);
        String s2=st2.toString().substring(46);
        assertEquals(s1,s2);
        pool.returnConnection(connection);
    }

    @Test
    public void testPrepareStatementForInsert() throws Exception {
        connection = pool.getConnection();
        User user=new User("peter","petrov","peter@mail.ru","3","peter","321");
        String sql = dbBundle.getString("USER.INSERT");
        PreparedStatement st = connection.prepareStatement(sql);
        userSqlDao.prepareStatementForInsert(st,user);
        assertNotNull(st);

        //not included address
        String s1=st.toString().substring(46);
        String sql2 = "INSERT INTO restaurant.user (name, surname, login, password, email, role, pay_card_id) VALUES ('peter', 'petrov', 'peter', '321', 'peter@mail.ru', 'USER', '3')";
        PreparedStatement st2 = connection.prepareStatement(sql2);
        String s2=st2.toString().substring(46);
        assertEquals(s1,s2);
        pool.returnConnection(connection);
    }

    @Test
    public void testGetByLogin() throws Exception {
        User user = userSqlDao.getByLogin("vera");
        assertNotNull(user);
        assertEquals("vera",user.getName());
        assertEquals("chigir",user.getSurname());
        assertEquals("vera",user.getLogin());
        assertEquals("123",user.getHash());
        assertEquals("test",user.getEmail());
        assertEquals("ADMIN",user.getRole().toString());
        assertEquals("1",user.getPayCard());
    }

    @Test
    public void testGetByPK() throws DaoException {
        Long l = new Long(5L);
        User user = userSqlDao.getByPK(l);
        assertNotNull(user);
        assertEquals("vera",user.getName());
        assertEquals("chigir",user.getSurname());
        assertEquals("vera",user.getLogin());
        assertEquals("123",user.getHash());
        assertEquals("test",user.getEmail());
        assertEquals("ADMIN",user.getRole().toString());
        assertEquals("1",user.getPayCard());
    }

    @Test
    public void testGetAll() throws DaoException {
        List<User> list = userSqlDao.getAll();
        assertNotNull(list);
    }
}