package com.epam.restaurant.entity;

import com.epam.restaurant.dao.Identified;

import java.io.Serializable;

/**
 *
 */
public class News implements Serializable, Identified<Long> {
    public static final long serialVersionUID = 1;

    private long id;
    private String content;

    public News(){}

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




}
