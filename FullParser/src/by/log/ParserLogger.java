package by.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logging class.
 */
public class ParserLogger {

    private static Logger logger;

    public static void init(){
        logger = LogManager.getLogger("test-fullparser");
    }

    public static Logger getLogger(){
        return logger;
    }

}
