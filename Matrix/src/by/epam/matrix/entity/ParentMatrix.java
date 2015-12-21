package by.epam.matrix.entity;

import java.util.Arrays;

/**
 * Class ParentMatrix for another
 * matrix and to improve utilities
 * and output.
 *
 * @author Veronika
 */
public class ParentMatrix {
    private int dimension;
    private int [][] inner;

    public ParentMatrix() {
    }

    public int[][] getInner() {
        return inner;
    }

    public void setInner(int[][] inner) {
        this.inner = inner;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "ParentMatrix{" +
                "dimension=" + dimension +
                ", inner=" + Arrays.toString(inner) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParentMatrix parentMatrix = (ParentMatrix) o;

        if (dimension != parentMatrix.dimension) return false;
        return Arrays.deepEquals(inner, parentMatrix.inner);

    }

    @Override
    public int hashCode() {
        int result = dimension;
        result = 31 * result + (inner != null ? Arrays.deepHashCode(inner) : 0);
        return result;
    }
}
