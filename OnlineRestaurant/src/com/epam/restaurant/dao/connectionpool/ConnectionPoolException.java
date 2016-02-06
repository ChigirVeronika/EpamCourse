package com.epam.restaurant.dao.connectionpool;


import com.epam.restaurant.exception.ProjectException;

/**
 * Created by Вероника on 22.01.2016.
 */
public class ConnectionPoolException extends ProjectException {
    private static final long serialVersionUID = 1;

    public ConnectionPoolException(String message){
        super(message);
    }

    public ConnectionPoolException(String message, Exception e){
        super(message, e);
    }
}
