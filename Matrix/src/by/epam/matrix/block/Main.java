package by.epam.matrix.block;

/**
 * Created by Вероника on 17.12.2015.
 */
public class Main {

    public static  void main(String args[]){
        int n=3;
        Matrix matrix = new Matrix(n);
        printMatrix(n,matrix);

        Walker walker = new Walker(1);
        walker.start();

    }

    public static void printMatrix(int n,Matrix m){
        for(int i=0;i<n;i++){
            for (int j = 0; j <n ; j++) {
                System.out.print(m.getInner()[i][j]);
            }
            System.out.println();
        }
    }
}
