package by.epam.matrix.methodlogic.start;

import by.epam.matrix.methodlogic.entity.Matrix;
import by.epam.matrix.methodlogic.service.Walker;

import static by.epam.matrix.util.InputOutputUtility.*;

/**
 * <p>Main class of part with synchronized method.</p>
 *
 * @author Veronika
 * @version 1.0
 */
public class Main {

    public static  void main(String args[]){
        System.out.println("Matrix changing with synchronized method.");

        System.out.println("Enter dimension of matrix");
        int matrixDimension=inputIntegerValidation();

        System.out.println("Enter number of threads");
        int numberOfThreads=inputIntegerValidation();

        Matrix matrix = new Matrix(matrixDimension);

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
