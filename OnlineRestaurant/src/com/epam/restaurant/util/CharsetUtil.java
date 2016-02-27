package com.epam.restaurant.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.*;

/**
 * Created by Вероника on 27.02.2016.
 */
public class CharsetUtil {
    public static String StringToUtf8(String oldString){

//        Charset set = Charset.forName("UTF-8");
//        ByteBuffer buf = set.encode(oldString);
//        byte[] b = buf.array();
//        String newString = new String(b);
//
//        try {
//            byte[] ptext = oldString.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();//// TODO: 28.02.2016
//        }

        byte ptext[] = oldString.getBytes(ISO_8859_1);
        String value = new String(ptext, UTF_8);
        return value;
    }
}
