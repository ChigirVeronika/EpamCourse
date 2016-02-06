package com.epam.restaurant.dao.pool.connect;

import com.epam.restaurant.dao.pool.config.DAOConfigManager;
import com.epam.restaurant.exception.ProjectException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

//Connection Pool, ��� ���������� � ��
public class ConnectionPool {

    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static ConnectionPool instance;

    private BlockingQueue<Connection> connections;
    private boolean working = true;

    private ConnectionPool() {
        initialize();
    }

    public static ConnectionPool getInstance() {
        if(instance==null) {
            try{
                reentrantLock.lock();
                if(instance==null) {
                    instance = new ConnectionPool();//pul created
                }
            } finally {
                reentrantLock.unlock();
            }
        }

        return instance;
    }

    public Connection getConnection() throws ProjectException {//todo
        Connection connection = null;
        if (working) {
            try {
                connection = connections.take();
            } catch (InterruptedException e) {
                throw new ProjectException ("DAO Exception",e);//todo
            }
        }

        return connection;
    }

    public void returnConnection(Connection connection) throws ProjectException  {
        try {
            if (!connection.isClosed()) {
                if (!connections.offer(connection)) {
                   // LOGGER.error("Error while trying to return the connection to the pool");
                }
            } else {
               // LOGGER.error("Connection has been closed");
            }
        } catch (SQLException e) {
          //  LOGGER.error("SQL Exception " + e);
            throw new ProjectException ("DAO Exception",e);
        }
    }

    public void releasePool() {
        working = false;
        Connection connection = null;
        int realSize = Integer.parseInt(DAOConfigManager.getProperty(DAOConfigManager.POOL_SIZE));
        while (realSize > 0) {
            try {
                connection = connections.take();
            } catch (InterruptedException e) {
               // LOGGER.error("Error while trying to take the connection from the pool");
            }
            if (connection != null) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                  //  LOGGER.error("SQL Exception " + e);
                }
                realSize--;
            }
        }
        //LOGGER.info("Pool has been released");
    }

    private void initialize(){
        String url = DAOConfigManager.getProperty(DAOConfigManager.URL);
        String user = DAOConfigManager.getProperty(DAOConfigManager.USER);
        String password = DAOConfigManager.getProperty(DAOConfigManager.PASS);
        int size = Integer.parseInt(DAOConfigManager.getProperty(DAOConfigManager.POOL_SIZE));

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connections = new ArrayBlockingQueue<>(size);
            for (int i = 0; i < size; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connections.offer(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

      //  LOGGER.info("Pool has been initialized");
    }
}
