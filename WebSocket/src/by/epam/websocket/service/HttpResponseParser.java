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
        }while (!list.get(j).equals(EMPTY_STRING));
        headerList.removeAll(Arrays.asList("\r","","\n"));//delete empty because of while loop

        //что найдем в новом массиве, такую инфу в старый допишем
        if(!headerList.get(0).contains(HTTP)){
            output="Invalid response!";
            return output;
        }
        parseHttp(headerList.get(0));
        for (String line:headerList) {
            System.out.println(line);
        }
        return output;
    }

    private static void parseHttp(String input){
        List<String> list = Arrays.asList(input.split(SPACE));

        list.add(0,list.get(0)+" The protocol version that web server is using\n");
        list.add(1,list.get(1)+" The http response status code\n");
        list.add(2,list.get(2)+" The text version of status code\n");
        StringBuilder sb = new StringBuilder();
        for (String s:list){
            sb.
        }
    }
}
