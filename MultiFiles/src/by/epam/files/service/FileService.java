package by.epam.files.service;

import by.epam.files.entity.InputFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Вероника on 08.01.2016.
 */
public class FileService {
    private InputFile file;
    private InputFile[] structure;
    private int c;

    public InputFile[] getFileArray(String path){
        InputFile inputFile = new InputFile(path);
        InputFile[] s = inputFile.listFiles();

        return s;
    }

    public int filesInDirectoryCount(String path) {
        InputFile inputFile = new InputFile(path);
        InputFile[] s = inputFile.listFiles();

        for (int i = 0; i < s.length; i++) {
            if(!s[i].isDirectory())
                c++;
            if (s[i].isDirectory())
                filesInDirectoryCount(s[i].getPath());
        }
        return c;
    }

    public static List<String> readFileToList(String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        Scanner sc = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        //String[] arr = lines.toArray(new String[0]);
        return lines;
    }

    public static int getStringCount(File file)
    {
        int i=0;
        BufferedReader bufferedReader;
        try{
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while(bufferedReader.readLine()!=null)
                i++;
            bufferedReader.close();
        }catch(Exception e){}
        return i;
    }




}
