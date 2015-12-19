package by.epam.matrix.methodlogic.start;

import by.epam.matrix.blocklogic.service.WalkerWithBlock;
import by.epam.matrix.methodlogic.entity.MatrixWithMethod;
import by.epam.matrix.methodlogic.service.Walker;

import static by.epam.matrix.util.InputOutputUtility.inputIntegerValidation;
import static by.epam.matrix.util.InputOutputUtility.printMatrix;

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

        MatrixWithMethod matrix = new MatrixWithMethod(matrixDimension);

        printMatrix(matrixDimension, matrix);

        Walker walker=null;
        for (int i = 0; i < numberOfThreads; i++) {
            walker = new Walker("Thread"+i,i+1,matrix);
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
