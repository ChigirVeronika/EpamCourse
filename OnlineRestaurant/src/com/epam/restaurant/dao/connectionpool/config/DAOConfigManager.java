package com.epam.restaurant.dao.connectionpool.config;

import org.apache.log4j.Logger;

import java.util.ResourceBundle;

public class DAOConfigManager {
    private static final Logger LOGGER = Logger.getLogger( DAOConfigManager.class);

    public static final String URL = "db.url";
    public static final String USER = "db.user";
    public static final String PASS = "db.password";
    public static final String POOL_SIZE = "db.poolsize";
    public static final String DRIVER = "\"com.mysql.jdbc.Driver\"";

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("db.db");

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static void setResourceBundle(ResourceBundle resourceBundle) {
        DAOConfigManager.resourceBundle = resourceBundle;
    }

    public static String getProperty(String property) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle("db.db");
            LOGGER.info("Resource bundle of db has been created");
        }
        return (String) resourceBundle.getObject(property);
    }
}
