/**
 * Created by Amadeusz on 27.12.2015.
 */
public class Poziom2 extends Poziom {

    int aktualnyPoziom = 2;

    public  Poziom2 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, int punkty)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom);
        punktyLBL.setText("Punkty:" + punkty);
        this.punkty = punkty;
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