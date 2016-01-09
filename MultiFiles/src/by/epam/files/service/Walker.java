package by.epam.files.service;

import by.epam.files.entity.InputFile;

import java.io.File;

/**
 * Service class realizes thread logic.
 *
 * @author Veronika
 * @see Thread
 */
public class Walker extends Thread {

    /**
     * Number to calculate from file
     */
    private double ownNumber;
    private InputFile[] files;

    public Walker(String s, int ownNumber, InputFile[] files){
        super(s);

        this.ownNumber=ownNumber;
        this.files = files;
    }

    public double getOwnNumber() {
        return ownNumber;
    }

    public void setOwnNumber(int ownNumber) {
        this.ownNumber = ownNumber;
    }
    
    /**
     * .
     */
    public void run(){
        int millisecondsToSleep=500;
        int count=0;

        //открыть файл output.dat и посчитать количество строк
        synchronized(this) {
            File outFile = new File("E:\\EpamCourse\\MultiFiles\\src\\by\\epam\\files\\resource\\release\\out.dat");
            int outCount =  FileService.getStringCount(outFile);

        //берем из массива файлс[outCount] - ведь вроде с 0 нумерация
            if(outCount<files.length) {
                String path=files[outCount].getPath();
                ownNumber=files[outCount].doWork(path);
            }

            try {
                Thread.sleep(millisecondsToSleep);
            } catch (InterruptedException e) {
                System.out.print(e);
            }
        }
    }
}
