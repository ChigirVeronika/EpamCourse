package server.by.epam.fullparser.service.parser.impl;

import entity.Book;
import entity.Category;
import server.by.epam.fullparser.service.parser.Parser;
import server.by.epam.fullparser.service.parser.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * Parse book.xml by DomParse.
 */
public class DomParser implements Parser {
    private static final String XSD_PATH = "resources/book.xsd";

    /**
     * Method parses xml file.
     *
     * @param fileName xml file path
     * @return list of elements of xml file
     * @throws ParserException if parsing is deprecated
     */
    @Override
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
            books=transformToList(nodeList);

        }catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParserException("Parse error occurred.", e);
        }
        return books;
    }

    private List<Book> transformToList(NodeList nodeList){
        List<Book> books = new ArrayList<>(nodeList.getLength());
        for(int i=0;i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            Element element = (Element)node;
            Book book = getBook(element);
            books.add(book);
        }
        return books;
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
