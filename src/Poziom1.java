import javax.swing.*;

/**
 * <b>Poziom1</b> klasa dziedzicząca po klasie <b>Poziom</b>, odpowiadająca za obsługę poziomu pierwszego
 * @author Amadeusz Kardasz
 */
public class Poziom1 extends Poziom {

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
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(1, punkty, punktyZaPoziom, poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();
    } // koniec przejdzDoNasteonegoPoziomu
} // koniec Poziom1