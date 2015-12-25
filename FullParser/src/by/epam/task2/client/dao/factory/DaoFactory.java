package by.epam.task2.client.dao.factory;

import by.epam.task2.client.dao.NetworkDao;
import by.epam.task2.client.dao.factory.impl.FileDaoFactory;

public abstract class DaoFactory {
    private final static String DAO_TYPE = "file";

    public static DaoFactory getDaoFactory() {
        switch (DAO_TYPE) {
            case "file":
                return FileDaoFactory.getInstance();
        }
        return null;
    }

    public final NetworkDao getNetworkDao() {
        return NetworkDao.getInstance();
    }
}
