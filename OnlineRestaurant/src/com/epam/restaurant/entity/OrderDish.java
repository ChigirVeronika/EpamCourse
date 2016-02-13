package com.epam.restaurant.entity;

import java.io.Serializable;

/**
 * Created by Вероника on 04.02.2016.
 */
public class OrderDish implements Serializable {
    public static final long serialVersionUID = 1;

    private int id;
    private int dishId;
    private int orderId;
    private int quantity;

    public OrderDish(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dish_id) {
        this.dishId = dish_id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int order_id) {
        this.orderId = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDish orderDish = (OrderDish) o;

        if (id != orderDish.id) return false;
        if (dishId != orderDish.dishId) return false;
        if (orderId != orderDish.orderId) return false;
        return quantity == orderDish.quantity;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dishId;
        result = 31 * result + orderId;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "OrderDish{" +
                "id=" + id +
                ", dish_id=" + dishId +
                ", order_id=" + orderId +
                ", quantity=" + quantity +
                '}';
    }
}
