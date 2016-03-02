package com.epam.restaurant.dao.connectionpool.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;

import java.sql.Connection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Вероника on 02.03.2016.
 */
public class ConnectionPoolImplTest {

    ConnectionPoolImpl pool;
    Connection connection ;

    @Before
    @Test
    public void testInitialize() throws Exception {
        pool = mock(ConnectionPoolImpl.class);
        pool.initialize();
        assertNotNull(pool);
    }

    @Test
    @After
    public void testReturnConnection() throws Exception {
        if(connection!=null){
            pool.returnConnection(connection);
        }
        assertNull(connection);
    }

    @Test
    @After
    public void testReleasePool() throws Exception {
        pool.releasePool();
        assertNotNull(pool);
    }
}