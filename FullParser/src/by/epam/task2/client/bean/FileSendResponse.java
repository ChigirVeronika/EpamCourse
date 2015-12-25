package by.epam.task2.client.bean;

import by.epam.task2.entity.Book;

import java.util.List;

public class FileSendResponse extends Response {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setContacts(List<Book> books) {
        this.books = books;
    }
}