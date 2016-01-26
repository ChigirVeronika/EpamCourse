package by.epam.websocket.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parse header of http response.
 */
public class HttpResponseParser {
    private static final String EMPTY_STRING = "";
    private static final String NEXT_LINE="\n";

    public HttpResponseParser(){}

    public static String parse(String input){
        String output ="FUTURE OUTPUT";
        List<String> list = Arrays.asList(input.split(NEXT_LINE));
//        for (String line:list) {
//            System.out.println("LINE."+line);
//        }
        //todo пока не найдем пустую строку, будем парсить
        List<String> headerList =new ArrayList<>();
        int j = 0;
        do {
            headerList.add(list.get(j));
            j++;
        }while (!list.get(j).equals(EMPTY_STRING));
        headerList.removeAll(Arrays.asList("\r\n"));//delete empty because of while loop
        //что найдем в новом массиве, такую инфу в стары й допишкм
        for (String line:headerList) {
            System.out.println(line);
        }
        System.out.println(headerList.get(6).length());
        return output;
    }
}
