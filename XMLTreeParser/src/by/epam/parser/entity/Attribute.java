package by.epam.parser.entity;

/**
 * <p>Describe Attribute entity.
 * Attribute is a pare name=value inside open tag.
 * </p>
 *
 * @author VeronikaChigir
 * @version 2.0
 */
public class Attribute {
    private String name;
    private String value;

    /**
     * This constructor requires all fields to be passed as parameters.
     *
     * @param name - attribute name
     * @param value - attribute value in quotes
     */
    public Attribute(String name,String value){
        this.name=name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (name != null ? !name.equals(attribute.name) : attribute.name != null) return false;
        return !(value != null ? !value.equals(attribute.value) : attribute.value != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
