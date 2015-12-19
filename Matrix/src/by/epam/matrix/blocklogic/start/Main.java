package by.epam.matrix.blocklogic.start;

import by.epam.matrix.blocklogic.entity.MatrixForBlock;
import by.epam.matrix.blocklogic.service.WalkerWithBlock;

import static by.epam.matrix.util.InputOutputUtility.*;

/**
 * <p>Main class of application.</p>
 *
 * @author Veronika
 * @version 1.0
 */
public class Main {

    public static  void main(String args[]){

        int matrixDimension;
        int numberOfThreads;

        System.out.println("Enter dimension of matrix");
        matrixDimension=inputIntegerValidation();

        System.out.println("Enter number of threads");
        numberOfThreads=inputIntegerValidation();

        MatrixForBlock matrix = new MatrixForBlock(matrixDimension);

        printMatrix(matrixDimension, matrix);

        WalkerWithBlock walker=null;
        for (int i = 0; i < numberOfThreads; i++) {
            walker = new WalkerWithBlock("Thread"+i,i+1,matrix);
            walker.start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                walker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printMatrix(matrixDimension, matrix);
    }
}
