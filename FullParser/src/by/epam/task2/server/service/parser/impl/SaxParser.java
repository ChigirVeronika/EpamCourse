package by.epam.task2.server.service.parser.impl;

import by.epam.task2.entity.Book;
import by.epam.task2.entity.Category;
import by.epam.task2.server.service.parser.Parser;
import by.epam.task2.server.service.parser.ParserException;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse book.xml by SaxParse.
 */
public class SaxParser implements Parser{
    private static final String XSD_PATH = "resources/book.xsd";
    private List<Book> books;

    /**
     * Method parses xml file.
     *
     * @param fileName xml file path
     * @return list of elements of xml file
     * @throws ParserException if parsing is deprecated
     */
    @Override
    public List<Book> parse(String fileName) throws ParserException {
        try {
            File file = new File(fileName);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);

            SchemaFactory schemaFactory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            factory.setSchema(schemaFactory.newSchema(new StreamSource("resources/book.xsd")));

            SAXParser saxParser = factory.newSAXParser();
            Handler handler = new Handler();
            saxParser.parse(file, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParserException("Parse failed.", e);
        }
        return books;
    }

    class Handler extends DefaultHandler{
        private String elementName;
        private Book book;
        private Category category;



        @Override
        public void startElement(String uri, String localName,
                                 String qName, Attributes attributes) throws SAXException{
            elementName=qName;
            switch (qName){
                case "book":{
                    book = new Book();
                    book.setId(attributes.getValue("id"));
                    break;
                }
                case "category":{
                    category = new Category();
                    break;
                }
                case "books":{
                    books= new ArrayList<>();
                    break;
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            switch (qName){
                case "book":{
                    books.add(book);
                    break;
                }
                case "category":{
                    book.setCategory(category);
                    break;
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            switch (elementName) {
                case "title":
                    book.setTitle(new String(ch,start,length));
                    break;
                case "author":
                    book.setAuthor(new String(ch, start, length));
                    break;
                case "year":
                    category.setYear(Integer.parseInt(new String(ch, start, length)));
                    break;
                case "genre":
                    category.setGenre(new String(ch, start, length));
                    break;
                case "pages":
                    category.setPages(Integer.parseInt(new String(ch, start, length)));
                    break;
                case "phone":
                    book.setDescription(new String(ch, start, length));
                    break;
            }
            elementName = "";
        }



        @Override
        public void warning(SAXParseException e) throws SAXException {
            throw e;
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            throw e;
        }

        @Override
        public void fatalError(SAXParseException e) throws SAXException {
            throw e;
        }
    }
}
