package com.epam.restaurant.entity;

import java.io.Serializable;

/**
 *
 */
public class OrderDish implements Serializable {
    public static final long serialVersionUID = 1;

    private long id;
    private long dishId;
    private long orderId;
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderDish() {
    }

    @Override
    public String toString() {
        return "OrderDish{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderDish orderDish = (OrderDish) o;

        if (id != orderDish.id){
            return false;
        }
        if (dishId != orderDish.dishId) {
            return false;
        }
        if (orderId != orderDish.orderId){
            return false;
        }
        return quantity == orderDish.quantity;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (dishId ^ (dishId >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + quantity;
        return result;
    }
}