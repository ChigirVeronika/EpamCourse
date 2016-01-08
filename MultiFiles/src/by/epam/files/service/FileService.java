package by.epam.files.service;

import by.epam.files.entity.InputFile;

import java.io.File;

/**
 * Created by Вероника on 08.01.2016.
 */
public class FileService {
    private InputFile file;
    private InputFile[] structure;
    public InputFile[] getFileArray(String path){
        file = new InputFile(path);
        structure = (InputFile[]) file.listFiles();

        return structure;
    }
    private int c = 0;

    public int fileCount(String path) {
        InputFile file = new InputFile(path);
        InputFile[] s = (InputFile[]) file.listFiles();

        for (int i = 0; i < s.length; i++) {
            if(!s[i].isDirectory())
                c++;
            if (s[i].isDirectory())
                fileCount(s[i].getPath());
        }
        return c;
    }


}
