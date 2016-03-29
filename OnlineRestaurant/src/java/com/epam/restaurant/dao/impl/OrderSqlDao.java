package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.restaurant.dao.name.ParameterName.*;

/**
 * Dao implementation for MySQL database and Order entity.
 */
public class OrderSqlDao extends AbstractSqlDao<Order, Long> {
    private static final Logger LOGGER = Logger.getLogger(OrderSqlDao.class);

    private ResourceBundle dbBundle = ResourceBundle.getBundle(BUNDLE);

    /**
     * Connection to database
     */
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private final static OrderSqlDao instance = new OrderSqlDao();

    public static GenericDao getInstance() {
        return instance;
    }

    private class PersistOrder extends Order {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString(ORDER_SELECT);
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString(ORDER_INSERT);
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString(ORDER_UPDATE);
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString(ORDER_DELETE);
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Order> result = new LinkedList<>();

        try {
            while (rs.next()) {
                PersistOrder order = new PersistOrder();
                order.setId(rs.getLong(ID));
                order.setUserId(rs.getLong(USER_ID));
                order.setCreatedAt(rs.getDate(CREATED_AT));
                order.setTotal(rs.getBigDecimal(TOTAL));
                order.setPaid(rs.getBoolean(PAID));
                result.add(order);
            }
            LOGGER.info("ResultSet parsed");
        } catch (SQLException e) {
            throw new DaoException("Exception in parseResultSet method", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws DaoException {
        try {
            statement.setLong(1, object.getUserId());
            statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
            statement.setBigDecimal(3, object.getTotal());
            statement.setBoolean(4, object.isPaid());
            LOGGER.info("Statement for insert prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForInsert method");
            throw new DaoException("Exception in prepareStatementForInsert method", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws DaoException {
        try {
            statement.setLong(1, object.getUserId());
            statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
            statement.setBigDecimal(3, object.getTotal());
            statement.setBoolean(4, object.isPaid());
            statement.setLong(5, object.getId());
            LOGGER.info("Statement for update prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForUpdate method");
            throw new DaoException("Exception in prepareStatementForUpdate method", e);
        }

    }

    @Override
    public Order create() throws DaoException {
        Order order = new Order();
        return persist(order);
    }

    public Order getByUserId(Long name) throws DaoException {
        List<Order> list;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            String sql = dbBundle.getString(ORDER_FROM_USER_ID);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, name);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

            if (list == null || list.size() == 0) {
                return null;
            }

            if (list.size() > 1) {
                LOGGER.error("Received more than one record");
                throw new DaoException("Received more than one record.");
            }
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
                throw new DaoException("Dao Exception",e);
            }
        }

        return list.iterator().next();
    }
}
