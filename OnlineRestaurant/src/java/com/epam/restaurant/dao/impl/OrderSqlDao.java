package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Dao implementation for MySQL database and Order entity.
 */
public class OrderSqlDao extends AbstractSqlDao<Order, Long> {
    private ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");

    /**
     * Connection to database
     */
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private final static OrderSqlDao instance = new OrderSqlDao();

    public static GenericDao getInstance(){return instance;}

    private class PersistOrder extends Order {
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString("ORDER.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("ORDER.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("ORDER.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("ORDER.DELETE");
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Order> result = new LinkedList<>();

        try {
            while (rs.next()){
                PersistOrder order = new PersistOrder();
                order.setId(rs.getLong("id"));
                order.setUserId(rs.getLong("user_id"));
                order.setCreatedAt(rs.getDate("created_at"));
                order.setTotal(rs.getBigDecimal("total"));
                result.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("OrderSqlDao Exception",e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws DaoException {
        try {
            statement.setLong(1, object.getUserId());
            statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
            statement.setBigDecimal(3,object.getTotal());
        } catch (SQLException e) {
            throw new DaoException("OrderSqlDao Exception",e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws DaoException {
        try {
            statement.setLong(1, object.getUserId());
            statement.setDate(2, new java.sql.Date(object.getCreatedAt().getTime()));
            statement.setBigDecimal(3,object.getTotal());
            statement.setLong(4, object.getId());
        } catch (SQLException e) {
            throw new DaoException("OrderSqlDao Exception",e);
        }

    }

    @Override
    public Order create() throws DaoException {
        Order order = new Order();
        return persist(order);
    }

    public Order getByUserId(String name) throws DaoException {
        List<Order> list;
        Connection connection=null;
        try  {
            connection = pool.getConnection();
            String sql = dbBundle.getString("ORDER.FROM_USER_ID");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, Long.parseLong(name));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

        } catch (ConnectionPoolException|SQLException e) {
            throw new DaoException("OrderSqlDao Exception",e);
        }finally {
            try {
                if(connection != null) {
                    pool.returnConnection(connection);
                }
            } catch (ConnectionPoolException e) {
                throw new DaoException("",e);
            }
        }

        if (list == null || list.size() == 0) {
            return null;
        }

        if (list.size() > 1) {
            throw new DaoException("Received more than one record.");
        }

        return list.iterator().next();
    }
}
