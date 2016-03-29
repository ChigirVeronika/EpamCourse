package com.epam.restaurant.entity;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class UserTest {

    private User user = new User("name", "surname", "email", "12341234", "login", "hash");

    @Test
    public void testGetName() throws Exception {
        assertEquals("name", user.getName());
    }

    @Test
    public void testSetName() throws Exception {
        User user = new User();
        user.setName("name");
        assertEquals("name", user.getName());
    }

    @Test
    public void testGetSurname() throws Exception {
        assertEquals("surname", user.getSurname());
    }

    @Test
    public void testSetSurname() throws Exception {
        User user = new User();
        user.setSurname("name");
        assertEquals("name", user.getSurname());
    }

    @Test
    public void testGetPayCard() throws Exception {
        assertEquals("12341234", user.getPayCard());
    }

    @Test
    public void testSetPayCard() throws Exception {
        User user = new User();
        user.setPayCard("name");
        assertEquals("name", user.getPayCard());
    }

    @Test
    public void testGetLogin() throws Exception {
        assertEquals("login", user.getLogin());
    }

    @Test
    public void testSetLogin() throws Exception {
        User user = new User();
        user.setLogin("name");
        assertEquals("name", user.getLogin());
    }

    @Test
    public void testGetHash() throws Exception {
        assertEquals("hash", user.getHash());
    }

    @Test
    public void testSetHash() throws Exception {
        User user = new User();
        user.setHash("name");
        assertEquals("name", user.getHash());
    }

    @Test
    public void testGetEmail() throws Exception {
        assertEquals("email", user.getEmail());
    }

    @Test
    public void testSetEmail() throws Exception {
        User user = new User();
        user.setEmail("name");
        assertEquals("name", user.getEmail());
    }

    @Test
    public void testEquals() throws Exception {
        User u1 = new User();
        User u2 = new User();
        Assert.assertTrue(u1.equals(u2) && u2.equals(u1));
    }

    @Test
    public void testHashCode() throws Exception {
        User u1 = new User();
        User u2 = new User();
        Assert.assertEquals(u1, u2);
        Assert.assertTrue(u1.hashCode() == u2.hashCode());
    }
}