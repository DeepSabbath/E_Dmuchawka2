/**
 * Created by Amadeusz on 28.12.2015.
 */
public class Poziom3 extends Poziom {

    int aktualnyPoziom = 3;

    public  Poziom3 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, int punkty)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom);
        this.punkty = punkty;
    }

    public void wygrana()
    {
        czasDoKoncaTimer.stop();
        liczPunkty();
        zakonczenieGry();

        /*removeAll();
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(aktualnyPoziom,punkty,punktyZaPoziom,poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();*/
    }
}
