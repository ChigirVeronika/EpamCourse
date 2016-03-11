package com.epam.restaurant.service.exception;


import com.epam.restaurant.exception.ProjectException;

/**
 * Exception for service layer classes.
 */
public class ServiceException extends ProjectException {
    private static final long serialVersionUID = 1;

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Exception e){
        super(message, e);
    }
}
