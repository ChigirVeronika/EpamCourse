package client.by.epam.fullparser.client.dao.factory;

import client.by.epam.fullparser.client.dao.NetworkDao;
import client.by.epam.fullparser.client.dao.factory.impl.FileDaoFactory;

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
