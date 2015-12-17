package by.epam.matrix.thread;

import by.epam.matrix.util.Matrix;
import by.epam.matrix.util.MatrixWithBlock;

/**
 * Created by Вероника on 17.12.2015.
 */
public class Walker extends Thread {

    public int ownNumber;

    private Matrix matrix;

    public Walker(int ownNumber,Matrix matrix){
        this.ownNumber=ownNumber;
        this.matrix = matrix;
    }

    public void run(){
        int secondsToSleep=500;

        for(int i=0;i< matrix.getDimension();i++){
            matrix.changeZeroOnMainDiagonal(ownNumber, i);
            try{
                Thread.sleep(secondsToSleep);
            }catch (InterruptedException e){
                System.out.print(e);
            }
        }
    }
}
