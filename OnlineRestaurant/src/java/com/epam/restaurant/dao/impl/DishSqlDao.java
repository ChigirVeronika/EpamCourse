package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.restaurant.dao.name.ParameterName.*;

/**
 * Dao implementation for MySQL database and Dish entity.
 */
public class DishSqlDao extends AbstractSqlDao<Dish, Long> {
    private static final Logger LOGGER = Logger.getLogger(DishSqlDao.class);

    private ResourceBundle dbBundle = ResourceBundle.getBundle(BUNDLE);

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
        return dbBundle.getString(DISH_SELECT);
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString(DISH_INSERT);
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString(DISH_UPDATE);
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString(DISH_DELETE);
    }

    @Override
    protected List<Dish> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Dish> result = new LinkedList<>();

        try {
            while (rs.next()) {
                PersistDish dish = new PersistDish();
                dish.setId(rs.getLong(ID));
                dish.setName(rs.getString(NAME));
                dish.setDescription(rs.getString(DESCRIPTION));
                dish.setIngredients(rs.getString(INGREDIENTS));
                dish.setPrice(rs.getBigDecimal(PRICE));
                dish.setQuantity(rs.getInt(QUANTITY));
                dish.setCategoryId(rs.getLong(CATEGORY_ID));
                dish.setImage(rs.getString(IMAGE));
                result.add(dish);
            }
            LOGGER.info("ResultSet parsed");
        } catch (SQLException e) {
            throw new DaoException("Exception in parseResultSet method", e);
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
            LOGGER.info("Statement for insert prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForInsert method");
            throw new DaoException("Exception in prepareStatementForInsert method", e);
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
            LOGGER.info("Statement for update prepared.");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForUpdate method");
            throw new DaoException("Exception in prepareStatementForUpdate method", e);
        }
    }

    @Override
    public Dish create() throws DaoException {
        Dish dish = new Dish();
        return persist(dish);
    }

    /**
     * Get all dishes from concrete category
     *
     * @param key category id
     * @return list of dishes of concrete category
     * @throws DaoException
     */
    public List<Dish> getAllFromCategory(Long key) throws DaoException {
        List<Dish> result;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            String sql = dbBundle.getString(DISH_FORM_CATEGORY);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, key);
            ResultSet rs = statement.executeQuery();
            result = parseResultSet(rs);
            if (result == null) {
                return Collections.emptyList();
            }
            LOGGER.info("Method getAllCategories executed");
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
                throw new DaoException("DaoException", e);
            }
        }
        return result;
    }
}
