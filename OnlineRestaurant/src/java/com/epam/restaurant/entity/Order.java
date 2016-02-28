package com.epam.restaurant.entity;

import com.epam.restaurant.dao.Identified;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Вероника on 04.02.2016.
 */
public class Order implements Serializable, Identified<Long> {
    public static final long serialVersionUID = 1;

    private long id;
    private long userId;
    private Date createdAt;
    private BigDecimal total;
    private List<OrderDish> orderDishes;

    public Order(){
        this.orderDishes = new ArrayList<>();
        this.createdAt = new Date();
    }

    public Order(long userId,Date createdAt){
        this.userId = userId;
        this.createdAt = createdAt;
        this.orderDishes = new ArrayList<>();
        this.total = total;
    }

    public Order(long userId,Date createdAt, BigDecimal total){
        this.userId = userId;
        this.createdAt = createdAt;
        this.orderDishes = new ArrayList<>();
        this.total = total;
    }

    public Order(long id,long userId,Date createdAt, BigDecimal total){
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.orderDishes = new ArrayList<>();
        this.total = total;
    }

    public int getSize() {
        return this.orderDishes.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderDish> getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(List<OrderDish> orderDishes) {
        this.orderDishes = orderDishes;
    }


}
