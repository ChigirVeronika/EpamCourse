package com.epam.restaurant.dao.impl;

import com.epam.restaurant.dao.AbstractSqlDao;
import com.epam.restaurant.dao.GenericDao;
import com.epam.restaurant.dao.connectionpool.ConnectionPool;
import com.epam.restaurant.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.entity.News;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 */
public class NewsSqlDao extends AbstractSqlDao<News, Long> {
    private ResourceBundle dbBundle = ResourceBundle.getBundle("db.db");

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
        return dbBundle.getString("NEWS.SELECT");
    }

    @Override
    public String getCreateQuery() {
        return dbBundle.getString("NEWS.INSERT");
    }

    @Override
    public String getUpdateQuery() {
        return dbBundle.getString("NEWS.UPDATE");
    }

    @Override
    public String getDeleteQuery() {
        return dbBundle.getString("NEWS.DELETE");
    }

    @Override
    protected List<News> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<News> result = new LinkedList<>();

        try {
            while (rs.next()) {
                PersistNews news = new PersistNews();
                news.setId(rs.getLong("id"));
                news.setName(rs.getString("name"));
                news.setDate(rs.getDate("date"));
                news.setContent(rs.getString("content"));
                news.setImage(rs.getString("image"));
                result.add(news);
            }
        } catch (SQLException e) {
            throw new DaoException("DishDaoSql Exception");
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, News object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setDate(2, (Date) object.getDate());
            statement.setString(3, object.getContent());
            statement.setString(4, object.getImage());
        } catch (SQLException e) {
            throw new DaoException("DishSqlDao Exception");
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, News object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setDate(2, (Date) object.getDate());
            statement.setString(3, object.getContent());
            statement.setString(4, object.getImage());
            statement.setLong(5, object.getId());
        } catch (SQLException e) {
            throw new DaoException("DishSqlDao Exception");
        }
    }

    @Override
    public News create() throws DaoException {
        News news = new News();
        return persist(news);
    }
}
