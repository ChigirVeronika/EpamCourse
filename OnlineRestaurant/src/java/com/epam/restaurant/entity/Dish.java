package com.epam.restaurant.entity;

import com.epam.restaurant.dao.Identified;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Describes dish entity.
 */
public class Dish implements Serializable, Identified<Long>{
    public static final long serialVersionUID = 1;

    private long id;
    private String name;
    private String description;
    private String ingredients;
    private BigDecimal price;
    private int quantity;
    private long categoryId;
    private String image;

    public Dish(){}

    public Dish(String name, String description, String ingredients,
                BigDecimal price, int  quantity, Long categoryId, String image){
        this.name=name;
        this.description=description;
        this.ingredients=ingredients;
        this.price=price;
        this.quantity=quantity;
        this.categoryId=categoryId;
        this.image=image;
    }

    public Dish(Long id, String name, String description, String ingredients,
                BigDecimal price, int  quantity, Long categoryId, String image){
        this.id=id;
        this.name=name;
        this.description=description;
        this.ingredients=ingredients;
        this.price=price;
        this.quantity=quantity;
        this.categoryId=categoryId;
        this.image=image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", categoryId=" + categoryId +
                ", image='" + image + '\'' +
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

        Dish dish = (Dish) o;

        if (id != dish.id){
            return false;
        }
        if (quantity != dish.quantity){
            return false;
        }
        if (categoryId != dish.categoryId){
            return false;
        }
        if (name != null ? !name.equals(dish.name) : dish.name != null){
            return false;
        }
        if (description != null ? !description.equals(dish.description) : dish.description != null){
            return false;
        }
        if (ingredients != null ? !ingredients.equals(dish.ingredients) : dish.ingredients != null){
            return false;
        }
        if (price != null ? !price.equals(dish.price) : dish.price != null) {
            return false;
        }
        return image != null ? image.equals(dish.image) : dish.image == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (int) (categoryId ^ (categoryId >>> 32));
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
