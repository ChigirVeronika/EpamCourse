/**
 * Created by ¬ероника on 12.10.2015.
 */
public class NewYear {
    public  static  void main(String arsg[])throws CloneNotSupportedException{
        Fir myFirTree = new Fir();
        //вешаем на ель игрушки
        myFirTree.addFirToy("blue","big");
        myFirTree.addFirToy("red","big");
        myFirTree.addFirToy("blue","small");
        myFirTree.addFirToy("black","big");

        Fir f =null;
        f.toString();

        System.out.println(myFirTree.allFirToys());
        System.out.println(myFirTree.firToysCount);

        myFirTree.removeFirToy(2);//снимаем с ели

        System.out.println(myFirTree.allFirToys());
        System.out.println(myFirTree.firToysCount);

        //System.out.println(myFirTree.getFirToyByContent(`````````````);

    }

}
