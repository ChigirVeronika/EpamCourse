package by.epam.multifile.main;

import by.epam.multifile.service.InputFile;
import by.epam.multifile.service.OperationExecutor;
import org.apache.log4j.Logger;

import java.util.Scanner;

import static by.epam.multifile.service.FileService.*;

/**
 * Main class of application.
 */
public class Main {
    private static String WORK_PATH ="src\\by\\epam\\multifile\\resource\\";
    private static String OUT_FILE_PATH = "E:\\EpamCourse\\MultiFiles\\src\\by\\epam\\multifile\\release\\out.dat";

    private static final Logger LOGGER = Logger.getLogger( Main.class);

    public static void main(String[] args){
//        String userPath=args[0];
        String userPath=WORK_PATH;
        if(userPath!=WORK_PATH){
            System.out.println("Invalid path.");
            userPath=WORK_PATH;
            System.out.println("System path is "+WORK_PATH);
        }

        InputFile f = new InputFile(userPath);
        InputFile[] files = f.listFiles();
        System.out.println("In directory "+ files.length +" multifile.");
        boolean[] flags = new boolean[files.length];

        System.out.println("Enter number of threads");
        int numberOfThreads=inputIntegerValidation();

        OperationExecutor executors[] = new OperationExecutor[numberOfThreads];

        OperationExecutor operationExecutor = null;
        for (int i = 0; i < numberOfThreads; i++) {
            operationExecutor = new OperationExecutor("Thread" + i, 0, files, flags);
            operationExecutor.start();
            LOGGER.info("Thread" + i + " started");
            executors[i] = operationExecutor;
        }
        for(int i = 0; i < numberOfThreads; i++) {
            try {
                operationExecutor.join();
                LOGGER.info(executors[i].getOwnNumber());
                LOGGER.info("Thread"+i+" joined successfully");
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }
        double result=0;
        for(int i = 0; i < numberOfThreads; i++){
            result=result+executors[i].getOwnNumber();
        }
        rewriteFile(OUT_FILE_PATH,result);
    }

    /**
     * Process user input and validate it.
     * In case of incorrect input show
     * appropriate message and give another try.
     * Let positive integer only.
     *
     * @return number user entered
     */
    public static int inputIntegerValidation(){
        int number;
        Scanner scanner = new Scanner(System.in);
        do{
            while (!scanner.hasNextInt()) {
                System.out.println("Not a number. Try again:");
                scanner.next();
            }
            number = scanner.nextInt();
            if(number<1) {
                System.out.println("Enter positive integer please:");
            }
        }while (number<1);
        System.out.println("Got it!");
        return number;
    }
}
