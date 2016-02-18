package com.epam.restaurant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Вероника on 04.02.2016.
 */
public class Bill implements Serializable {
    public static final long serialVersionUID = 1;

    private long id;
    private long orderId;
    private Date createdAt;
    private BigDecimal total;

    public Bill(){}


}
