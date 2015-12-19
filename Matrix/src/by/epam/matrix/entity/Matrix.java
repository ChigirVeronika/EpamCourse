package by.epam.matrix.entity;

import java.util.Arrays;

/**
 * Created by Вероника on 19.12.2015.
 */
public class Matrix {
    private int dimension;
    private int [][] inner;

    public Matrix() {
    }

    public Matrix(int dimension) {
        this.dimension = dimension;
        inner = new int[dimension][dimension];
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
        return "Matrix{" +
                "dimension=" + dimension +
                ", inner=" + Arrays.toString(inner) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix = (Matrix) o;

        if (dimension != matrix.dimension) return false;
        return Arrays.deepEquals(inner, matrix.inner);

    }

    @Override
    public int hashCode() {
        int result = dimension;
        result = 31 * result + (inner != null ? Arrays.deepHashCode(inner) : 0);
        return result;
    }
}
