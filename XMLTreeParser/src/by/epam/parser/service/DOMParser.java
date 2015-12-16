package by.epam.parser.service;

import by.epam.parser.entity.Attribute;
import by.epam.parser.entity.Document;
import by.epam.parser.entity.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.parser.service.Constant.*;

/**
 * <p>Parse xml file to elements tree structure.
 * Form total document object.</p>
 *
 * @author VeronikaChigir
 */
public class DOMParser implements Parser{

    /**
     * Help to work with elements
     * during looking for new tags.
     */
    private ArrayDeque<Element> tagsStack;

    /**
     * Help to work with elements
     * during formation of the document.
     */
    private ArrayDeque<Element> documentStack;

    private int fullCloseTagGroup = 1;
    private int closeTagNameGroup = 2;
    private int fullOpenTagGroup = 3;
    private int openTagNameGroup = 4;
    private int openTagAttributesGroup = 5;
    private int fullSingleTagGroup = 6;
    private int singleTagNameGroup = 7;
    private int singleTagAttributesGroup = 8;
    private int tagContentGroup = 9;

    public DOMParser(){}

    /**
     * <p>Parse xml file to elements tree structure.
     * Form total document object.</p>
     *
     * @param reader for reading character streams
     * @return total document object
     * @throws ParseException if something wrong with parsing
     */
    @Override
    public Document parse(Reader reader) throws ParseException {
        Document document=new Document();
        BufferedReader read = new BufferedReader(reader);
        String line;
        try{
            tagsStack=new ArrayDeque<>();
            documentStack=new ArrayDeque<>();
            line = read.readLine();
            Pattern pattern = Pattern.compile(FIND_TAGS);
            Matcher matcher;
            int currentRange = 1;
            do{
                matcher=pattern.matcher(line);
                while (matcher.find()){
                    if (matcher.group(fullOpenTagGroup) != null) {//поиск и запись открывающих тегов
                        Element e = new Element();
                        parseOpenTag(matcher,currentRange,e);
                        currentRange++;
                    }
                    if (matcher.group(tagContentGroup) != null) {//содержимое тегов
                        if(notInstructionAndComment(matcher.group(tagContentGroup))) {
                            parseContent(matcher);
                        }
                    }
                    if (matcher.group(fullCloseTagGroup) != null) {//поиск закрывающего тега и запись элемента
                        Element e = tagsStack.pop();
                        currentRange--;
                        parseCloseTag(matcher, currentRange, e);
                    }
                    if (matcher.group(fullSingleTagGroup) != null) {//одиночные теги
                        parseSingleTag(matcher,currentRange);
                    }
                }
                line=read.readLine();
            }while (line!=null);
            document.setRoot(documentStack.pop());
        }catch (Exception e){
            throw  new ParseException(e);
        }finally {
            try {
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  document;
    }

    /**
     * <p>For element form list of attributes.
     * Regard that in input xml file attributes split by spaces.</p>
     *
     * @param inputStringWithAttributes
     * @param currentElement
     */
    public void createAttributesList(String inputStringWithAttributes, Element currentElement){
        String[] result=inputStringWithAttributes.split(SPACES);
        for(String str: result){
            String[] s = str.split(ASSIGNMENT);
            String name = s[0];
            String value = s[1];
            currentElement.addAttribute(new Attribute(name,value));
        }
    }

    /**
     * <p>Add content to current element.</p>
     *
     * @param tagMatcher is matcher
     */
    public void parseContent(Matcher tagMatcher){
        String currentContent=tagMatcher.group(tagContentGroup);
        Element e = tagsStack.pop();
        if(!currentContent.contains(SPACE)) {
            e.setContent(currentContent);
        }else{
            e.setContent(EMPTY_STRING);
        }
        tagsStack.push(e);
    }

    /**
     * <p>Parse and add single tag to document stack.</p>
     *
     * @param tagMatcher is matcher
     * @param tagRange is range - to monitor necessity wrapping child elements in the parent element
     */
    public void parseSingleTag(Matcher tagMatcher, int tagRange){
        Element e = new Element();
        e.setOpenName(tagMatcher.group(singleTagNameGroup));
        if (tagMatcher.group(singleTagAttributesGroup) != null) {
            createAttributesList(tagMatcher.group(singleTagAttributesGroup), e);
        }
        e.setContent(EMPTY_STRING);
        e.setRange(tagRange);
        e.setCloseName(tagMatcher.group(singleTagNameGroup));
        documentStack.push(e);
    }

    /**
     * <p>Parse open tag and its attributes.
     * Form tags stack.</p>
     *
     * @param tagMatcher is matcher
     * @param tagRange is range - to monitor necessity wrapping child elements in the parent element
     * @param element is current element
     */
    public void parseOpenTag(Matcher tagMatcher, int tagRange, Element element){
        element.setOpenName(tagMatcher.group(openTagNameGroup));
        if (tagMatcher.group(openTagAttributesGroup) != null) {
            createAttributesList(tagMatcher.group(openTagAttributesGroup), element);
        }
        element.setRange(tagRange);
        tagsStack.push(element);
    }

    /**
     * <p>Parse close tag. Form document stack.
     * Loop monitors necessity wrapping child elements
     * in the parent element</p>
     *
     * @param tagMatcher is matcher
     * @param tagRange is range - to monitor necessity wrapping child elements in the parent element
     */
    public void parseCloseTag(Matcher tagMatcher, int tagRange, Element e){
        String currentCloseTagName = tagMatcher.group(closeTagNameGroup);
        if (e.getOpenName().equals(currentCloseTagName)){//имена совпали
            e.setCloseName(currentCloseTagName);
            if (!documentStack.isEmpty()) {
                if (e.getRange() < documentStack.peek().getRange()) {//ранг текущего эл-а < верхнего на documentStack
                    for (Element el : documentStack) {//упаковываем эл-ты с documentStack в текущий элемент
                        el = documentStack.pop();
                        e.addElement(el);
                    }
                }
            }
            documentStack.push(e);
        }else{
            try {
                throw new ParseException("Invalid document. Parsing is incorrect.", new Exception());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);//stop running
            }
        }
    }

    /**
     *
     * @param current
     * @return true if string contains tags or content,
     * false - if string is process instruction or comment
     */
    public boolean notInstructionAndComment(String current){
        if(current.contains(INSTRUCTION)){
            return false;
        }
        if(current.contains(COMMENT)){
            return false;
        }
        return  true;
    }
}
