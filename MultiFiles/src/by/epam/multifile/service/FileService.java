package by.epam.multifile.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Contains methods to work with multifile.
 */
public class FileService {
    public FileService(){}

    public static List<String> readFileToList(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        return lines;
    }

    public static void rewriteFile(String filePath, String emptyString, double newString){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath);
            writer.print(emptyString);
            writer.print(newString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            writer.close();
        }
    }
}
