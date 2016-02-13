package com.epam.restaurant.entity;

import java.io.Serializable;

/**
 * Created by Вероника on 04.02.2016.
 */
public class PayCard implements Serializable {
    public static final long serialVersionUID = 1;

    private int id;
    private int userId;
    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "PayCard{" +
                "id=" + id +
                ", user_id=" + userId +
                ", money=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayCard payCard = (PayCard) o;

        if (id != payCard.id) return false;
        if (userId != payCard.userId) return false;
        return Double.compare(payCard.money, money) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + userId;
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
