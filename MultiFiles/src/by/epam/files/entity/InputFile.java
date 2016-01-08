package by.epam.files.entity;

import java.io.*;

/**
 * Created by Вероника on 08.01.2016.
 */
public class InputFile extends File {
    private static final long serialVersionUID = 1L;

    public InputFile(String path){
        super(path);
    }

    public void doWork(){
        //откроем файл
        //прочитаем первое число
        //выполним мат. операцию
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
