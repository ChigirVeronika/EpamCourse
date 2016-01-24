package main.com.epam.site.dao;

import java.util.List;

/**
 * Created by Вероника on 22.01.2016.
 */
public interface XMLDao {
    List<Object> parse(String resourceName) throws XMLDaoException;
}
