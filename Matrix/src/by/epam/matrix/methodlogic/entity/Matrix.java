package by.epam.matrix.methodlogic.entity;

import by.epam.matrix.entity.ParentMatrix;

import java.util.Arrays;

/**
 * Class extends ParentMatrix and has
 * synchronized method which changes zeros
 * on main diagonal of matrix.
 *
 * @author Veronika
 * @see by.epam.matrix.blocklogic.entity.Matrix
 */
public class Matrix extends ParentMatrix {
    private int dimension;
    private int [][] inner;

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

    public synchronized void changeZeroOnMainDiagonal(int changeNumber,int i){
            if (inner[i][i] == 0) {
                inner[i][i] = changeNumber;
            }
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
        if (!super.equals(o)) return false;

        by.epam.matrix.methodlogic.entity.Matrix that = (by.epam.matrix.methodlogic.entity.Matrix) o;

        if (dimension != that.dimension) return false;
        return Arrays.deepEquals(inner, that.inner);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + dimension;
        result = 31 * result + (inner != null ? Arrays.deepHashCode(inner) : 0);
        return result;
    }
}
