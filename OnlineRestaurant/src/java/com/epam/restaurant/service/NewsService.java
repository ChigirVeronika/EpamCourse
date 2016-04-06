package com.epam.restaurant.service;

import com.epam.restaurant.dao.exception.DaoException;
import com.epam.restaurant.dao.factory.SqlDaoFactory;
import com.epam.restaurant.dao.impl.NewsSqlDao;
import com.epam.restaurant.entity.News;
import com.epam.restaurant.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * Perform service operations with news object.
 */
public class NewsService {

    /**
     * Dao for news dao objects
     */
    private static final NewsSqlDao newsDao = (NewsSqlDao) SqlDaoFactory.getInstance().getDao(SqlDaoFactory.DaoType.NEWS);

    private static NewsService instance = new NewsService();

    private NewsService() {
    }

    public static NewsService getInstance() {
        return instance;
    }

    /**
     * Get all news from news table
     *
     * @return list of news
     * @throws ServiceException
     */
    public List<News> getAllNews() throws ServiceException {
        try {
            return newsDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("NewsServiceException", e);
        }
    }

    /**
     * Create new record in data source with specific params
     *
     * @param name
     * @param date
     * @param content
     * @param image
     * @return
     * @throws ServiceException
     */
    public News create(String name, Date date, String content, String image)
            throws ServiceException {
        News news = new News(name, date, content, image);
        try {
            News persistNews = newsDao.persist(news);
            return persistNews;
        } catch (DaoException e) {
            throw new ServiceException("NewsServiceException", e);
        }
    }
}
