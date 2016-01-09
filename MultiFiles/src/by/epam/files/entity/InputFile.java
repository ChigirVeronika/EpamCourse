package by.epam.files.entity;

import by.epam.files.service.FileService;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by �������� on 08.01.2016.
 */
public class InputFile extends File {
    private double[] f = {};

    public InputFile(String path){
        super(path);
    }

    public double doWork(String fullPath){
        //������� ���� � ���������  � fileLinesList
        List<String> fileLinesList = null;
        try {
            fileLinesList = FileService.readFileToList(fullPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //todo �������� ���. ��������
        String operationNumber = fileLinesList.get(0);
        System.out.println(operationNumber);

        double number=0;
        switch (operationNumber){
            case "1":{
//                ��������
                System.out.println("SUM");
                number=10;
                break;
            }
            case "2":{
                //���������
                System.out.println("***");
                number=20;
                break;
            }
            case "3":{
                //����� ���������
                number=30;
                break;
            }

        }
        //
        System.out.println("Doing work");

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