package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * .
 */
public class CategorySqlDao extends AbstractSqlDao<Category, Long> {

    /**
     * Resource bundle with MySQL DB queries
     */
    private ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private final static CategorySqlDao instance = new CategorySqlDao();

    public static GenericDao getInstance(){
        return instance;
    }


    private class PersistCategory extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return dbBundle.getString("CATEGORY.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("CATEGORY.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("CATEGORY.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("CATEGORY.DELETE");
    }

    @Override
    public Category create() throws DaoException {
        Category category = new Category();
        return persist(category);
    }

    @Override
    protected List<Category> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Category> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistCategory category = new PersistCategory();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                result.add(category);
            }
        }catch(SQLException e){
            throw new DaoException("Exception",e);
        }

        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Category object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            statement.setLong(3, object.getId());
        } catch (SQLException e) {
            throw new DaoException("Exception",e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Category object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
        } catch (SQLException e) {
            throw new DaoException("Exception",e);
        }
    }

    public Category getByName(String name) throws DaoException {
        List<Category> list;
        try (Connection connection = pool.getConnection()) {
            String sql = dbBundle.getString("CATEGORY.WITH_NAME");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

            if (list == null || list.size() == 0) {
                return null;
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
    public List<Category> getAllFromRecord(Long id) throws DaoException {
        //// TODO: 22.02.2016  
        List<Category> result;
        String sql = dbBundle.getString("DISH.FROM_CATEGORY");
        try (Connection connection = pool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,id);
            ResultSet rs = statement.executeQuery();
            result = parseResultSet(rs);
        }catch (ConnectionPoolException |SQLException e) {
            throw new DaoException("Exception",e);
        }
        return result;
    }
}

