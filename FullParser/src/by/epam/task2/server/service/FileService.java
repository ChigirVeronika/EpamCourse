package by.epam.task2.server.service;

import by.epam.task2.server.dao.DaoException;
import by.epam.task2.server.dao.FileDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by Вероника on 25.12.2015.
 */
public class FileService {
    private final static FileDao dao = FileDao.getInstance();

    public static void createFile() throws ServiceException {
        try {
            String fileName = String.valueOf("resources/" + new Date().getTime() + ".xml");
            dao.createFile(fileName);
        } catch (DaoException e) {
            throw new ServiceException("Can't create file.", e);
        }
    }

    public static void writeToFile(byte[] bytes) throws ServiceException {
        try {
            dao.write(bytes);
        } catch (DaoException e) {
            throw new ServiceException("Can't write to file.", e);
        }
    }

    public static String closeFile() throws ServiceException {
        try {
            return dao.close();
        } catch (DaoException e) {
            throw new ServiceException("Can't close file.", e);
        }
    }

    public static void delete(String fileName) throws ServiceException {
        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException e) {
            throw new ServiceException("Can't delete file.", e);
        }
    }
}
