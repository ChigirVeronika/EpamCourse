package com.epam.restaurant.entity;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 *
 */
public class OrderTest {

    private Order order = new Order(1l,1l,new Date(),new BigDecimal(13));

    @Test
    public void testGetUserId() throws Exception {
        assertEquals(1l,order.getUserId());
    }

    @Test
    public void testSetUserId() throws Exception {
        Order order = new Order();
        order.setUserId(1l);
        assertEquals(1l,order.getUserId());
    }

    @Test
    public void testGetCreatedAt() throws Exception {
        assertEquals(new Date().toString(),order.getCreatedAt().toString());
    }

    @Test
    public void testSetCreatedAt() throws Exception {
        Order order = new Order();
        order.setCreatedAt(new Date());
        assertEquals(new Date().toString(),order.getCreatedAt().toString());
    }

    @Test
    public void testGetTotal() throws Exception {
        assertEquals(new BigDecimal(13),order.getTotal());
    }

    @Test
    public void testSetTotal() throws Exception {
        Order order = new Order();
        order.setTotal(new BigDecimal(13));
        assertEquals(new BigDecimal(13),order.getTotal());
    }
}