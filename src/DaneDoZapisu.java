import java.io.Serializable;
import java.util.Date;

/**
 * <b>DaneDoZapisu</b> - klasa służąca do zapisu danych na temat danej rozgrywki, obiekt tworzony po zakonczeniu gry
 * @author Amadeusz Kardasz
 */
public class DaneDoZapisu implements Serializable {

    /** przechowuje podana nazwe uzytkownika */
    String nazwaUzytkownika;
    /** przechowuje liczbe zdobytych punktow */
    int punkty;
    /** przechowuje date */
    Date data;
    /** przechowuje poziom trudnosci gry */
    int poziomTrudnosci;

    /** Konstruktor umożliwia podanie właściwości obiektu*/

    DaneDoZapisu(String nazwaUzytkownika, int punkty, Date data, int poziomTrudnosci) {

        this.poziomTrudnosci = poziomTrudnosci;
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.punkty = punkty;
        this.data = data;
    } // koniec konstruktora
}
