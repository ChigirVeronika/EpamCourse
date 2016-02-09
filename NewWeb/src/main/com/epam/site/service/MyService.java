package main.com.epam.site.service;

import main.com.epam.site.dao.XMLDao;
import main.com.epam.site.dao.XMLDaoException;
import main.com.epam.site.dao.XMLDaoFactory;

import java.util.List;

/**
 * Created by Вероника on 08.02.2016.
 */
public class MyService {
    public static List<Object> service(String s){
        XMLDao dao;
        List<Object> info=null;
        try{
            dao = XMLDaoFactory.getInstance().getDao(XMLDaoFactory.DaoType.SAX);
            info = dao.parse(s);
        }catch (XMLDaoException e){

        }
        return info;
    }
}
