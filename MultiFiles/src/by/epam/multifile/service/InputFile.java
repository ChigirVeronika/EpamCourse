package by.epam.multifile.service;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

/**
 * File class for thread OperationExecutor.
 */
public class InputFile extends File {
    private final static String SPACE = "\\s+";

    private final static String ONE = "1";
    private final static String TWO = "2";
    private final static String THREE = "3";
    private static final Logger LOGGER = Logger.getLogger( InputFile.class);

    public InputFile(String path){
        super(path);
    }

    public double executeOperation(String fullPath){
        List<String> fileLinesList = null;
        try {
            fileLinesList = FileService.readFileToList(fullPath);
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        }
        String operationNumber = fileLinesList.get(0);
        double number=0;
        String [] oneStrings = fileLinesList.get(1).split(SPACE);
        double[] one = new double[oneStrings.length];
        for (int i = 0; i < one.length; i++) {
            one[i]=Double.parseDouble(oneStrings[i]);
        }
        switch (operationNumber){
            case ONE:{
                for (int i = 0; i < one.length; i++) {
                    number=number+one[i];
                }
                break;
            }
            case TWO:{
                number=1;
                for (int i = 0; i < one.length; i++) {
                    number=number*one[i];
                }
                break;
            }
            case THREE:{
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