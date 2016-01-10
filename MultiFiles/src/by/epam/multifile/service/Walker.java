package by.epam.multifile.service;

import by.epam.multifile.entity.InputFile;

import java.io.*;
import java.util.Arrays;

/**
 * Service class realizes thread logic.
 *
 * @author Veronika
 * @see Thread
 */
public class Walker extends Thread {

    private static String OUT_FILE_PATH = "E:\\EpamCourse\\MultiFiles\\src\\by\\epam\\multifile\\release\\out.dat";

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

    public void setOwnNumber(double ownNumber) {
        this.ownNumber = ownNumber;
    }

    public boolean[] getUsed() {
        return used;
    }

    public void setUsed(boolean[] used) {
        this.used = used;
    }

    public InputFile[] getFiles() {
        return files;
    }

    public void setFiles(InputFile[] files) {
        this.files = files;
    }

    @Override
    public void run(){
        int millisecondsToSleep=3000;
        for (int i = 0; i < files.length; i++) {
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

    @Override
    public String toString() {
        return "Walker{" +
                "ownNumber=" + ownNumber +
                ", multifile=" + Arrays.toString(files) +
                ", used=" + Arrays.toString(used) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Walker walker = (Walker) o;

        if (Double.compare(walker.ownNumber, ownNumber) != 0) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(files, walker.files)) return false;
        return Arrays.equals(used, walker.used);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(ownNumber);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (files != null ? Arrays.hashCode(files) : 0);
        result = 31 * result + (used != null ? Arrays.hashCode(used) : 0);
        return result;
    }
}
