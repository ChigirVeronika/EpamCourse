package by.epam.task2.client.service;


import by.epam.task2.client.dao.DaoException;
import by.epam.task2.client.dao.NetworkDao;
import by.epam.task2.client.dao.factory.DaoFactory;
import by.epam.task2.entity.ServerRequest;
import by.epam.task2.entity.ServerResponse;

import java.io.FileInputStream;
import java.io.IOException;

public class NetworkService {
    private static final NetworkDao networkDao = DaoFactory.getDaoFactory().getNetworkDao();

    public static void connect(String host, int port) throws ServiceException {
        try {
            networkDao.connectTo(host, port);
        } catch (DaoException e) {
            throw new ServiceException("[TODO]: Something went wrong.", e);
        }
    }

    public static void send(ServerRequest object) throws ServiceException {
        try {
            networkDao.sendObject(object);
        } catch (DaoException e) {
            throw new ServiceException("Object send error.", e);
        }
    }

    public static void send(long longValue) throws ServiceException {
        try {
            networkDao.sendLong(longValue);
        } catch (DaoException e) {
            throw new ServiceException("Object send error.", e);
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
        } catch (DaoException|IOException e) {
            throw new ServiceException("Bytes array send error.", e);
        }
    }

    public static ServerResponse receive() throws ServiceException {
        try {
            return networkDao.receiveFromServer();
        } catch (DaoException e) {
            throw new ServiceException("Service failed during receiving data.", e);
        }
    }

    public static void disconnect() throws ServiceException {
        try {
            networkDao.disconnect();
        } catch (DaoException e) {
            throw new ServiceException("Service failed.", e);
        }
    }
}
