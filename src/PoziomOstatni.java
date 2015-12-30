/**
 * Created by Amadeusz on 30.12.2015.
 */
import javax.swing.*;

/**
 * Created by Amadeusz on 28.12.2015.
 */
public class PoziomOstatni extends Poziom {

    int aktualnyPoziom = 3;

    public  PoziomOstatni (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, int punkty, String plik, int aktualnyPoziom)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, plik, aktualnyPoziom);
        this.punkty = punkty;
        this.aktualnyPoziom = aktualnyPoziom;
        punktyLBL.setText("Punkty:" + punkty);
    }

    public void wygrana()
    {
        czasDoKoncaTimer.stop();
        czyWygrano = true;
        dynamit.setIcon(new ImageIcon("image//dynamit.png"));
        liczPunkty();
        czyWygrano = true;
        opoznij(500);
        zakonczenieGry();
    }
}
