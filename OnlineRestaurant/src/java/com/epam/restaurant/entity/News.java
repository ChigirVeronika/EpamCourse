package com.epam.restaurant.entity;

import com.epam.restaurant.dao.Identified;

import java.io.Serializable;

/**
 * Created by Вероника on 04.02.2016.
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

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        News news = (News) o;

        if (id != news.id){
            return false;
        }
        return content != null ? content.equals(news.content) : news.content == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
