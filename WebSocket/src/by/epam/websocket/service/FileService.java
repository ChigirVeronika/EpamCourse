package by.epam.websocket.service;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Operations with files.
 */
public class FileService {
    private static final String END_INPUT = "\\Z";
    private static final String NEXT_LINE="\n\n";
    private static final Logger LOGGER = Logger.getLogger( HttpClient.class);

    public FileService(){}

    public static String readFile(String filepath){
        String content = null;
        try {
            content = new Scanner(new File(filepath)).useDelimiter(END_INPUT).next();
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        }
        content=content+NEXT_LINE;
        return content;
    }
}
