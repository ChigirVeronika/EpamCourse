package com.epam.restaurant.dao.connectionpool.exception;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.testng.annotations.Test;

import java.sql.Connection;

/**
 * Exception test for ConnectionPool implementation.
 */
public class ConnectionPoolExceptionTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testConnectionPoolException() throws ConnectionPoolException {
        ConnectionPool pool = ConnectionPoolImpl.getInstance();

        exception.expect(ConnectionPoolException.class);
        Connection connection = pool.getConnection();
    }
}