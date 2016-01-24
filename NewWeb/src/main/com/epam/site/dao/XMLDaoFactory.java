package main.com.epam.site.dao;

import main.com.epam.site.dao.impl.SaxXmlDao;

/**
 * Created by Вероника on 22.01.2016.
 */
public class XMLDaoFactory {
    private final static  XMLDaoFactory instance = new XMLDaoFactory();

    public static XMLDaoFactory getInstance(){
        return  instance;
    }

    public XMLDao getDao(DaoType type) throws XMLDaoException{
        XMLDao dao;
        switch (type){
            case SAX:
                return SaxXmlDao.getInstance();
            default:
                throw new XMLDaoException("No such dao");
        }
    }

    public enum DaoType{
        SAX, STAX, DOM;
    }

}
