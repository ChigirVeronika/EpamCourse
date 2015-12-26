package client.by.epam.fullparser.service;


import client.by.epam.fullparser.dao.DaoException;
import client.by.epam.fullparser.dao.NetworkDao;
import client.by.epam.fullparser.dao.factory.DaoFactory;
import entity.ServerRequest;
import entity.ServerResponse;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Service for dao network class.
 */
public class NetworkService {
    private static final Logger LOGGER = Logger.getLogger( NetworkService.class);
    private static final NetworkDao networkDao = DaoFactory.getDaoFactory().getNetworkDao();

    public static void connect(String host, int port) throws ServiceException {
        try {
            networkDao.connectTo(host, port);
            LOGGER.info("Connected.");
        } catch (DaoException e) {
            LOGGER.error("Something went wrong.", e);
        }
    }

    public static void send(ServerRequest object) throws ServiceException {
        try {
            networkDao.sendObject(object);
            LOGGER.info("Sent.");
        } catch (DaoException e) {
            LOGGER.error("Object send error.", e);
        }
    }

    public static void send(long longValue) throws ServiceException {
        try {
            networkDao.sendLong(longValue);
            LOGGER.info("Sent.");
        } catch (DaoException e) {
            LOGGER.error("Object send error.", e);
        }
    }

    public static void send(FileInputStream file, long length) throws ServiceException {
        try {
            long sent = 0;

            byte[] bytes = new byte[2048];
            while (sent < length) {
                int read = file.read(bytes);
                sent += read;
                networkDao.sendBytes(bytes);
            }
            LOGGER.info("Sent.");
        } catch (DaoException|IOException e) {
            LOGGER.error("Bytes array send error.", e);
        }
    }

    public static ServerResponse receive() throws ServiceException {
        try {
            LOGGER.info("Received.");
            return networkDao.receiveFromServer();
        } catch (DaoException e) {
            throw new ServiceException("Service failed during receiving data.", e);
        }
    }

    public static void disconnect() throws ServiceException {
        try {
            networkDao.disconnect();
            LOGGER.info("Disconnected.");
        } catch (DaoException e) {
            LOGGER.error("Service failed.", e);
        }
    }
}
