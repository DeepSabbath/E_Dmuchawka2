import javax.swing.*;

/**
 * Created by Amadeusz on 26.12.2015.
 */
public class Poziom1 extends Poziom {

    int aktualnyPoziom = 1;

    public  Poziom1 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, String plik, int aktualnyPoziom)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, plik, aktualnyPoziom);
    }



    public void przejdzDoNastepnegoPoziomu()
    {
        removeAll();
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(aktualnyPoziom, punkty, punktyZaPoziom, poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();
    }
}