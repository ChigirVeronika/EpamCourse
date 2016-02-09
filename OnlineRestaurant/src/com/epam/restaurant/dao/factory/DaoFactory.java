package com.epam.restaurant.dao.factory;

import com.epam.restaurant.dao.GenericDao;

public interface DaoFactory{
    interface DaoCreator {
        GenericDao create(DaoFactory parentFactory);
    }//чтоб взять в фабрике дао

    GenericDao getDao(Class entityClass) throws IllegalArgumentException;
}
