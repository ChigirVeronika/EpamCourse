package server.by.epam.fullparser.server.dao;

/**
 * Dao layer exception class.
 *
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
