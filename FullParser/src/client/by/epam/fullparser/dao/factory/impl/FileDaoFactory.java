package client.by.epam.fullparser.dao.factory.impl;

import client.by.epam.fullparser.dao.factory.DaoFactory;

public class FileDaoFactory extends DaoFactory {
    private static final DaoFactory instance = new FileDaoFactory();

    private FileDaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }
}
