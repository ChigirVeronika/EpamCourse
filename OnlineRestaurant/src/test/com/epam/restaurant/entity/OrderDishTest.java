package com.epam.restaurant.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class OrderDishTest {
    private OrderDish orderDish = new OrderDish(1l,1l,13);

    @Test
    public void testGetDishId() throws Exception {
        assertEquals(1l,orderDish.getDishId());
    }

    @Test
    public void testSetDishId() throws Exception {
        OrderDish orderDish = new OrderDish();
        orderDish.setDishId(1L);
        assertEquals(1L,orderDish.getDishId());
    }

    @Test
    public void testGetOrderId() throws Exception {
        assertEquals(1L,orderDish.getOrderId());
    }

    @Test
    public void testSetOrderId() throws Exception {
        OrderDish orderDish = new OrderDish();
        orderDish.setOrderId(1L);
        assertEquals(1L,orderDish.getOrderId());
    }

    @Test
    public void testGetQuantity() throws Exception {
        assertEquals(13,orderDish.getQuantity());
    }

    @Test
    public void testSetQuantity() throws Exception {
        OrderDish orderDish = new OrderDish();
        orderDish.setQuantity(13);
        assertEquals(13,orderDish.getQuantity());
    }
}