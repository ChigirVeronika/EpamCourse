package com.epam.restaurant.dao.connectionpool;

import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;

/**
 * Interface describes connection pool.
 */
public interface ConnectionPool {

    Connection getConnection() throws ConnectionPoolException ;

    void returnConnection(Connection connection) throws ConnectionPoolException;

    @PreDestroy
    void releasePool() throws ConnectionPoolException;

    @PostConstruct
    void initialize() throws ConnectionPoolException;
}
