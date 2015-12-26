package client.by.epam.fullparser.service;

/**
 * Exception handler class for service layer
 * in client application part.
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
