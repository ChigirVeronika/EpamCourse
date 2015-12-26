package server.by.epam.fullparser.server.service.parser.impl;

import server.by.epam.fullparser.server.entity.Book;
import server.by.epam.fullparser.server.service.parser.Parser;
import server.by.epam.fullparser.server.service.parser.ParserException;
import server.by.epam.fullparser.server.entity.Category;
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

/**
 * Parse book.xml by StaxParse.
 */
public class StaxParser implements Parser {
    private List<Book> books;
    private String elementName;
    private Book book;
    private Category category;

    @Override
    public List<Book> parse(String fileName) throws ParserException {
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants
                    .W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("resources/book.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(fileName));

            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(fileName));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_DOCUMENT:
                        books = new ArrayList<>();
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement start = event.asStartElement();
                        String qName = start.getName().getLocalPart();

                        elementName = qName;
                        if ("book".equals(qName)) {
                            book = new Book();
                            Iterator<Attribute> attributes = start.getAttributes();
                            if (attributes.hasNext()) {
                                book.setId(attributes.next().getValue());
                            }
                        } else if ("category".equals(qName)) {
                            category = new Category();
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        switch (elementName) {
                            case "title":
                                book.setTitle(characters.getData());
                                break;
                            case "author":
                                book.setAuthor(characters.getData());
                                break;
                            case "year":
                                category.setYear(Integer.parseInt(characters.getData()));
                                break;
                            case "genre":
                                category.setGenre(characters.getData());
                                break;
                            case "pages":
                                category.setPages(Integer.parseInt(characters.getData()));
                                break;
                            case "description":
                                book.setDescription(characters.getData());
                                break;
                        }
                        elementName = "";
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement end = event.asEndElement();
                        qName = end.getName().getLocalPart();

                        if ("book".equals(qName)) {
                            books.add(book);
                        } else if ("category".equals(qName)) {
                            book.setCategory(category);
                        }
                        break;
                }
            }
        } catch (XMLStreamException | SAXException | IOException e) {
            throw new ParserException("StAX parser failed.", e);
        }
        return books;
    }
}
