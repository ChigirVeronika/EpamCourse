package by.epam.matrix.blocklogic.service;

import by.epam.matrix.blocklogic.entity.Matrix;

/**
 * Service class realizes thread logic.
 *
 * @author Veronika
 * @see java.lang.Thread
 */
public class Walker extends Thread {

    /**
     * Number to put in matrix instead of zero
     */
    private int ownNumber;

    private Matrix matrix;

    public Walker(String s, int ownNumber, Matrix matrix){
        super(s);
        this.ownNumber=ownNumber;
        this.matrix = matrix;
    }

    /**
     * Try to replace zero on matrix main diagonal and sleep 1,5 seconds.
     */
    public void run(){
        int millisecondsToSleep=150;

        for(int i=0;i< matrix.getDimension();i++){
            synchronized (matrix) {
                matrix.changeZeroOnMainDiagonal(ownNumber, i);
                try {
                    Thread.sleep(millisecondsToSleep);
                } catch (InterruptedException e) {
                    System.out.print(e);
                }
            }
        }
    }
}
