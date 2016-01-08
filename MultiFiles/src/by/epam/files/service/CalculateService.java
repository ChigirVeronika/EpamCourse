package by.epam.files.service;

import java.util.Scanner;

/**
 * Created by Вероника on 08.01.2016.
 */
public class CalculateService {
    public String calculateSum(String input){
        double[] array=new double[]{};
        int i = 0;
        String output="";
        Scanner fi = new Scanner(input);
        while(fi.hasNext()){
            if (fi.hasNextDouble()) {
                System.out.println("Double: " + fi.nextDouble());
                array[i]=fi.nextDouble();
            }
            else {
                break;
            }
        }

        return output;
    }

}
