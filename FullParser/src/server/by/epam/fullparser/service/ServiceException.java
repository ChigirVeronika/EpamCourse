package server.by.epam.fullparser.service;

/**
 * Exception handler of service layer.
 */
public class ServiceException extends Exception{
    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message,Throwable cause){
        super(message,cause);
    }
}
