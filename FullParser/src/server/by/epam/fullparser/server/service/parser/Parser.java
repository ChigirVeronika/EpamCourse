package server.by.epam.fullparser.server.service.parser;

import server.by.epam.fullparser.server.entity.Book;

import java.util.List;

/**
 *  Service interface for all parser types.
 */
public interface Parser {
    List<Book> parse(String fileName) throws ParserException;
}
