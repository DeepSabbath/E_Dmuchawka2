import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by Amadeusz on 26.12.2015.
 */
public class Poziom1 extends Poziom {

    int aktualnyPoziom = 1;

    public  Poziom1 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, String plik)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, plik);
    }

    public void wygrana()
    {
        dynamit.setIcon(new ImageIcon("image//dynamit.png"));
        czasDoKoncaTimer.stop();
        czyWygrano = true;
        repaint();
        liczPunkty();
        opoznij(1000);
        removeAll();
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(aktualnyPoziom,punkty,punktyZaPoziom,poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();
    }
}