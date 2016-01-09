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

    public void setOwnNumber(int ownNumber) {
        this.ownNumber = ownNumber;
    }

    public boolean[] getUsed() {
        return used;
    }

    public void setUsed(boolean[] used) {
        this.used = used;
    }

    /**
     * .
     */
    public void run(){
        int millisecondsToSleep=500;

        synchronized(this) {
            //îòêğûòü ôàéë output.dat è ïîñ÷èòàòü êîëè÷åñòâî ñòğîê todo ÒÅÏÅĞÜ ÍÅ ÍÀÄÎ
//            File outFile = new File("E:\\EpamCourse\\MultiFiles\\src\\by\\epam\\files\\resource\\release\\out.dat");
//            int outCount =  FileService.getStringCount(outFile);
            for (int i = 0; i < files.length; i++) {//todo ÇÀÌÅÍÈÒÜ ÍÀ while Â ÌÀÑÑÈÂÅ ÍÅ ÍÀÉÄÅÒÑß false
                if(used[i]==false){
                    String path=files[i].getPath();
                    System.out.println(files[i]);
                    ownNumber=files[i].doWork(path);
                    used[i]=true;
                    //break;
                }
            }

            try {
                Thread.sleep(millisecondsToSleep);
            } catch (InterruptedException e) {
                System.out.print(e);
            }
        }
    }
}
