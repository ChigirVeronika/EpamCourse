package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.entity.News;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.restaurant.dao.name.ParameterName.*;

/**
 * Dao implementation for MySQL database and News entity.
 */
public class NewsSqlDao extends AbstractSqlDao<News, Long> {
    private static final Logger LOGGER = Logger.getLogger(NewsSqlDao.class);

    private ResourceBundle dbBundle = ResourceBundle.getBundle(BUNDLE);

    private final static NewsSqlDao instance = new NewsSqlDao();

    public static GenericDao getInstance() {
        return instance;
    }

    private class PersistNews extends News {
        public void setId(Long id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return dbBundle.getString(NEWS_SELECT);
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString(NEWS_INSERT);
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString(NEWS_UPDATE);
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString(NEWS_DELETE);
    }

    @Override
    protected List<News> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<News> result = new LinkedList<>();

        try {
            while (rs.next()) {
                PersistNews news = new PersistNews();
                news.setId(rs.getLong(ID));
                news.setName(rs.getString(NAME));
                news.setDate(rs.getDate(DATE));
                news.setContent(rs.getString(CONTENT));
                news.setImage(rs.getString(IMAGE));
                result.add(news);
            }
            LOGGER.info("ResultSet parsed");
        } catch (SQLException e) {
            throw new DaoException("Exception in parseResultSet method", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, News object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setDate(2, new java.sql.Date(object.getDate().getTime()));
            statement.setString(3, object.getContent());
            statement.setString(4, object.getImage());
            LOGGER.info("Statement for insert prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForInsert method");
            throw new DaoException("Exception in prepareStatementForInsert method", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, News object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setDate(2, new java.sql.Date(object.getDate().getTime()));
            statement.setString(3, object.getContent());
            statement.setString(4, object.getImage());
            statement.setLong(5, object.getId());
            LOGGER.info("Statement for update prepared");
        } catch (SQLException e) {
            LOGGER.error("Exception in prepareStatementForUpdate method");
            throw new DaoException("Exception in prepareStatementForUpdate method", e);
        }
    }

    @Override
    public News create() throws DaoException {
        News news = new News();
        return persist(news);
    }
}
