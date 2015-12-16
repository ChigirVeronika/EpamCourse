package by.epam.parser.service;

import by.epam.parser.entity.Document;

import java.io.Reader;

/**
 * @author VeronikaChigir
 * @version 1.0
 */
public interface Parser {
    Document parse(Reader reader) throws ParseException;
}
