package com.epam.restaurant.dao.sql;

import org.apache.log4j.Logger;

/**
 * Created by Вероника on 22.01.2016.
 */
public class SqlDaoFactory {
    private static final Logger LOGGER = Logger.getLogger(SqlDaoFactory.class);

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
                //// TODO: 06.02.2016
                ///LOGGER.error();
        }
    }

    public enum DaoType{
        SAX, STAX, DOM;
    }

}
