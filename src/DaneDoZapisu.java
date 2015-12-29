import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Amadeusz on 28.12.2015.
 */
public class DaneDoZapisu implements Serializable {

    String nazwaUzytkownika;
    int punkty;
    Date data;
    int poziomTrudnosci;

    DaneDoZapisu(String nazwaUzytkownika, int punkty, Date data, int poziomTrudnosci) {

        this.poziomTrudnosci = poziomTrudnosci;
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.punkty = punkty;
        this.data = data;
    }
}
