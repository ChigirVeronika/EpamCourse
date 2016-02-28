package com.epam.restaurant.entity;

import com.epam.restaurant.dao.Identified;

import java.io.Serializable;

/**
 * Describes category of dish.
 */
public class Category implements Serializable, Identified<Long> {
    public static final long serialVersionUID = 1;

    private long id;
    private String name;
    private String description;

    public Category(){}

    public Category(String name, String description){
        this.name=name;
        this.description=description;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Category category = (Category) o;

        if (id != category.id){
            return false;
        }
        if (name != null ? !name.equals(category.name) : category.name != null){
            return false;
        }
        return description != null ? description.equals(category.description) : category.description == null;

    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
