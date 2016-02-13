package com.epam.restaurant.entity;

import javax.xml.crypto.Data;
import java.io.Serializable;

/**
 * Created by Вероника on 04.02.2016.
 */
public class Order implements Serializable {
    public static final long serialVersionUID = 1;

    private int id;
    private int userId;
    private Data createdAt;

}
