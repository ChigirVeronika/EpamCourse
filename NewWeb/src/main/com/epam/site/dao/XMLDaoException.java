package main.com.epam.site.dao;

import main.com.epam.site.exception.ProjectException;

/**
 * Created by Вероника on 22.01.2016.
 */
public class XMLDaoException extends ProjectException {
    private static final long serialVersionUID = 1;

    public XMLDaoException(String message){
        super(message);
    }

    public XMLDaoException(String message, Exception e){
        super(message, e);
    }
}
