package server.by.epam.fullparser.service.parser.impl;

import entity.Book;
import entity.Category;
import server.by.epam.fullparser.service.parser.Parser;
import server.by.epam.fullparser.service.parser.ParserException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static server.by.epam.fullparser.util.BookConstant.*;

/**
 * Parse book.xml by StaxParse.
 */
public class StaxParser implements Parser {
    private static final String XSD_PATH = "resources/book.xsd";
    private List<Book> books;
    private String elementName;
    private Book book;
    private Category category;

    public StaxParser(){}

    @Override
    public List<Book> parse(String fileName) throws ParserException {
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants
                    .W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(XSD_PATH));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(fileName));

            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(fileName));

            cycleParse(eventReader);

        } catch (XMLStreamException | SAXException | IOException e) {
            throw new ParserException("StAX parser failed.", e);
        }
        return books;
    }

    public void parseCharacters(Characters characters){
        switch (elementName) {
            case TITLE:
                book.setTitle(characters.getData());
                break;
            case AUTHOR:
                book.setAuthor(characters.getData());
                break;
            case YEAR:
                category.setYear(Integer.parseInt(characters.getData()));
                break;
            case GENRE:
                category.setGenre(characters.getData());
                break;
            case PAGES:
                category.setPages(Integer.parseInt(characters.getData()));
                break;
            case DESCRIPTION:
                book.setDescription(characters.getData());
                break;
        }
        elementName = "";
    }

    public void parseStart(String name, StartElement startElement){
        elementName = name;
        if (BOOK.equals(name)) {
            book = new Book();
            Iterator<Attribute> attributes = startElement.getAttributes();
            if (attributes.hasNext()) {
                book.setId(attributes.next().getValue());
            }
        } else if (CATEGORY.equals(name)) {
            category = new Category();
        }
    }

    public void parseEnd(String name){
        if (BOOK.equals(name)) {
            books.add(book);
        } else if (CATEGORY.equals(name)) {
            book.setCategory(category);
        }
    }

    public void cycleParse(XMLEventReader eventReader) throws XMLStreamException {
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT:
                    books = new ArrayList<>();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    StartElement start = event.asStartElement();
                    String qName = start.getName().getLocalPart();
                    parseStart(qName, start);
                    break;

                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    parseCharacters(characters);
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement end = event.asEndElement();
                    qName = end.getName().getLocalPart();

                    parseEnd(qName);
                    break;
            }
        }
    }
}
