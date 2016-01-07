/**
 * <b>Poziom2</b> - klasa dziedzicząca po klasie <b>Poziom</b>, odpowiedzialna za obsługę poziomów od drugiego do przedostatniego
 * @author Amadeusz Kardasz
 */
public class Poziom2 extends Poziom {

    /** przechowuje aktualny poziom */
    int aktualnyPoziom;

    /**
     * konstruktor pozwalający na przypisanie parametrów oraz wywołujący konstruktor klasy nadrzędnej
     */

    public  Poziom2 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, int punkty, String plik, int aktualnyPoziom)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, plik, aktualnyPoziom);
        punktyLBL.setText("Punkty:" + punkty);
        this.aktualnyPoziom = aktualnyPoziom;
        this.punkty = punkty;
    } // koniec konstruktora

    /**
     * metoda obsługująca zdarzenia po przejściu poziomu
     */

    public void przejdzDoNastepnegoPoziomu()
    {
        removeAll();
        PrzejscieMiedzyPoziomami pmp = new PrzejscieMiedzyPoziomami(aktualnyPoziom,punkty,punktyZaPoziom,poziomTrudnosci);
        add(pmp);
        repaint();
        pmp.requestFocusInWindow();
    } // koniec przejdzDONastepnegoPoziomu
} // koniecPoziom2