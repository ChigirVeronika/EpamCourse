package by.epam.task2.entity;

import java.io.Serializable;


public class Category implements Serializable{
    private int year;
    private String genre;
    private int pages;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    @Override
    public String toString() {
        return year+" "+genre+" "+pages+" ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (year != category.year) return false;
        if (pages != category.pages) return false;
        return !(genre != null ? !genre.equals(category.genre) : category.genre != null);

    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + pages;
        return result;
    }
}
