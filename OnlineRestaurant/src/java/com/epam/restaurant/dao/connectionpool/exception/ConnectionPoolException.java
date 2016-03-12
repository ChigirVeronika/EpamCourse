package com.epam.restaurant.dao.connectionpool.exception;


import com.epam.restaurant.exception.ProjectException;

/**
 * Exception for connection pool.
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
