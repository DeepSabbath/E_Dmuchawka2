import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <b>PoziomOstatni</b> - klasa dziedziacząca po klasie <b>Poziom</b>, odpowiadająca za obsługę ostatniego poziomu
 * @author Amadeusz Kardasz
 */
public class PoziomOstatni extends Poziom {

    int aktualnyPoziom;

    /**
     * konstruktor pozwalający na przypisanie parametrów oraz wywołujący konstruktor klasy nadrzędnej
     */

    public  PoziomOstatni (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, int punkty, String plik, int aktualnyPoziom)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, plik, aktualnyPoziom);
        this.punkty = punkty;
        this.aktualnyPoziom = aktualnyPoziom;
        punktyLBL.setText("Punkty:" + punkty);
    } // koniec konstruktora

    /**
     * metoda obsługująca zdarzenia po przejściu poziomu
     */

    public void przejdzDoNastepnegoPoziomu()
    {
        zakonczenieGry();
    } // koniec przejdzDoNastepnegoPoziomu

}
