package by.epam.parser.util;

import by.epam.parser.entity.Attribute;
import by.epam.parser.entity.Element;

import java.util.List;

import static by.epam.parser.service.Constant.*;

/**
 * <p>Util class for output root element contents
 * in xml file structure view.</p>
 *
 * @author Veronika
 * @version 1.0
 */
public class PrintStructure {
    private static final String OPEN_BRACKET = "<";
    private static final String CLOSE_BRACKET = ">";
    private static final String OPEN_BRACKET_CLOSE_TAG = "</";
    private static final String NEWLINE = "\n";
    private static final String SPACE = " ";
    private static final String TAB = "\t";

    public PrintStructure(){}

    public static String printAttribute(Attribute a){
        return SPACE+a.getName()+ASSIGNMENT+a.getValue();
    }

    public static String printElement(Element e){
        if(e.getContent().equals(EMPTY_STRING)){
            return OPEN_BRACKET + e.getOpenName() + printAttributesList(e) + CLOSE_BRACKET +
                    NEWLINE + printElementsList(e) +
                    OPEN_BRACKET_CLOSE_TAG + e.getCloseName() + CLOSE_BRACKET;
        }
        return OPEN_BRACKET + e.getOpenName() + printAttributesList(e) + CLOSE_BRACKET +
                NEWLINE + e.getContent() + NEWLINE + printElementsList(e) +
                OPEN_BRACKET_CLOSE_TAG + e.getCloseName() + CLOSE_BRACKET;
    }

    /**
     * <p>Represent attributes list in format name = value.
     * Use StringBuffer to form result in common string.</p>
     *
     * @return string with all attributes in format name = value
     * @see StringBuffer
     */
    public static String printAttributesList(Element e){
        int stringBufferSize = 300;
        StringBuffer result = new StringBuffer(stringBufferSize);
        result.append(EMPTY_STRING);
        List<Attribute> attributes = e.getAttributes();
        if(e.getAttributes()!=null) {
            for (Attribute a : attributes) {
                result.append(printAttribute(a));
            }
        }
        return result.toString();
    }

    /**
     * <p>Represent elements list in xml format.
     * Use StringBuffer to form result in common string.</p>
     *
     * @return string with elements in xml format
     * @see StringBuffer
     */
    public static String printElementsList(Element e) {
        int stringBufferSize = 1000;
        StringBuffer result = new StringBuffer(stringBufferSize);
        result.append(EMPTY_STRING);
        if(e.getElements()!=null) {
            for (Element el : e.getElements()) {
                result.append(printElement(el));
                result.append(NEWLINE);
            }
        }
        return result.toString();
    }
}
