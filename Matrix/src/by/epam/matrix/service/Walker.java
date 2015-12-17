package by.epam.matrix.service;

import by.epam.matrix.entity.Matrix;

/**
 * Created by Вероника on 17.12.2015.
 */
public class Walker extends Thread {

    private int ownNumber;

    private Matrix matrix;

    public Walker(String s,int ownNumber,Matrix matrix){
        super(s);
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
