package by.epam.files.entity;

import by.epam.files.service.FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вероника on 08.01.2016.
 */
public class InputFile extends File {
    private static final long serialVersionUID = 1L;

    public InputFile(String path){
        super(path);
    }

    public void doWork(String fullPath){
        //откроем файл и прочитаем первое число
        List<String> fileLines = null;
        try {
            fileLines = FileService.readFileToList(fullPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //выполним мат. операцию
        String operationNumber = fileLines.get(0);
        switch (operationNumber){
            case "1":{
                //сложение
                break;
            }
            case "2":{
                //умножение
                break;
            }
            case "3":{
                //сумма квадратов
                break;
            }

        }
        //закинем результат в out.dat
        System.out.println("Doing work");
        System.out.println("Doing work");
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