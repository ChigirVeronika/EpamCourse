package com.epam.restaurant.dao.exception;


import com.epam.restaurant.exception.ProjectException;

/**
 * Exception claas for dao layer classes.
 */
public class DaoException extends ProjectException {
    private static final long serialVersionUID = 1;

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Exception e){
        super(message, e);
    }
}
