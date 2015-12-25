package by.epam.task2.server.service;

import by.epam.task2.entity.Book;
import by.epam.task2.entity.ParserType;
import by.epam.task2.server.service.parser.Parser;
import by.epam.task2.server.service.parser.ParserException;
import by.epam.task2.server.service.parser.impl.DomParser;
import by.epam.task2.server.service.parser.impl.SaxParser;
import by.epam.task2.server.service.parser.impl.StaxParser;

import java.util.Collections;
import java.util.List;

/**
 * Created by Вероника on 25.12.2015.
 */
public class ParseService {
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
