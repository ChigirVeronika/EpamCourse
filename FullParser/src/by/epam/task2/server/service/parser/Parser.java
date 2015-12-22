package by.epam.task2.server.service.parser;

import by.epam.task2.entity.Book;

import java.util.List;

/**
 * Created by Вероника on 21.12.2015.
 */
public interface Parser {
    List<Book> parse(String fileName) throws ParserException;
}
