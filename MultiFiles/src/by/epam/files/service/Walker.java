package by.epam.files.service;

import java.io.File;

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
    private File file;

    public Walker(String s, int ownNumber, File file){
        super(s);
        this.ownNumber=ownNumber;
        this.file = file;
    }

    /**
     * Try to replace zero on matrix main diagonal and sleep 5 seconds.
     */
    public void run(){
        int millisecondsToSleep=500;

        synchronized (file) {
            //file.SomeMethod();
            try {
                Thread.sleep(millisecondsToSleep);
            } catch (InterruptedException e) {
                System.out.print(e);
            }
        }
    }
}
