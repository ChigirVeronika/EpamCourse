package by.epam.task2.client.dao.factory.impl;

import by.epam.task2.client.dao.factory.DaoFactory;

public class FileDaoFactory extends DaoFactory {
    private static final DaoFactory instance = new FileDaoFactory();

    private FileDaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }
}
