package by.epam.matrix.start;

import by.epam.matrix.util.Matrix;
import by.epam.matrix.util.MatrixWithBlock;
import by.epam.matrix.util.MatrixWithMethod;
import by.epam.matrix.thread.Walker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Вероника on 17.12.2015.
 */
public class Main {

    public static  void main(String args[]){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //to create and output matrix
        String dimensionString;
        int n=0;
        //to choose way
        String solutionString;
        int way;
        //to choose number of threads
        String threadString;
        int threadNumber;
        try {
            System.out.println("Enter dimension of matrix");
            dimensionString = reader.readLine();
            n=Integer.parseInt(dimensionString);

            System.out.println("Enter way of solution: 1 - with blocks, 2 - with methods");
            solutionString = reader.readLine();
            way=Integer.parseInt(solutionString);

            System.out.println("Enter number of threads");
            threadString = reader.readLine();
            threadNumber=Integer.parseInt(threadString);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//stop running
        }

        Matrix matrix = null;
        switch (way){
            case 1:
                matrix = new MatrixWithBlock(n);
                break;
            case 2:
                matrix = new MatrixWithMethod(n);
                break;
        }
        printMatrix(n, matrix);

        int threadFirstNumber=1;
        int threadSecondNumber=2;

        Walker walker1 = new Walker(threadFirstNumber, matrix);
        Walker walker2 = new Walker(threadSecondNumber, matrix);

        walker1.start();
        walker2.start();

        try {
            walker1.join();
            walker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        printMatrix(n, matrix);



    }

    public static void printMatrix(int n,Matrix m){
        System.out.println("matrix "+n+" x "+n);
        for(int i=0;i<n;i++){
            for (int j = 0; j <n ; j++) {
                System.out.print(m.getInner()[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
