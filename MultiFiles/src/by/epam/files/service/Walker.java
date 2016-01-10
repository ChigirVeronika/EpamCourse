package by.epam.files.service;

import by.epam.files.entity.InputFile;

import java.io.*;

/**
 * Service class realizes thread logic.
 *
 * @author Veronika
 * @see Thread
 */
public class Walker extends Thread {

    private static String OUT_FILE_PATH = "E:\\EpamCourse\\MultiFiles\\src\\by\\epam\\files\\release\\out.dat";

    public double ownNumber;
    private InputFile[] files;
    private boolean[] used;

    public Walker(String s, double ownNumber, InputFile[] files, boolean[] used){
        super(s);

        this.ownNumber=ownNumber;
        this.files = files;
        this.used = used;

    }

    public double getOwnNumber() {
        return ownNumber;
    }

    public void setOwnNumber(double ownNumber) {
        this.ownNumber = ownNumber;
    }

    public boolean[] getUsed() {
        return used;
    }

    public void setUsed(boolean[] used) {
        this.used = used;
    }

    /**
     *
     */
    @Override
    public void run(){
        int millisecondsToSleep=3000;
        for (int i = 0; i < files.length; i++) {//todo ÇÀÌÅÍÈÒÜ ÍÀ while Â ÌÀÑÑÈÂÅ ÍÅ ÍÀÉÄÅÒÑß false
            synchronized(files[i]) {
                if (used[i] == false) {
                    String path = files[i].getPath();
                    ownNumber = files[i].doWork(path);
                    setOwnNumber(ownNumber);
                    PrintWriter out = null;
                    try {
                        out = new PrintWriter(new BufferedWriter(new FileWriter(OUT_FILE_PATH, true)));
                        out.println(ownNumber);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        out.close();
                    }

                    used[i] = true;
                }

                try {
                    Thread.sleep(millisecondsToSleep);
                } catch (InterruptedException e) {
                    System.out.print(e);
                }
            }

        }

    }
}
