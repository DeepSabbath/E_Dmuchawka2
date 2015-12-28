import java.awt.event.KeyEvent;

/**
 * Created by Amadeusz on 26.12.2015.
 */
public class Poziom1 extends Poziom {

    int aktualnyPoziom = 1;

    public  Poziom1 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom);
    }

    public void wygrana()
    {
        czasDoKoncaTimer.stop();
        liczPunkty();

        removeAll();
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(aktualnyPoziom,punkty,punktyZaPoziom,poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();
    }
}
