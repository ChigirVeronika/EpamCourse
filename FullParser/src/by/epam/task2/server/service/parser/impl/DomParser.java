package by.epam.task2.server.service.parser.impl;

import by.epam.task2.entity.Book;
import by.epam.task2.entity.Category;
import by.epam.task2.server.service.parser.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;



/**
 * Created by Вероника on 21.12.2015.
 */
public class DomParser {
    private static final String XSD_PATH = "resources/book.xsd";
    public List<Book> parse(String fileName) throws ParserException {
        List<Book> books;
        try{
            File file = new File(fileName);

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            documentFactory.setValidating(false);
            documentFactory.setNamespaceAware(true);

            SchemaFactory schemaFactory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            documentFactory.setSchema(
                    schemaFactory.newSchema(new StreamSource(XSD_PATH)));

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("book");
            //books=transformTo

        }catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParserException("Parse error occurred.", e);
        }
        return books;
    }

    private List<Book> transformToList(NodeList nodeList){
        

    }

    private Book getBook(Element element){
        Book book = new Book();
        book.setId(element.getAttribute("id"));
        book.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
        book.setAuthor(element.getElementsByTagName("author").item(0).getTextContent());

        Element category = (Element) element.getElementsByTagName("category").item(0);
        book.setCategory(getCategory(category));

        book.setDescription(element.getElementsByTagName("description").item(0).getTextContent());

        return book;
    }

    private Category getCategory(Element element){
        Category category = new Category();
        category.setGenre(element.getElementsByTagName("genre").item(0).getTextContent());
        category.setPages(Integer.parseInt(element.getElementsByTagName("pages").item(0).getTextContent()));
        category.setYear(Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent()));

        return category;
    }
}
