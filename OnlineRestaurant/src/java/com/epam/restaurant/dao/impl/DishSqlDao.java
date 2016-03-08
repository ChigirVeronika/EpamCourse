package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
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
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Dao implementation for MySQL database and Dish entity.
 */
public class DishSqlDao extends AbstractSqlDao<Dish, Long> {
    private ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private final static DishSqlDao instance = new DishSqlDao();

    public static GenericDao getInstance() {
        return instance;
    }

    private class PersistDish extends Dish {
        public void setId(Long id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString("DISH.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("DISH.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("DISH.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("DISH.DELETE");
    }

    @Override
    protected List<Dish> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Dish> result = new LinkedList<>();

        try {
            while (rs.next()) {
                PersistDish dish = new PersistDish();
                dish.setId(rs.getLong("id"));
                dish.setName(rs.getString("name"));
                dish.setDescription(rs.getString("description"));
                dish.setIngredients(rs.getString("ingredients"));
                dish.setPrice(rs.getBigDecimal("price"));
                dish.setQuantity(rs.getInt("quantity"));
                dish.setCategoryId(rs.getLong("category_id"));
                dish.setImage(rs.getString("image"));
                result.add(dish);
            }
        } catch (SQLException e) {
            throw new DaoException("DishDaoSql Exception");
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Dish object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            statement.setString(3, object.getIngredients());
            statement.setBigDecimal(4, object.getPrice());
            statement.setInt(5, object.getQuantity());
            statement.setLong(6, object.getCategoryId());
            statement.setString(7, object.getImage());
        } catch (SQLException e) {
            throw new DaoException("DishSqlDao Exception");
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Dish object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            statement.setString(3, object.getIngredients());
            statement.setBigDecimal(4, object.getPrice());
            statement.setInt(5, object.getQuantity());
            statement.setLong(6, object.getCategoryId());
            statement.setString(7, object.getImage());
            statement.setLong(8, object.getId());
        } catch (SQLException e) {
            throw new DaoException("DishSqlDao Exception");
        }
    }

    @Override
    public Dish create() throws DaoException {
        Dish dish = new Dish();
        return persist(dish);
    }

    public List<Dish> getAllFromCategory(Long key) throws DaoException {
        List<Dish> result;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            String sql = dbBundle.getString("DISH.FROM_CATEGORY");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, key);
            ResultSet rs = statement.executeQuery();
            result = parseResultSet(rs);
            if (result == null) {
                return Collections.emptyList();
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Dao Exception");
        } finally {
            try {
                if (connection != null) {
                    pool.returnConnection(connection);
                }
            } catch (ConnectionPoolException e) {
                throw new DaoException("Dao Exception");
            }
        }
        return result;
    }
}
