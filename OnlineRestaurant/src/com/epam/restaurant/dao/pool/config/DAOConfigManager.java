package com.epam.restaurant.dao.pool.config;

import java.util.ResourceBundle;

//�����, �������� ��������� ������������ �� ����� jdbc.properties
public class DAOConfigManager {

    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASS = "pass";
    public static final String POOL_SIZE = "pool.size";

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.jdbc");

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public static void setResourceBundle(ResourceBundle resourceBundle) {
        DAOConfigManager.resourceBundle = resourceBundle;
    }

    public static String getProperty(String property) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle("resources.jdbc");
           // LOGGER.info("Resource bundle has been created");
        }
        return (String) resourceBundle.getObject(property);
    }
}
