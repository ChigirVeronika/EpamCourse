package by.epam.multifile.service;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Service class realizes thread logic.
 *
 * @author Veronika
 * @see Thread
 */
public class OperationExecutor extends Thread {

    private double ownNumber;
    private InputFile[] files;
    private boolean[] used;

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final Logger LOGGER = Logger.getLogger( OperationExecutor.class);

    public OperationExecutor(String s, double ownNumber, InputFile[] files, boolean[] used){
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
        int millisecondsToSleep=5000;
        for (int i = 0; i < files.length; i++) {
            rwLock.writeLock().lock();
            //synchronized(files[i]) {
                if (used[i] == false) {
                    String path = files[i].getPath();
                    ownNumber = ownNumber+files[i].executeOperation(path);//app doesn't how many threads will be here
                    used[i] = true;
                }
                try {
                    Thread.sleep(millisecondsToSleep);
                } catch (InterruptedException e) {
                    LOGGER.error(e);
                }
            //}
            rwLock.writeLock().unlock();
        }
    }

    @Override
    public String toString() {
        return "OperationExecutor{" +
                "ownNumber=" + ownNumber +
                ", multifile=" + Arrays.toString(files) +
                ", used=" + Arrays.toString(used) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationExecutor operationExecutor = (OperationExecutor) o;

        if (Double.compare(operationExecutor.ownNumber, ownNumber) != 0) return false;

        if (!Arrays.equals(files, operationExecutor.files)) return false;
        return Arrays.equals(used, operationExecutor.used);

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
