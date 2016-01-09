package by.epam.files.main;

import by.epam.files.entity.InputFile;
import by.epam.files.service.FileService;
import by.epam.files.service.Walker;

import static by.epam.files.util.InputOutputUtility.*;

/**
 * Created by Вероника on 08.01.2016.
 */
public class Main {
    private static String PATH ="src\\by\\epam\\files\\resource\\";


    public static void main(String[] args){
        double mainSumValue=0;
//        if(args[0]=="src\\by\\epam\\files\\resource\\"){
//
//        }
//        else{
//            System.out.println("Invalid path.");
//        }

        FileService s = new FileService();
        System.out.println("In directory "+s.filesInDirectoryCount(PATH)+" files.");

        System.out.println("Enter number of threads");
        int numberOfThreads=inputIntegerValidation();

        InputFile f = new InputFile(PATH);
        InputFile[] files = f.listFiles();
        System.out.println("List of files created.");


        Walker walker=null;
        for (int i = 0; i < numberOfThreads; i++) {
            walker = new Walker("Thread"+i,0,files);
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

    }
}
