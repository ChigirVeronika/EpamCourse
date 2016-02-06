package com.epam.restaurant.dao.sql;


import com.epam.restaurant.exception.ProjectException;

/**
 * Created by Вероника on 22.01.2016.
 */
public class SqlDaoException extends ProjectException {
    private static final long serialVersionUID = 1;

    public SqlDaoException(String message){
        super(message);
    }

    public SqlDaoException(String message, Exception e){
        super(message, e);
    }
}
