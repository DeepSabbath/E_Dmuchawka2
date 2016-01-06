import java.io.Serializable;
import java.util.Date;

/**
 * <b>DaneDoZapisu</b> - klasa służąca do zapisu danych na temat danej gry
 * @author Amadeusz Kardasz
 */
public class DaneDoZapisu implements Serializable {

    String nazwaUzytkownika;
    int punkty;
    Date data;
    int poziomTrudnosci;

    /** Konstruktor umożliwia podanie właściwości obiektu*/

    DaneDoZapisu(String nazwaUzytkownika, int punkty, Date data, int poziomTrudnosci) {

        this.poziomTrudnosci = poziomTrudnosci;
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.punkty = punkty;
        this.data = data;
    } // koniec konstruktora
}
