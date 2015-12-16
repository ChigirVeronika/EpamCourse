package by.epam.matrix.block;


import java.util.Arrays;

/**
 * Created by Вероника on 17.12.2015.
 */
public class Matrix {
    public int [][] inner;

    Matrix(int n){
        inner=new int[n][n];
    }

    public int[][] getInner() {
        return inner;
    }

    public void setInner(int[][] inner) {
        this.inner = inner;
    }

    public void changeNumber(){
        
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "inner=" + Arrays.toString(inner) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix = (Matrix) o;

        return Arrays.deepEquals(inner, matrix.inner);

    }

    @Override
    public int hashCode() {
        return inner != null ? Arrays.deepHashCode(inner) : 0;
    }
}
