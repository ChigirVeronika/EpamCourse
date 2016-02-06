package com.epam.restaurant.service;


import com.epam.restaurant.exception.ProjectException;

/**
 * Created by Вероника on 22.01.2016.
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
