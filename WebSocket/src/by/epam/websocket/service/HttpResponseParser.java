package by.epam.websocket.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parse header of http response.
 */
public class HttpResponseParser {
    private static final String STATUS_CODE = "(\\s{1}\\d+)";
    private static final String TEXT_CODE = "([a-zA-Z&&[^HTTP]])";
    

    private static final String NEXT_LINE="\n";
    private static final String EMPTY_SIGN="\r";
    private static final String HTTP ="HTTP";
    private static final String SPACE =" ";

    public HttpResponseParser(){}

    public static String parse(String input){
        String output ="FUTURE OUTPUT";
        List<String> list = Arrays.asList(input.split(NEXT_LINE));

        List<String> headerList =new ArrayList<>();
        int j = 0;
        do {//try to find empty string - means border between header and body
            headerList.add(list.get(j));
            j++;
        }while (!list.get(j).equals(EMPTY_SIGN));

        if(!headerList.get(0).contains(HTTP)){
            output="Invalid response!";
            return output;
        }
        list.set(0,parseHttp(headerList.get(0)));
        headerList.remove(0);
        //todo тут потом придется в итый класть новый i+1

        //todo что найдем в новом массиве, такую инфу в старый допишем
//        for (String line:headerList) {
//            System.out.println(line);
//        }

        //todo hash map is a good idea

        return output;
    }

    private static String parseHttp(String input){
        List<String> list = Arrays.asList(input.split(SPACE));

        list.set(0,list.get(0)+" The protocol version that web server is using\n");
        list.set(1,list.get(1)+" The http response status code\n");
        list.set(2,list.get(2)+" The text version of status code\n");
        StringBuilder sb = new StringBuilder();
        for (String s: list){
            sb.append(s);
        }
        input=sb.toString();
        return input;
    }
}
