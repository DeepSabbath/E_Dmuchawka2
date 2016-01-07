import java.io.Serializable;

/**
 * <b>CzasDoZapisu</b> - klasa służąca do przechowywania danych na temat czasu gry
 * @author Amadeusz Kardasz
 */
public class CzasDoZapisu implements Serializable{

    /** przechowuje calkowity czas odpalenia aplikacji */
    int lacznyCzasGry;
    /** przechowuje calkowity czas dmuchania (klikania dynamitu) */
    int lacznyCzasDmuchania;

    /** Konstruktor umożliwia podanie właściwości obiektu*/
    CzasDoZapisu (int lacznyCzasGry, int lacznyCzasDmuchania)
    {
        this.lacznyCzasGry = lacznyCzasGry;
        this.lacznyCzasDmuchania = lacznyCzasDmuchania;
    } // koniec konstruktora

}
