package by.epam.task2.server.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Dao class to write to file.
 */
public class FileDao {
    private final static FileDao instance = new FileDao();
    private FileOutputStream out;
    private FileInputStream in;
    private String fileName;

    private FileDao() {}

    public static FileDao getInstance() {
        return instance;
    }

    public void createFile(String fileName) throws DaoException {
        try {
            out = new FileOutputStream(fileName);
            this.fileName = fileName;
        } catch (IOException e) {
            throw new DaoException("Can't create file with name " + fileName + ".", e);
        }
    }

    public void write(byte[] bytes) throws DaoException {
        try {
            out.write(bytes);
        } catch (IOException e) {
            throw new DaoException("Can't write data to file.", e);
        }
    }

    public String close() throws DaoException {
        try {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                in.close();
                in = null;
            }
        } catch (IOException e) {
            throw new DaoException("Can't close file.", e);
        }
        return fileName;
    }
}
