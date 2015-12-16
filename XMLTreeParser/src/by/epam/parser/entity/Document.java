package by.epam.parser.entity;

/**
 * <p>Help to create and work with object Document
 * which is an object representation of xml document.
 * Root is only one element contains list of its child
 * elements.</p>
 *
 * @author VeronikaChigir
 * @version 2.0
 */
public class Document {
    private Element root;

    public Document(){}

    public Element getRoot() {
        return root;
    }

    public void setRoot(Element root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "Document{" +
                "root=" + root +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        return !(root != null ? !root.equals(document.root) : document.root != null);

    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
    }
}
