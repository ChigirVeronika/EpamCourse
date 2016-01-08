package by.epam.files.service;

import by.epam.files.entity.InputFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Service class realizes thread logic.
 *
 * @author Veronika
 * @see Thread
 */
public class Walker extends Thread {

    /**
     * Number to put in matrix instead of zero
     */
    private int ownNumber;
    private InputFile file;

    public Walker(String s, int ownNumber, InputFile file){
        super(s);
        this.ownNumber=ownNumber;
        this.file = file;
    }

    /**
     * Try to replace zero on matrix main diagonal and sleep 5 seconds.
     */
    public void run(){
        int millisecondsToSleep=500;
        int count=0;

        //открыть файл output.dat и посчитать количество строк
        synchronized(this) {
            File outFile = new File("E:\\EpamCourse\\MultiFiles\\src\\by\\epam\\files\\resource\\release\\out.dat");
            int outCount =  FileService.getStringCount(outFile);
        }

        //берем из массива файл[outCount] - ведь вроде с 0 нумерация


        synchronized (file) {

            file.doWork();
            try {
                Thread.sleep(millisecondsToSleep);
            } catch (InterruptedException e) {
                System.out.print(e);
            }
        }
    }
}
