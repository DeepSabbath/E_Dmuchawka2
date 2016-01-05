import javax.swing.*;

/**
 * <b>Poziom1</b> klasa dziedzicząca po klasie poziom, odpowiadająca za obsługę poziomu pierwszego
 * @Author Amadeusz Kardasz
 */
public class Poziom1 extends Poziom {

    int aktualnyPoziom = 1;

    /**
     * konstruktor pozwalający na przypisanie parametrów oraz wywołujący konstruktor klasy nadrzędnej
     */

    public  Poziom1 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, String plik, int aktualnyPoziom)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, plik, aktualnyPoziom);
    } // koniec konstruktora

    /**
     * metoda obsługująca zdarzenia po przejściu poziomu
     */

    public void przejdzDoNastepnegoPoziomu()
    {
        removeAll();
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(aktualnyPoziom, punkty, punktyZaPoziom, poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();
    } // koniec przejdzDoNasteonegoPoziomu
} // koniec Poziom1