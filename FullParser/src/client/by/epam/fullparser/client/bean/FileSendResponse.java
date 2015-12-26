package client.by.epam.fullparser.client.bean;

import server.by.epam.fullparser.server.entity.Book;

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