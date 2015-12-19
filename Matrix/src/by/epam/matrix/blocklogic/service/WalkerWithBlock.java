package by.epam.matrix.blocklogic.service;

/**
 * Created by Вероника on 19.12.2015.
 */

import by.epam.matrix.blocklogic.entity.MatrixForBlock;

/**
 * Service class realizes thread logic.
 *
 * @author Veronika
 * @see java.lang.Thread
 */
public class WalkerWithBlock extends Thread {

    /**
     * Number to put in matrix instead of zero
     */
    private int ownNumber;

    private MatrixForBlock matrix;

    public WalkerWithBlock(String s,int ownNumber,MatrixForBlock matrix){
        super(s);
        this.ownNumber=ownNumber;
        this.matrix = matrix;
    }

    /**
     * Try to replace zero on matrix main diagonal and sleep 5 seconds.
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
