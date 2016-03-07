package com.epam.restaurant.entity;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 *
 */
public class DishTest {

    private Dish dish = new Dish("name","description","ingredients",new BigDecimal(12),10,1L,"image.png");

    @Test
    public void testGetName() throws Exception {
        assertEquals("name",dish.getName());
    }

    @Test
    public void testSetName() throws Exception {
        Dish dish = new Dish();
        dish.setName("name");
        assertEquals("name",dish.getName());
    }

    @Test
    public void testGetDescription() throws Exception {
        assertEquals("description",dish.getDescription());
    }

    @Test
    public void testSetDescription() throws Exception {
        Dish dish = new Dish();
        dish.setDescription("name");
        assertEquals("name",dish.getDescription());
    }

    @Test
    public void testGetIngredients() throws Exception {
        assertEquals("ingredients",dish.getIngredients());
    }

    @Test
    public void testSetIngredients() throws Exception {
        Dish dish = new Dish();
        dish.setIngredients("name");
        assertEquals("name",dish.getIngredients());
    }

    @Test
    public void testGetPrice() throws Exception {
        assertEquals(new BigDecimal(12),dish.getPrice());
    }

    @Test
    public void testSetPrice() throws Exception {
        Dish dish = new Dish();
        dish.setName("name");
        assertEquals("name",dish.getName());
    }

    @Test
    public void testGetQuantity() throws Exception {
        assertEquals(10,dish.getQuantity());
    }

    @Test
    public void testSetQuantity() throws Exception {
        Dish dish = new Dish();
        dish.setName("name");
        assertEquals("name",dish.getName());
    }

    @Test
    public void testGetCategoryId() throws Exception {
        assertEquals(1L,dish.getCategoryId());
    }

    @Test
    public void testSetCategoryId() throws Exception {
        Dish dish = new Dish();
        dish.setName("name");
        assertEquals("name",dish.getName());
    }

    @Test
    public void testGetImage() throws Exception {
        assertEquals("image.png",dish.getImage());
    }

    @Test
    public void testSetImage() throws Exception {
        Dish dish = new Dish();
        dish.setName("name");
        assertEquals("name",dish.getName());
    }
}