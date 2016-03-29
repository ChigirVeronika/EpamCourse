package com.epam.restaurant.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Вероника on 16.03.2016.
 */
public class NewsTest {

    private News news  = new News("name",new Date(),"content","image");

    @Test
    public void testGetName() throws Exception {
        assertEquals("name", news.getName());
    }

    @Test
    public void testSetName() throws Exception {
        News news  = new News();
        news.setName("name");
        assertEquals("name", news.getName());
    }

    @Test
    public void testGetDate() throws Exception {
        assertEquals(new Date(), news.getDate());
    }

    @Test
    public void testSetDate() throws Exception {
        News news  = new News();
        news.setDate(new Date());
        assertEquals(new Date(), news.getDate());
    }

    @Test
    public void testGetContent() throws Exception {
        assertEquals("content", news.getContent());
    }

    @Test
    public void testSetContent() throws Exception {
        News news  = new News();
        news.setContent("content");
        assertEquals("content",news.getContent());
    }

    @Test
    public void testGetImage() throws Exception {
        assertEquals("image",news.getImage());
    }

    @Test
    public void testSetImage() throws Exception {
        News news  = new News();
        news.setImage("image");
        assertEquals("image",news.getImage());
    }

    @Test
    public void testEquals() throws Exception {
        News n1 = new News();
        News n2 = new News();
        Assert.assertTrue(n1.equals(n2) && n2.equals(n1));
    }

    @Test
    public void testHashCode() throws Exception {
        News n1 = new News();
        News n2 = new News();
        Assert.assertEquals(n1, n2);
        Assert.assertTrue( n1.hashCode()==n2.hashCode() );
    }
}