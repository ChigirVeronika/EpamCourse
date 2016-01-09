package by.epam.files.main;

import by.epam.files.entity.InputFile;
import by.epam.files.service.FileService;
import by.epam.files.service.Walker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static by.epam.files.util.InputOutputUtility.*;

/**
 * Created by ¬ÂÓÌËÍ‡ on 08.01.2016.
 */
public class Main {
    private static String WORK_PATH ="src\\by\\epam\\files\\resource\\";
    private static String OUT_FILE_PATH = "E:\\EpamCourse\\MultiFiles\\src\\by\\epam\\files\\release\\out.dat";

    public static void main(String[] args){
        //String userPath=args[0];//todo ›“Œ œŒ◊»Õ»“‹
        String userPath=WORK_PATH;//todo ›“Œ ”ƒ¿À»“‹
        double mainSumValue=0;
        if(userPath!="src\\by\\epam\\files\\resource\\"){
            System.out.println("Invalid path.");
            userPath=WORK_PATH;
            System.out.println("System path is "+WORK_PATH);
        }

        InputFile f = new InputFile(userPath);
        InputFile[] files = f.listFiles();
        System.out.println("List of files created.");//‚Ò∏ „ÛÚ
        System.out.println("In directory "+ files.length +" files.");
        boolean[] flags = new boolean[files.length];

        System.out.println("Enter number of threads");
        int numberOfThreads=inputIntegerValidation();


        Walker walker=null;
        for (int i = 0; i < numberOfThreads; i++) {
            walker = new Walker("Thread"+i,0,files,flags);
            walker.start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                walker.join();
                mainSumValue = mainSumValue + walker.getOwnNumber();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(mainSumValue);

        writeSumToOutFile(mainSumValue);
    }

    private static void writeSumToOutFile(double value){
        FileWriter fileWriter=null;
        try {
            fileWriter = new FileWriter(new File(OUT_FILE_PATH));
            fileWriter.write(String.valueOf(value));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
