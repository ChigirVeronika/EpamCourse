package by.epam.parser.service;

/**
 * <p>Contain constant strings to
 * make clear code reading.
 * </p>
 *
 * @author VeronikaChigir
 * @version 1.0
 */
public class Constant {

    /**
     * Regular expression to parse xml file.
     * Contains groups to distinguish between
     * types of tags, attributes, content.
     * Use in DOMParser class.
     *
     * @see DOMParser
     */
    public static final String FIND_TAGS="(</(?<closeTagName>[\\w]+)>$)?(<(?<name>[\\w]+)\\s?" +
            "(?<attributes>[\\s\\w=\\\"]+)*>)?(<(?<singleTag>[\\w]+)\\s?" +
            "(?<singleTagAttributes>[\\s\\w=\\\"]+)*/>)?(?<text>.[^<]*)?";

    public static final String EMPTY_STRING="";

    public static final String SPACES="\\s+";

    public static final String SPACE="  ";

    public static final String ASSIGNMENT="=";

    public static final String INSTRUCTION="?xml";

    public static final String COMMENT="<!--";

    public Constant(){}
}
