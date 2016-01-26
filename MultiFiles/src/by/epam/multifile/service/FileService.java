package by.epam.multifile.service;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Contains methods to work with multifile.
 */
public class FileService {
    private static String EMPTY_STRING="";

    private static final Logger LOGGER = Logger.getLogger( FileService.class);

    public FileService(){}

    public static List<String> readFileToList(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        return lines;
    }

    public static void rewriteFile(String filePath, double newString){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath);
            writer.print(EMPTY_STRING);
            writer.print(newString);
            LOGGER.info("File "+filePath+" rewrote");
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        }
        finally {
            writer.close();
        }
    }
}
