package by.epam.parser.start;

import by.epam.parser.entity.Document;
import by.epam.parser.entity.Element;
import by.epam.parser.service.DOMParser;
import by.epam.parser.service.ParseException;
import by.epam.parser.util.PrintStructure;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * <p>Main class of application.</p>
 *
 * @author Veronika
 * @version 1.0
 */
public class Main {
    private static final String XML_FILE_PATH="src\\by\\epam\\parser\\data\\books.xml";

    public static void main(String args[]){
        DOMParser dom = new DOMParser();

        Document document;

        try {
            document=dom.parse(new FileReader(XML_FILE_PATH));
            Element rootElement = document.getRoot();
            String outputString = PrintStructure.printElement(rootElement);
            System.out.println(outputString);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
