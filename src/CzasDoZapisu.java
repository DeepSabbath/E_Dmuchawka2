import java.io.Serializable;

/**
 * Created by Amadeusz on 30.12.2015.
 */
public class CzasDoZapisu implements Serializable{

    int lacznyCzasGry;
    int lacznyCzasDmuchania;

    CzasDoZapisu (int lacznyCzasGry, int lacznyCzasDmuchania)
    {
        this.lacznyCzasGry = lacznyCzasGry;
        this.lacznyCzasDmuchania = lacznyCzasDmuchania;
    }

}
