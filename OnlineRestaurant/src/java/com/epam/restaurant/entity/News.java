package com.epam.restaurant.entity;

import com.epam.restaurant.dao.Identified;

import java.io.Serializable;
import java.util.Date;

/**
 * Describes news entity.
 */
public class News implements Serializable, Identified<Long> {
    public static final long serialVersionUID = 1;

    private long id;
    private String name;
    private Date date;
    private String content;
    private String image;

    public News() {
    }

    public News(String name, Date date, String content, String image) {
        this.name = name;
        this.date = date;
        this.content = content;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
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

        News news = (News) o;

        if (id != news.id) {
            return false;
        }
        if (name != null ? !name.equals(news.name) : news.name != null) {
            return false;
        }
        if (date != null ? !date.equals(news.date) : news.date != null) {
            return false;
        }
        if (content != null ? !content.equals(news.content) : news.content != null) {
            return false;
        }
        return image != null ? image.equals(news.image) : news.image == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
