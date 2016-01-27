package by.epam.websocket.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse header of http response.
 */
public class HttpResponseParser {
    private static final String HTTP_STRING = "(HTTP/\\d\\.\\d)\\s(\\d+)\\s([a-zA-Z&&[^HTTP]]+)";

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

        list.set(0,parseHttpString(headerList.get(0)));
        headerList.remove(0);
        //todo тут потом придется в итый класть новый i+1

        //todo что найдем в новом массиве, такую инфу в старый допишем


        //todo hash map is a good idea

        return output+list.get(0);
    }

    private static String parseHttpString(String input){
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(HTTP_STRING);
        Matcher matcher=pattern.matcher(input);
        while (matcher.find()){
            if (matcher.group(1) != null) {
                sb.append(matcher.group(1)+" The protocol version that web server is using\n");
            }
            if (matcher.group(2) != null) {
                sb.append(matcher.group(2)+" The http response status code\n");
            }
            if (matcher.group(3) != null) {
                sb.append(matcher.group(3)+" The text version of status code\n");
            }
        }
        input = sb.toString();
        return input;
    }

    private static String parseHeaderStrings(String input){

        return input;
    }
}
