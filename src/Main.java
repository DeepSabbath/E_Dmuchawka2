import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Amadeusz on 26.12.2015.
 */
public class Main {

    static final int WYSOKOSC = 700;
    static final int SZEROKOSC = 1280;
    public static OknoGlowne o;
    public static int lacznyCzasOdpaleniaAplikacji;
    public static int lacznyCzasDmuchania;

    public static void main(String[] args) {

        try
        {
            odczyt("czas.txt");
        }
        catch (IOException e)
        {
            System.out.println("Błąd odczytu");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Błąd odczytu");
        }

        o = new OknoGlowne(SZEROKOSC, WYSOKOSC);
        o.setDefaultCloseOperation(o.EXIT_ON_CLOSE);
    }

    public static void odczyt(String nazwaPl) throws IOException, ClassNotFoundException {
        ObjectInputStream pl2 = null;
        CzasDoZapisu cdz = null;
        try {
            pl2 = new ObjectInputStream(new FileInputStream(nazwaPl));
            cdz = (CzasDoZapisu) pl2.readObject();

        } catch (EOFException ex) {
            System.out.println("Koniec pliku");
        } finally {
            if (pl2 != null)
                pl2.close();
        }
        lacznyCzasOdpaleniaAplikacji = cdz.lacznyCzasGry;
        lacznyCzasDmuchania = cdz.lacznyCzasDmuchania;
    }
}