import java.io.Serializable;

/**
 * Created by Вероника on 12.10.2015.
 */
public class FirToy implements Serializable,Cloneable {
    private final long id;
    private String color;
    private String size;
    FirToy(long id, String color, String size) {
        this.id = id;
        this.color = color;
        this.size = size;
    }
    @Override
    public String toString() {
        return size + " and " + color + " ";
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FirToy other = (FirToy) obj;
        if ((this.color == null) ? (other.color != null) : !this.color.equals(other.color)) {
            return false;
        }
        if ((this.size == null) ? (other.size != null) : !this.size.equals(other.size)) {
            return false;
        }
        return true;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public long getId() {
        return id;
    }
    public String getColor() {
        return color;
    }
    public String getSize() {
        return size;
    }
}
