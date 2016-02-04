package com.epam.restaurant.exception;

/**
 * Created by Вероника on 22.01.2016.
 */
public class ProjectException extends Exception {
    private static final long serialVersionUID = 1;
    private Exception hiddenException;

    public ProjectException(String message){
        super(message);
    }

    public ProjectException(String message, Exception e){
        super(message);
        hiddenException = e;
    }

    public Exception getHiddenException(){
        return hiddenException;
    }
}
