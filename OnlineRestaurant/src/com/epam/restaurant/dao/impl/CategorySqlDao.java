package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.factory.DaoFactory;
import com.epam.restaurant.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Вероника on 09.02.2016.
 */
public class CategorySqlDao extends AbstractSqlDao<Category, Long> {

    /**
     * Resource bundle with MySQL DB queries
     */
    private ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();


    private class PersistCategory extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }

    public CategorySqlDao(DaoFactory parentFactory) {
        super(parentFactory);
    }


    @Override
    public String getSelectQuery() {
        return dbBundle.getString("CATEGORIES.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("CATEGORIES.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("CATEGORIES.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("CATEGORIES.DELETE");
    }

    @Override
    public Category create() throws SQLException {
        Category category = new Category();
        return persist(category);
    }

    @Override
    protected List<Category> parseResultSet(ResultSet rs) throws SQLException {
        LinkedList<Category> result = new LinkedList<>();

        while (rs.next()) {
            PersistCategory category = new PersistCategory();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setDescription(rs.getString("description"));
            result.add(category);
        }

        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Category object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getDescription());
        statement.setInt(3, object.getId());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Category object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getDescription());
    }

    /**
     * Get category from MySQL database with specific name
     * @param name category name
     * @return category with this name or null (if doesn't exist)
     * @throws SQLException
     */
    public Category getByName(String name) throws SQLException {
        List<Category> list;
        try (Connection connection = pool.getConnection()) {
            String sql = dbBundle.getString("CATEGORIES.WITH_NAME");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);

            if (list == null || list.size() == 0) {
                return null;
            }
            if (list.size() > 1) {
                throw new SQLException("Received more than one record.");
            }
        }catch (ConnectionPoolException |SQLException e) {
            throw new SQLException(e);//TODO!!!
        }
        return list.iterator().next();
    }
}

