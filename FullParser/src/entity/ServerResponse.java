package entity;


import java.io.Serializable;
import java.util.List;

public class ServerResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success;
    private List<Book> books;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setError(String message) {
        success = false;
        this.message = message;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> Books) {
        this.books = Books;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(String message) {
        this.message = message;
        success = true;
    }
}
