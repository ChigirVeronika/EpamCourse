package client.by.epam.fullparser.bean;

import entity.Book;

import java.util.List;

public class FileAskResponse extends Response {
    private List<Book> books;

    public FileAskResponse(){}

    public List<Book> getBooks() {
        return books;
    }

    public void setContacts(List<Book> books) {
        this.books = books;
    }
}