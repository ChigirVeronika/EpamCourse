package client.by.epam.fullparser.dao;

/**
 * Exception handler class for dao layer
 * in client application part.
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
