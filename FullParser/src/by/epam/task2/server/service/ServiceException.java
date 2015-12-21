package by.epam.task2.server.service;

/**
 * Created by Вероника on 21.12.2015.
 */
public class ServiceException extends Exception{
    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message,Throwable cause){
        super(message,cause);
    }
}
