import javax.swing.*;

/**
 * Created by Amadeusz on 27.12.2015.
 */
public class Poziom2 extends Poziom {

    int aktualnyPoziom = 2;

    public  Poziom2 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, int punkty, String plik)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, plik);
        punktyLBL.setText("Punkty:" + punkty);
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
        removeAll();
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(aktualnyPoziom,punkty,punktyZaPoziom,poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();
    }
}