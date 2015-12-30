import javax.swing.*;

/**
 * Created by Amadeusz on 27.12.2015.
 */
public class Poziom2 extends Poziom {

    int aktualnyPoziom;

    public  Poziom2 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, int punkty, String plik, int aktualnyPoziom)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, plik, aktualnyPoziom);
        punktyLBL.setText("Punkty:" + punkty);
        this.aktualnyPoziom = aktualnyPoziom;
        this.punkty = punkty;
    }

    public void wygrana()
    {
        czasDoKoncaTimer.stop();
        czyWygrano = true;
        dynamit.setIcon(new ImageIcon("image//dynamit.png"));
        repaint();
        liczPunkty();
        czyWygrano = true;
        opoznij(500);
        removeAll();
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(aktualnyPoziom,punkty,punktyZaPoziom,poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();
    }
}