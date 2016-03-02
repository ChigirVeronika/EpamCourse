package com.epam.restaurant.dao.connectionpool.config;

import org.junit.Test;

import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Вероника on 02.03.2016.
 */
public class DAOConfigManagerTest {

    @Test
    public void testGetResourceBundle() throws Exception {
        DAOConfigManager configManager = new DAOConfigManager();
        ResourceBundle rb = configManager.getResourceBundle();
        assertNotNull(rb);
        assertEquals(rb, ResourceBundle.getBundle("db.db"));
    }

    @Test
    public void testSetResourceBundle() throws Exception {
        DAOConfigManager configManager = new DAOConfigManager();
        configManager.setResourceBundle(ResourceBundle.getBundle("db.db"));
        assertEquals(configManager.getResourceBundle(),ResourceBundle.getBundle("db.db"));
    }

    @Test
    public void testGetProperty() throws Exception {
        DAOConfigManager configManager = new DAOConfigManager();
        assertEquals(configManager.getProperty("USER.DELETE"),"DELETE FROM restaurant.user WHERE id = ?");
    }
}