package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Вероника on 21.02.2016.
 */
public class OrderDishSqlDao extends AbstractSqlDao<OrderDish, Long> {

    private final static OrderDishSqlDao instance = new OrderDishSqlDao();

    public static GenericDao getInstance(){return instance;}

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
    protected List<OrderDish> parseResultSet(ResultSet rs) throws DaoException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderDish object) throws DaoException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderDish object) throws DaoException {

    }

    @Override
    public OrderDish create() throws DaoException {
        return null;
    }

    @Override
    public OrderDish getByName(String name) throws DaoException {
        return null;
    }

    @Override
    public List<OrderDish> getAllFromRecord(Long key) throws DaoException {
        return null;
    }
}
