package com.epam.restaurant.dao.connectionpool;

import com.epam.restaurant.dao.connectionpool.config.DAOConfigManager;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public interface ConnectionPool {

    Connection getConnection() throws ConnectionPoolException ;

    void returnConnection(Connection connection) throws ConnectionPoolException;

    @PreDestroy
    void releasePool() throws ConnectionPoolException;

    @PostConstruct
    void initialize() throws ConnectionPoolException;
}
