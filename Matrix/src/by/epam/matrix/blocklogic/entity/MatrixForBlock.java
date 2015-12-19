package by.epam.matrix.blocklogic.entity;

import by.epam.matrix.entity.Matrix;

import java.util.Arrays;

/**
 * Class contains matrix n x n.
 *
 * @author Veronika
 */
public class MatrixForBlock extends Matrix{
    private int dimension;
    private int [][] inner;

    public MatrixForBlock(int dimension) {
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
        if (inner[i][i] == 0) {
            inner[i][i] = changeNumber;
        }
    }

    @Override
    public String toString() {
        return "MatrixForBlock{" +
                "dimension=" + dimension +
                ", inner=" + Arrays.toString(inner) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatrixForBlock matrix = (MatrixForBlock) o;

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
