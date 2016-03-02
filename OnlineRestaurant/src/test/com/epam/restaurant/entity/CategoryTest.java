package com.epam.restaurant.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Вероника on 01.03.2016.
 */
public class CategoryTest {

    @Test
    public void testGetDescription() throws Exception {
        Category category = new Category("name","description");
        Assert.assertEquals("description",category.getDescription());
    }

    @Test
    public void testSetDescription() throws Exception {
        Category category = new Category();
        category.setDescription("description");
        Assert.assertEquals("description",category.getDescription());
    }

    @Test
    public void testGetName() throws Exception {
        Category category = new Category("name","description");
        Assert.assertEquals("name",category.getName());
    }

    @Test
    public void testSetName() throws Exception {
        Category category = new Category();
        category.setName("name");
        Assert.assertEquals("name",category.getName());
    }

    @Test
    public void testEquals() throws Exception {
        Category c1 = new Category("CommonName","CommonDescription");
        Category c2 = new Category("CommonName","CommonDescription");
        Assert.assertTrue(c1.equals(c2) && c2.equals(c1));
    }

    @Test
    public void testHashCode() throws Exception {
        Category c1 = new Category("CommonName","CommonDescription");
        Category c2 = new Category("CommonName","CommonDescription");
        Assert.assertEquals(c1, c2);
        Assert.assertTrue( c1.hashCode()==c2.hashCode() );
    }
}