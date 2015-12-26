package server.by.epam.fullparser.service.parser;

import entity.Book;

import java.util.List;

/**
 *  Service interface for all parser types.
 */
public interface Parser {
    List<Book> parse(String fileName) throws ParserException;
}
