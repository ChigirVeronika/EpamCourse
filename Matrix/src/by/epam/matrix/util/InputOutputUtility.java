package by.epam.matrix.util;

import by.epam.matrix.entity.Matrix;

import java.util.Scanner;

/**
 * Created by Вероника on 17.12.2015.
 */
public class InputOutputUtility {
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

    public static int inputIntegerValidation(){
        int number=0;
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
