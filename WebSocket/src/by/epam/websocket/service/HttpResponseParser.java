package by.epam.websocket.service;

import java.util.*;
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
    private static final String COLON =": ";
    private static final String NAME ="Name: ";
    private static final String VALUE =" Value: ";

    public HttpResponseParser(){}

    public static String parse(String input){
        List<String> list = Arrays.asList(input.split(NEXT_LINE));

        List<String> headerList =new ArrayList<>();
        int j = 0;
        do {//try to find empty string - means border between header and body
            headerList.add(list.get(j));
            j++;
        }while (!list.get(j).equals(EMPTY_SIGN));
//        List<String> bodyList =new ArrayList<>();
//        for (int i=headerList.size();i<list.size();i++){
//            bodyList.add(list.get(i));
//        }
        if(!headerList.get(0).contains(HTTP)){
            return "Invalid response!";
        }

        return parseHttpString(headerList.get(0))+parseHeaderStrings(headerList)+formBody(list);
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
        return sb.toString();
    }

    private static String parseHeaderStrings(List<String> l){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < l.size(); i++) {
            String [] nameAndValue=l.get(i).split(COLON);
            sb.append(NAME+nameAndValue[0]+VALUE+nameAndValue[1]);
            sb.append(NEXT_LINE);
        }
        return sb.toString();
    }

    private static String formBody(List<String > l){
        StringBuilder sb = new StringBuilder();
        for (int i=l.size();i<l.size();i++){
            sb.append(l.get(i));
            sb.append(NEXT_LINE);

        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
