package by.epam.matrix.start;

import by.epam.matrix.entity.Matrix;
import by.epam.matrix.entity.MatrixWithBlock;
import by.epam.matrix.entity.MatrixWithMethod;
import by.epam.matrix.service.Walker;

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
        int wayToReplace;
        int numberOfThreads;

        System.out.println("Enter dimension of matrix");
        matrixDimension=inputIntegerValidation();

        System.out.println("Enter way to replace zeros: 1 - with synchronized block, 2 - with method");
        wayToReplace=inputWayValidation();

        System.out.println("Enter number of threads");
        numberOfThreads=inputIntegerValidation();

        Matrix matrix = null;
        switch (wayToReplace){
            case 1:
                matrix = new MatrixWithBlock(matrixDimension);
                break;
            case 2:
                matrix = new MatrixWithMethod(matrixDimension);
                break;
        }
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
