package com.epam.restaurant.dao;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstarct class that perform basic CRUD operations with JDBC.
 *
 * @param <T>  persistance object type
 * @param <PK> primary key type
 */
public abstract class AbstractSqlDao<T extends Identified<PK>, PK extends Long> implements GenericDao<T, PK> {
    private static final Logger LOGGER = Logger.getLogger(AbstractSqlDao.class);
    /**
     * Connection to database
     */
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws DaoException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DaoException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DaoException;

    /**
     * @param object
     * @return
     * @throws DaoException
     */
    @Override
    public T persist(T object) throws DaoException {
        T persistInstance;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            // Добавляем запись
            String sql = getCreateQuery();
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                LOGGER.error("On persist modify more than 1 record: "+ count);
                throw new DaoException("On persist modify more than 1 record: " + count);
            }

            // get last modify record
            sql = getSelectQuery() + " WHERE id = last_insert_id();";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                LOGGER.error("Exception on findByPK new persist data");
                throw new DaoException("Exception on findByPK new persist data");
            }
            persistInstance = list.iterator().next();
            LOGGER.info("Method persist executed" +
                    "");
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("Exception");
            throw new DaoException("Exception", e);
        } finally {
            try {
                if (connection != null) {
                    pool.returnConnection(connection);
                    LOGGER.info("Connection returned");
                }
            } catch (ConnectionPoolException e) {
                LOGGER.error("Exception during returning connection");
                throw new DaoException("Exception", e);
            }
        }

        return persistInstance;
    }

    @Override
    public T getByPK(PK key) throws DaoException {
        List<T> list;
        Connection connection = null;
        try {
            connection = pool.getConnection();

            String sql = getSelectQuery();
            sql += " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(String.valueOf(key)));//TODO Im not sure
            ResultSet rs = statement.executeQuery();

            list = parseResultSet(rs);

            if (list == null || list.size() == 0) {
                throw new DaoException("Record with PK = " + key + " not found.");
            }
            if (list.size() > 1) {
                throw new DaoException("Received more than one record.");
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Exception");
        } finally {
            try {
                if (connection != null) {
                    pool.returnConnection(connection);
                }
            } catch (ConnectionPoolException e) {
                throw new DaoException("Exception");
            }
        }

        return list.iterator().next();
    }

    @Override
    public void update(T object) throws DaoException {
        String sql = getUpdateQuery();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();

            if (count != 1) {
                throw new DaoException("On update modify more than 1 record: " + count);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Exception");
        } finally {
            try {
                if (connection != null) {
                    pool.returnConnection(connection);
                }
            } catch (ConnectionPoolException e) {
                throw new DaoException("Exception");
            }
        }

    }

    @Override
    public void delete(T object) throws DaoException {
        String sql = getDeleteQuery();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setObject(1, object.getId());

            int count = statement.executeUpdate();


            if (count != 1) {
                throw new DaoException("On delete modify more than 1 record: " + count);
            }
            statement.close();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Exception");
        } finally {
            try {
                if (connection != null) {
                    pool.returnConnection(connection);
                }
            } catch (ConnectionPoolException e) {
                throw new DaoException("Exception");
            }
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> list;
        String sql = getSelectQuery();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Exception");
        } finally {
            try {
                if (connection != null) {
                    pool.returnConnection(connection);
                }
            } catch (ConnectionPoolException e) {
                throw new DaoException("Exception");
            }
        }

        return list;
    }
}