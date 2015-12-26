package server.by.epam.fullparser.service;

import entity.Book;
import entity.ParserType;
import server.by.epam.fullparser.service.parser.Parser;
import server.by.epam.fullparser.service.parser.ParserException;
import server.by.epam.fullparser.service.parser.impl.DomParser;
import server.by.epam.fullparser.service.parser.impl.SaxParser;
import server.by.epam.fullparser.service.parser.impl.StaxParser;

import java.util.Collections;
import java.util.List;

/**
 * Choice of parser type.
 */
public class ParseService {

    public ParseService(){}

    public static List<Book> parse(ParserType type, String fileName) throws ServiceException {
        try {
            switch (type) {
                case DOM:
                    return parseWithDom(fileName);
                case SAX:
                    return parseWithSax(fileName);
                case STAX:
                    return parseWithStax(fileName);
            }
        } catch (ParserException e) {
            throw new ServiceException("Service failed.", e);
        }
        return Collections.emptyList();
    }

    private static List<Book> parseWithStax(String fileName) throws ParserException {
        Parser parser = new StaxParser();
        return parser.parse(fileName);
    }

    private static List<Book> parseWithSax(String fileName) throws ParserException {
        Parser parser = new SaxParser();
        return parser.parse(fileName);
    }

    private static List<Book> parseWithDom(String fileName) throws ParserException {
        Parser parser = new DomParser();
        return parser.parse(fileName);
    }
}
