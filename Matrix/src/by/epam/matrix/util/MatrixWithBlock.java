package by.epam.matrix.util;


import java.util.Arrays;

/**
 * Created by Вероника on 17.12.2015.
 */
public class MatrixWithBlock extends Matrix{
    public int dimension;
    public int [][] inner;

    public MatrixWithBlock(int dimension) {
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

    public void changeZeroOnMainDiagonal(int changeNumber,int i){
        synchronized (this) {
            if (inner[i][i] == 0) {
                inner[i][i] = changeNumber;
            }
        }
    }

    @Override
    public String toString() {
        return "MatrixWithMethod{" +
                "inner=" + Arrays.toString(inner) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatrixWithBlock matrixWithBlock = (MatrixWithBlock) o;

        return Arrays.deepEquals(inner, matrixWithBlock.inner);

    }

    @Override
    public int hashCode() {
        return inner != null ? Arrays.deepHashCode(inner) : 0;
    }
}
