package com.epam.restaurant.dao.exception;


import com.epam.restaurant.exception.ProjectException;

/**
 * Created by Вероника on 22.01.2016.
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
