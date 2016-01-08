package by.epam.files.util;

import java.util.Scanner;

/**
 * <p>Util class for user input
 * and output matrix in application.</p>
 *
 * @author Veronika
 * @version 1.0
 */
public class InputOutputUtility {

    public InputOutputUtility(){}

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
