package com.epam.restaurant.dao;

import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.factory.DaoFactory;

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
public abstract class AbstractSqlDao<T,PK extends Long> implements GenericDao<T, PK> {

    /**
     * Connection to database
     */
    protected DaoFactory parentFactory;

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    public AbstractSqlDao(DaoFactory parentFactory) {
        this.parentFactory = parentFactory;
    }

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;


    @Override
    public T persist(T object) throws SQLException {
        T persistInstance;
        try (Connection connection = pool.getConnection()) {
            // Добавляем запись
            String sql = getCreateQuery();
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On persist modify more then 1 record: " + count);
            }

            // get last modify record
            sql = getSelectQuery() + " WHERE id = last_insert_id();";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            //todo спросить, ведь вроде не надо освобождать, аннотации сами регламентируют
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new SQLException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        }catch (ConnectionPoolException |SQLException e) {
            throw new SQLException(e);//TODO!!!
        }

        return persistInstance;
    }

    @Override
    public T getByPK(PK key) throws SQLException {
        List<T> list;
        try (Connection connection = pool.getConnection()) {
            String sql = getSelectQuery();
            sql += " WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.valueOf(String.valueOf(key)));//TODO Im not sure
            ResultSet rs = statement.executeQuery();

            //todo см. пред. метод
            //parentFactory.putContext(connection);

            list = parseResultSet(rs);

            if (list == null || list.size() == 0) {
                throw new SQLException("Record with PK = " + key + " not found.");
            }
            if (list.size() > 1) {
                throw new SQLException("Received more than one record.");
            }
        }catch (ConnectionPoolException |SQLException e) {
            throw new SQLException(e);//TODO!!!
        }

        return list.iterator().next();
    }

    @Override
    public void update(T object) throws SQLException {
        String sql = getUpdateQuery();
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();

            if (count != 1) {
                throw new SQLException("On update modify more then 1 record: " + count);
            }
        }catch (ConnectionPoolException |SQLException e) {
            throw new SQLException(e);//TODO!!!
        }

    }

    @Override
    public void delete(T object) throws SQLException {
        String sql = getDeleteQuery();
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            //statement.setObject(1, object.getId());//TODO

            int count = statement.executeUpdate();


            if (count != 1) {
                throw new SQLException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        }catch (ConnectionPoolException |SQLException e) {
            throw new SQLException(e);//TODO!!!
        }
    }

    @Override
    public List<T> getAll() throws SQLException {
        List<T> list;
        String sql = getSelectQuery();
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            list = parseResultSet(rs);
        }catch (ConnectionPoolException |SQLException e) {
            throw new SQLException(e);//TODO!!!
        }

        return list;
    }
}