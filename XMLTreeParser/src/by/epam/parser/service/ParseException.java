package by.epam.parser.service;

import by.epam.parser.entity.Element;

/**
 * <p>Service layer exception class for parser.</p>
 *
 * @author VeronikaChigir
 * @version 1.0
 */
public class ParseException extends Exception{

    /**
     *Determines if a de-serialized file is compatible with this class.
     * Maintainers must change this value if and only if the new version
     * of this class is not compatible with old versions. See Sun docs for
     * <a href=http://java.sun.com/products/jdk/1.1/docs/guide/serialization/spec/version.doc.html>
     * details. </a> Not necessary to include in first version of the class,
     * but included here as a reminder of its importance.
     */
    private static final long serialVersionUID = 1L;

    public ParseException(Exception message){
        super(message);
    }

    public ParseException(String message, Exception ex){
        super(message, ex);
    }


}
