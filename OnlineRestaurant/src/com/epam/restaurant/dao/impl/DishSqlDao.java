package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * .
 */
public class DishSqlDao extends AbstractSqlDao<Dish, Long> {

    private ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private class PersistDish extends Dish {
        public void setId(Long id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    protected List<Dish> parseResultSet(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Dish object) throws DaoException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Dish object) throws DaoException {

    }

    @Override
    public Dish create() throws DaoException {
        return null;
    }

    @Override
    public Dish getByName(String name) throws DaoException {
        return null;
    }

    @Override
    public List<Dish> getAllFromRecord(Long key) throws DaoException {
        List<Dish> result;
        try (Connection connection = pool.getConnection()) {

            String sql = dbBundle.getString("DISH.FROM_CATEGORY");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, key);
            ResultSet rs = statement.executeQuery();
            result = parseResultSet(rs);
            if(result==null){
                return Collections.emptyList();
            }
            return result;
        }catch (ConnectionPoolException |SQLException e) {
            throw new DaoException("Dao Exception",e);
        }
    }
}
