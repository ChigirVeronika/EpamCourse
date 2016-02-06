package com.epam.restaurant.dao.connectionpool;

import org.apache.log4j.Logger;

import java.util.ResourceBundle;

//�����, �������� ��������� ������������ �� ����� jdbc.properties
public class DAOConfigManager {
    private static final Logger LOGGER = Logger.getLogger( DAOConfigManager.class);

    public static final String URL = "db.url";
    public static final String USER = "db.user";
    public static final String PASS = "db.password";
    public static final String POOL_SIZE = "db.poolsize";

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.db");

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static void setResourceBundle(ResourceBundle resourceBundle) {
        DAOConfigManager.resourceBundle = resourceBundle;
    }

    public static String getProperty(String property) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle("resources.db");
            LOGGER.info("Resource bundle of db has been created");
        }
        return (String) resourceBundle.getObject(property);
    }
}
