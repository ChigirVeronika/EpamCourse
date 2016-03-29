package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.Category;
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
 * Dao implementation for MySQL database and Category entity.
 */
public class CategorySqlDao extends AbstractSqlDao<Category, Long> {
    private static final Logger LOGGER = Logger.getLogger(CategorySqlDao.class);

    /**
     * Resource bundle with MySQL DB queries
     */
    private ResourceBundle dbBundle = ResourceBundle.getBundle(BUNDLE);

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    private final static CategorySqlDao instance = new CategorySqlDao();

    public static GenericDao getInstance() {
        return instance;
    }


    private class PersistCategory extends Category {
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return dbBundle.getString(CATEGORY_SELECT);
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString(CATEGORY_INSERT);
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString(CATEGORY_UPDATE);
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString(CATEGORY_DELETE);
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
                category.setId(rs.getInt(ID));
                category.setName(rs.getString(NAME));
                category.setDescription(rs.getString(DESCRIPTION));
                result.add(category);
            }
            LOGGER.info("ResultSet parsed");
        } catch (SQLException e) {
            throw new DaoException("Exception in parseResultSet method", e);
        }

        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Category object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            statement.setLong(3, object.getId());
            LOGGER.info("Statement for update prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForUpdate method");
            throw new DaoException("Exception in prepareStatementForUpdate method", e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Category object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getDescription());
            LOGGER.info("Statement for insert prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForInsert method");
            throw new DaoException("Exception in prepareStatementForInsert method", e);
        }
    }

    public Category getByName(String name) throws DaoException {
        List<Category> list;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            String sql = dbBundle.getString(CATEGORY_WITH_NAME);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            LOGGER.info("Method getByName executed");

            if (list == null || list.size() == 0) {
                return null;
            }
            if (list.size() > 1) {
                LOGGER.error("Received more than one record");
                throw new DaoException("Received more than one record");
            }
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
                throw new DaoException("Dao Exception", e);
            }
        }
        return list.iterator().next();
    }
}

