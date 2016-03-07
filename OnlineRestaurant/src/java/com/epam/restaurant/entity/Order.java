package com.epam.restaurant.entity;

import com.epam.restaurant.dao.Identified;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                ", total=" + total +
                ", orderDishes=" + orderDishes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userId != order.userId) return false;
        if (createdAt != null ? !createdAt.equals(order.createdAt) : order.createdAt != null) return false;
        if (total != null ? !total.equals(order.total) : order.total != null) return false;
        return orderDishes != null ? orderDishes.equals(order.orderDishes) : order.orderDishes == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (orderDishes != null ? orderDishes.hashCode() : 0);
        return result;
    }
}
