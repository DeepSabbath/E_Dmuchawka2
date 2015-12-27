/**
 * Created by Amadeusz on 27.12.2015.
 */
public class Poziom2 extends Poziom {


    public  Poziom2 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, int punkty)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom);
        System.out.println("Punkty: " + punkty);
        punktyLBL.setText("Punkty:" + punkty);
        this.punkty = punkty;
        System.out.println("Punkty: " + punkty);

    }
}