package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.OrderDish;
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
 * Dao implementation for MySQL database and OrderDish entity.
 */
public class OrderDishSqlDao extends AbstractSqlDao<OrderDish, Long> {

    private ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private final static OrderDishSqlDao instance = new OrderDishSqlDao();

    public static GenericDao getInstance(){return instance;}

    private class PersistOrderDish extends OrderDish{
        public void setId(long id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString("ORDER_DISH.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("ORDER_DISH.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("ORDER_DISH.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("ORDER_DISH.DELETE");
    }

    @Override
    protected List<OrderDish> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<OrderDish> result = new LinkedList<>();

        try {
            while (rs.next()){
                PersistOrderDish orderDish = new PersistOrderDish();
                orderDish.setId(rs.getLong("id"));
                orderDish.setDishId(rs.getLong("dish_id"));
                orderDish.setOrderId(rs.getLong("order_id"));
                orderDish.setQuantity(rs.getInt("quantity"));
                result.add(orderDish);
            }
        } catch (SQLException e) {
            throw new DaoException("");
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderDish object) throws DaoException {
        try {
            statement.setLong(1, object.getDishId());
            statement.setLong(2, object.getOrderId());
            statement.setInt(3, object.getQuantity());
        }catch (SQLException e) {
            throw new DaoException("");
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderDish object) throws DaoException {
        try{
            statement.setLong(1, object.getDishId());
            statement.setLong(2, object.getOrderId());
            statement.setInt(3, object.getQuantity());
            statement.setLong(4, object.getId());
        }catch (SQLException e) {
            throw new DaoException("");
        }
    }

    @Override
    public OrderDish create() throws DaoException {
        OrderDish orderDish = new OrderDish();
        return orderDish;
    }

    public List<OrderDish> getAllFromOrder(Long key) throws DaoException {
        List<OrderDish> result;

        Connection connection=null;
        try  {
            connection = pool.getConnection();
            String sql = dbBundle.getString("ORDER_DISH.FROM_ORDER");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, key);
            ResultSet rs = statement.executeQuery();
            result = parseResultSet(rs);
            if(result==null){
                return Collections.emptyList();
            }
        }catch (ConnectionPoolException |SQLException e) {
            throw new DaoException("Dao Exception",e);
        }finally {
            try {
                if(connection != null) {
                    pool.returnConnection(connection);
                }
            } catch (ConnectionPoolException e) {
                throw new DaoException("",e);
            }
        }

        return result;
    }
}
