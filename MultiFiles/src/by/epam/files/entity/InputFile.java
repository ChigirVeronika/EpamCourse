package by.epam.files.entity;

import by.epam.files.service.FileService;

import java.io.*;
import java.util.List;

/**
 * File class for thread Walker.
 */
public class InputFile extends File {
    private static String SPACE = "\\s+";

    public InputFile(String path){
        super(path);
    }

    public double doWork(String fullPath){
        List<String> fileLinesList = null;
        try {
            fileLinesList = FileService.readFileToList(fullPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String operationNumber = fileLinesList.get(0);
        double number=0;
        String [] oneStrings = fileLinesList.get(1).split(SPACE);
        double[] one = new double[oneStrings.length];
        for (int i = 0; i < one.length; i++) {
            one[i]=Double.parseDouble(oneStrings[i]);
        }
        switch (operationNumber){
            case "1":{//сложение
                for (int i = 0; i < one.length; i++) {
                    number=number+one[i];
                }
                break;
            }
            case "2":{//умножение
                for (int i = 0; i < one.length; i++) {
                    number=number*one[i];
                }
                break;
            }
            case "3":{//сумма квадратов
                for (int i = 0; i < one.length; i++) {
                    one[i]=Math.pow(one[i],2);
                }
                for (int i = 0; i < one.length; i++) {
                    number=number+one[i];
                }
                break;
            }
        }
        return number;
    }

    @Override
    public InputFile[] listFiles() {
        File[] theFiles = super.listFiles();
        if (theFiles == null || theFiles.length == 0) return null;

        InputFile[] myFiles = new InputFile[theFiles.length];
        for (int i = 0; i < theFiles.length; i++) {
            myFiles[i] = new InputFile(theFiles[i].getAbsolutePath());
        }
        return myFiles;
    }
}