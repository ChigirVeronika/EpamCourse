package by.epam.task2.server.service.parser;

/**
 * Created by Вероника on 21.12.2015.
 */
public class ParserException extends Exception{
    public ParserException(String message){
        super(message);
    }
    public ParserException(String message, Throwable cause){
        super(message,cause);
    }
}
