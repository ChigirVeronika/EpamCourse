package by.epam.parser.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Describe element of xml document.</p>
 *
 * @author VeronikaChigir
 * @version 2.0
 */
public class Element {
    private String content;
    private String openName;
    private String closeName;

    /**
     * monitor necessity wrapping child elements in the parent element
     */
    private int range;

    /**
     * list of child elements
     */
    private List<Element> elements = new ArrayList<>();
    private List<Attribute> attributes = new ArrayList<>();

    public Element(){
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOpenName() {
        return openName;
    }

    public void setOpenName(String openName) {
        this.openName = openName;
    }

    public String getCloseName() {
        return closeName;
    }

    public void setCloseName(String closeName) {
        this.closeName = closeName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {this.range = range;}

    public void addElement(Element e){
        elements.add(e);
    }

    public void addAttribute(Attribute a){
        attributes.add(a);
    }

    @Override
    public String toString() {
        return "Element{" +
                "content='" + content + '\'' +
                ", openName='" + openName + '\'' +
                ", closeName='" + closeName + '\'' +
                ", range=" + range +
                ", elements=" + elements +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (range != element.range) return false;
        if (content != null ? !content.equals(element.content) : element.content != null) return false;
        if (openName != null ? !openName.equals(element.openName) : element.openName != null) return false;
        if (closeName != null ? !closeName.equals(element.closeName) : element.closeName != null) return false;
        if (elements != null ? !elements.equals(element.elements) : element.elements != null) return false;
        return !(attributes != null ? !attributes.equals(element.attributes) : element.attributes != null);
    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (openName != null ? openName.hashCode() : 0);
        result = 31 * result + (closeName != null ? closeName.hashCode() : 0);
        result = 31 * result + range;
        result = 31 * result + (elements != null ? elements.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }
}
