package com.epam.restaurant.service;

/**
 * Created by Вероника on 23.02.2016.
 */
public class OrderDishService {

    private static OrderDishService instance = new OrderDishService();

    private OrderDishService(){}

    public static  OrderDishService getInstance(){
        return instance;
    }

}
