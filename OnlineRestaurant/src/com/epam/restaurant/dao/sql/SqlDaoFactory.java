package com.epam.restaurant.dao.sql;

/**
 * Created by Вероника on 22.01.2016.
 */
public class SqlDaoFactory {
    private final static SqlDaoFactory instance = new SqlDaoFactory();

    public static SqlDaoFactory getInstance(){
        return  instance;
    }

    public SqlDao getDao(DaoType type) throws SqlDaoException {
        SqlDao dao;
        switch (type){
            case SAX:
                //return SaxXmlDao.getInstance();
            default:
                throw new SqlDaoException("No such dao");
        }
    }

    public enum DaoType{
        SAX, STAX, DOM;
    }

}
