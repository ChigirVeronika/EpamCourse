package com.epam.restaurant.util;



import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;

/**
 * Change string to utf-8 charset.
 */
public class CharsetUtil {
    private static final Logger LOGGER = Logger.getLogger(CharsetUtil.class);

    private static String UTF_8 = "UTF-8";
    private static String ISO = "ISO-8859-1";

    public static String encodeToUTF8(String inputString) {

        String value = new String();
        byte ptext[];
        try {
            ptext = inputString.getBytes(ISO);
            value = new String(ptext, UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("UnsupportedEncodingException in CharsetUtil Class");
        }

        return value;
    }
}
