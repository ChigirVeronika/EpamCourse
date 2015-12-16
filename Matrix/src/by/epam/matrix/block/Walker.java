package by.epam.matrix.block;

/**
 * Created by Вероника on 17.12.2015.
 */
public class Walker extends Thread {

    public int ownNumber;

    private Matrix matrix;

    public Walker(int ownNumber,Matrix matrix){
        this.ownNumber=ownNumber;
        this.matrix=matrix;
    }

    public void run(){

        for(int i=0;i<3;i++){
            System.out.println(ownNumber);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.print(e);
            }
        }
    }
}
