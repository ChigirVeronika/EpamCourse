package by.epam.files.main;

import by.epam.files.service.FileService;
import by.epam.files.service.Walker;

import java.io.File;

import static by.epam.files.util.InputOutputUtility.*;

/**
 * Created by Вероника on 08.01.2016.
 */
public class Main {
    private static String PATH ="src\\by\\epam\\files\\resource\\";

    public static void main(String[] args){

//        if(args[0]=="src\\by\\epam\\files\\resource\\"){
//
//        }
//        else{
//            System.out.println("Invalid path.");
//        }

        System.out.println("Enter number of threads");
        int numberOfThreads=inputIntegerValidation();

        File file = new File(PATH+"in_1.dat");

        Walker walker=null;
        for (int i = 0; i < numberOfThreads; i++) {
            walker = new Walker("Thread"+i,i+1,file);
            walker.start();
        }

        FileService s = new FileService();
        System.out.println(s.fileCount(PATH));

    }
}
