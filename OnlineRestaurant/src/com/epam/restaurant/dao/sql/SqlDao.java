package com.epam.restaurant.dao.sql;

import java.util.List;

/**
 * Created by Вероника on 22.01.2016.
 */
public interface SqlDao {
    List<Object> parse(String resourceName) throws SqlDaoException;
}
