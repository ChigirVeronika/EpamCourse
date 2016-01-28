package by.epam.websocket.service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse header of http response.
 */
public class HttpResponseParser {
    private final static String HTTP_STRING = "(HTTP/\\d\\.\\d)\\s(\\d+)\\s([a-zA-Z&&[^HTTP]]+)";

    private final static String NEXT_LINE = "\n";
    private final static String EMPTY_SIGN = "\r";
    private final static String HTTP = "HTTP";
    private final static String COLON = ": ";
    private final static String NAME = "Name: ";
    private final static String VALUE = " Value: ";
    private final static String INVALID ="Invalid response!";

    private final static String HTTP_PROTOCOL_VERSION = " The protocol version that web server is using\n";
    private final static String HTTP_CODE_STATUS = " The http response status code\n";
    private final static String HTTP_TEXT_STATUS = " The text version of status code\n";

    public HttpResponseParser(){}

    public static String parse(String input){
        List<String> list = Arrays.asList(input.split(NEXT_LINE));

        List<String> headerList=formHeaderList(list);

        if(!headerList.get(0).contains(HTTP)){
            return INVALID;
        }

        return parseHttpString(headerList.get(0))+parseHeaderStrings(headerList)+formBody(list,headerList);
    }

    private static String parseHttpString(String input){
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(HTTP_STRING);
        Matcher matcher=pattern.matcher(input);
        while (matcher.find()){
            if (matcher.group(1) != null) {
                sb.append(matcher.group(1)+HTTP_PROTOCOL_VERSION);
            }
            if (matcher.group(2) != null) {
                sb.append(matcher.group(2)+HTTP_CODE_STATUS);
            }
            if (matcher.group(3) != null) {
                sb.append(matcher.group(3)+HTTP_TEXT_STATUS);
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

    private static String formBody(List<String> list,List<String> header){
        List<String > body = new ArrayList<>();
        for (int i=header.size();i<list.size();i++){
            body.add(list.get(i));
        }
        StringBuilder sb = new StringBuilder();
        for (String s:body){
            sb.append(s);
            sb.append(NEXT_LINE);
        }
        return sb.toString();
    }

    private static List<String> formHeaderList(List<String> list){
        List<String> headerList =new ArrayList<>();
        int j = 0;
        do {//try to find empty string - means border between header and body
            headerList.add(list.get(j));
            j++;
        }while (!list.get(j).equals(EMPTY_SIGN));
        return headerList;
    }
}
