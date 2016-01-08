package by.epam.files.service;

import by.epam.files.entity.InputFile;

import java.io.*;

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

    public int fileCount(String path) {
        InputFile inputFile = new InputFile(path);
        InputFile[] s = inputFile.listFiles();

        for (int i = 0; i < s.length; i++) {
            if(!s[i].isDirectory())
                c++;
            if (s[i].isDirectory())
                fileCount(s[i].getPath());
        }
        return c;
    }

    public static String read(String fileName) throws FileNotFoundException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
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
