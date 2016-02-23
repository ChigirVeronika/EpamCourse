package com.epam.restaurant.dao;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;

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
public abstract class AbstractSqlDao<T extends Identified<PK>,PK extends Long> implements GenericDao<T, PK> {

    //protected SqlDaoFactory parentFactory;

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


    @Override
    public T persist(T object) throws DaoException {
        T persistInstance;
        try (Connection connection = pool.getConnection()) {
            // Добавляем запись
            String sql = getCreateQuery();
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On persist modify more than 1 record: " + count);
            }

            // get last modify record
            sql = getSelectQuery() + " WHERE id = last_insert_id();";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            //todo спросить, ведь вроде не надо освобождать, аннотации сами регламентируют
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new DaoException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        }catch (ConnectionPoolException |SQLException e) {
            throw new DaoException("Exception",e);
        }

        return persistInstance;
    }

    @Override
    public T getByPK(PK key) throws DaoException {
        List<T> list;
        try (Connection connection = pool.getConnection()) {
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
        }catch (ConnectionPoolException |SQLException e) {
            throw new DaoException("Exception",e);
        }

        return list.iterator().next();
    }

    @Override
    public void update(T object) throws DaoException {
        String sql = getUpdateQuery();
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();

            if (count != 1) {
                throw new DaoException("On update modify more than 1 record: " + count);
            }
        }catch (ConnectionPoolException |SQLException e) {
            throw new DaoException("Exception",e);
        }

    }

    @Override
    public void delete(T object) throws DaoException{
        String sql = getDeleteQuery();
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setObject(1, object.getId());

            int count = statement.executeUpdate();


            if (count != 1) {
                throw new DaoException("On delete modify more than 1 record: " + count);
            }
            statement.close();
        }catch (ConnectionPoolException |SQLException e) {
            throw new DaoException("Exception",e);
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> list;
        String sql = getSelectQuery();

        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        }catch (ConnectionPoolException |SQLException e) {
            throw new DaoException("Exception",e);
        }

        return list;
    }
}