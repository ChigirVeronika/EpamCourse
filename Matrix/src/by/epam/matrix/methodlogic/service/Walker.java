package by.epam.matrix.methodlogic.service;

import by.epam.matrix.blocklogic.entity.MatrixForBlock;
import by.epam.matrix.methodlogic.entity.MatrixWithMethod;

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

    private MatrixWithMethod matrix;

    public Walker(String s,int ownNumber,MatrixWithMethod matrix){
        super(s);
        this.ownNumber=ownNumber;
        this.matrix = matrix;
    }

    /**
     * Try to replace zero on matrix main diagonal and sleep 5 seconds.
     */
    public void run(){
        int millisecondsToSleep=500;

        for(int i=0;i< matrix.getDimension();i++){
            matrix.changeZeroOnMainDiagonal(ownNumber, i);
            try{
                Thread.sleep(millisecondsToSleep);
            }catch (InterruptedException e){
                System.out.print(e);
            }
        }
    }
}
