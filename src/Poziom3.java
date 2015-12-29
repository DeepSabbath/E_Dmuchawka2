import javax.swing.*;

/**
 * Created by Amadeusz on 28.12.2015.
 */
public class Poziom3 extends Poziom {

    int aktualnyPoziom = 3;

    public  Poziom3 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, int punkty, String plik)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, plik);
        this.punkty = punkty;
    }

    public void wygrana()
    {
        czasDoKoncaTimer.stop();
        czyWygrano = true;
        dynamit.setIcon(new ImageIcon("image//dynamit.png"));
        liczPunkty();
        wynikGry.setText("Wygrales");
        czyWygrano = true;
        opoznij(500);
        zakonczenieGry();

        /*removeAll();
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(aktualnyPoziom,punkty,punktyZaPoziom,poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();*/
    }
}
