package main.com.epam.site.dao.impl;

import main.com.epam.site.dao.XMLDao;
import main.com.epam.site.dao.XMLDaoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вероника on 22.01.2016.
 */
public final class SaxXmlDao implements XMLDao {
    private final static SaxXmlDao instance = new SaxXmlDao();

    public static XMLDao getInstance(){
        return instance;
    }

    public List<Object> parse(String resourceName) throws XMLDaoException {
        List<Object> list = new ArrayList<Object>();
        list.add("First string from SAX DAO");
        list.add("Second string from SAX DAO");
        return list;
    }
}
