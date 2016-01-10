package by.epam.files.main;

import by.epam.files.entity.InputFile;
import by.epam.files.service.FileService;
import by.epam.files.service.Walker;

import java.io.FileNotFoundException;
import java.util.List;

import static by.epam.files.util.InputOutputUtility.*;

/**
 * Main class of application.
 */
public class Main {
    private static String WORK_PATH ="src\\by\\epam\\files\\resource\\";
    private static String OUT_FILE_PATH = "E:\\EpamCourse\\MultiFiles\\src\\by\\epam\\files\\release\\out.dat";
    private static String EMPTY_STRING="";

    public static void main(String[] args){
        String userPath=args[0];

        if(userPath!=WORK_PATH){
            System.out.println("Invalid path.");
            userPath=WORK_PATH;
            System.out.println("System path is "+WORK_PATH);
        }

        InputFile f = new InputFile(userPath);
        InputFile[] files = f.listFiles();
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double r=0;
        double result=sumResultsDoubles(r);

        FileService.rewriteFile(OUT_FILE_PATH,EMPTY_STRING,result);
    }

    private static double sumResultsDoubles(double res){
        List<String> fileLinesList = null;
        try {
            fileLinesList = FileService.readFileToList(OUT_FILE_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        double[] one = new double[fileLinesList.size()];
        for (int i = 0; i < one.length; i++) {
            one[i]=Double.parseDouble(fileLinesList.get(i));
            res=res+one[i];
        }
        return res;
    }
}
