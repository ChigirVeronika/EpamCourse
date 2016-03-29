package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.OrderDish;
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
 * Dao implementation for MySQL database and OrderDish entity.
 */
public class OrderDishSqlDao extends AbstractSqlDao<OrderDish, Long> {
    private static final Logger LOGGER = Logger.getLogger(OrderSqlDao.class);

    private ResourceBundle dbBundle = ResourceBundle.getBundle(BUNDLE);

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private final static OrderDishSqlDao instance = new OrderDishSqlDao();

    public static GenericDao getInstance() {
        return instance;
    }

    private class PersistOrderDish extends OrderDish {
        public void setId(long id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString(ORDER_DISH_SELECT);
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString(ORDER_DISH_INSERT);
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString(ORDER_DISH_UPDATE);
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString(ORDER_DISH_DELETE);
    }

    @Override
    protected List<OrderDish> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<OrderDish> result = new LinkedList<>();

        try {
            while (rs.next()) {
                PersistOrderDish orderDish = new PersistOrderDish();
                orderDish.setId(rs.getLong(ID));
                orderDish.setDishId(rs.getLong(DISH_ID));
                orderDish.setOrderId(rs.getLong(ORDER_ID));
                orderDish.setQuantity(rs.getInt(QUANTITY));
                result.add(orderDish);
            }
            LOGGER.info("ResultSet parsed");
        } catch (SQLException e) {
            throw new DaoException("Exception in parseResultSet method", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderDish object) throws DaoException {
        try {
            statement.setLong(1, object.getDishId());
            statement.setLong(2, object.getOrderId());
            statement.setInt(3, object.getQuantity());
            LOGGER.info("Statement for insert prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForInsert method");
            throw new DaoException("Exception in prepareStatementForInsert method", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderDish object) throws DaoException {
        try {
            statement.setLong(1, object.getDishId());
            statement.setLong(2, object.getOrderId());
            statement.setInt(3, object.getQuantity());
            statement.setLong(4, object.getId());
            LOGGER.info("Statement for update prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForUpdate method");
            throw new DaoException("Exception in prepareStatementForUpdate method", e);
        }
    }

    @Override
    public OrderDish create() throws DaoException {
        OrderDish orderDish = new OrderDish();
        return orderDish;
    }

    public List<OrderDish> getAllFromOrder(Long key) throws DaoException {
        List<OrderDish> result;

        Connection connection = null;
        try {
            connection = pool.getConnection();
            String sql = dbBundle.getString(ORDER_DISH_FORM_ORDER);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, key);
            ResultSet rs = statement.executeQuery();
            result = parseResultSet(rs);
            if (result == null) {
                return Collections.emptyList();
            }
            LOGGER.info("Method getAllFromOrder executed");
        } catch (ConnectionPoolException | SQLException e) {
            LOGGER.error("Exception");
            throw new DaoException("Exception", e);
        } finally {
            try {
                if (connection != null) {
                    pool.returnConnection(connection);
                    LOGGER.info("Connection returned ");
                }
            } catch (ConnectionPoolException e) {
                LOGGER.error("Exception during returning connection");
                throw new DaoException("Exception", e);
            }
        }
        return result;
    }
}
