package by.epam.matrix.util;

import by.epam.matrix.entity.ParentMatrix;

import java.util.Scanner;

/**
 * <p>Util class for input and output in application.</p>
 *
 * @author Veronika
 * @version 1.0
 */
public class InputOutputUtility {

    public InputOutputUtility(){}

    /**
     * Print number of matrix in matrix view.
     *
     * @param n dimension of matrix
     * @param m Matrix class object
     * @see ParentMatrix
     */
    public static void printMatrix(int n,ParentMatrix m){
        System.out.println("Matrix "+n+" x "+n);
        for(int i=0;i<n;i++){
            for (int j = 0; j <n ; j++) {
                System.out.print(m.getInner()[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Process user input and validate it.
     * In case of incorrect input show
     * appropriate message and give another try.
     * Let positive integer only.
     *
     * @return number user entered
     */
    public static int inputIntegerValidation(){
        int number;
        Scanner scanner = new Scanner(System.in);
        do{
            while (!scanner.hasNextInt()) {
                System.out.println("Not a number. Try again:");
                scanner.next();
            }
            number = scanner.nextInt();
            if(number<1) {
                System.out.println("Enter positive integer please:");
            }
        }while (number<1);
        System.out.println("Got it!");
        return number;
    }

    /**
     * Process user input and validate it.
     * In case of incorrect input show
     * appropriate message and give another try.
     * Let only 1 or 2 according to ways user has.
     *
     * @return number user entered
     */
    public static int inputWayValidation(){
        int number=0;
        Scanner scanner = new Scanner(System.in);
        while (!(number==1||number==2)){
            while (!scanner.hasNextInt()) {
                System.out.println("Not a number. Try again: ");
                scanner.next();
            }
            number = scanner.nextInt();
            if(!(number==1||number==2)){
                System.out.println("Enter 1 or 2 please:");
            }
        }
        System.out.println("Got it!");
        return number;
    }
}
