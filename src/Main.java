import javax.swing.*;
import java.awt.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * <b>Main</b> - klasa główna aplikajci.
 * Jest to prosta gra symulująca aplikację na platformę E-Dmuchawka
 * @Author Amadeusz Kardasz
 */
public class Main {

    static final int WYSOKOSC = 700;
    static final int SZEROKOSC = 1280;
    public static OknoGlowne o;
    public static int lacznyCzasOdpaleniaAplikacji;
    public static int lacznyCzasDmuchania;

    /**
     * metoda uruchamiająca grę. Najpierw pobierane są dane na temat czasu gry.
     * Potem następuje pobranie parametrów ekranu i ustalenie jego środka,
     * na koniec tworzony jest obiekt klasy OknoGlowne, gdzie wykonuje się dalsza część programu
     * @Author Amadeusz Kardasz
     */

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
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

                //pobierz rozmiar ekranu
                int szerokoscEkranu=Toolkit.getDefaultToolkit().getScreenSize().width;
                int wysokoscEkranu=Toolkit.getDefaultToolkit().getScreenSize().height;

                //oblicz współrzędne narożnika tak, aby pole gry było wyśrodkowane
                int xSrodek=(szerokoscEkranu - SZEROKOSC)/2;
                int ySrodek=(wysokoscEkranu - WYSOKOSC)/2;

                o = new OknoGlowne(SZEROKOSC, WYSOKOSC, xSrodek, ySrodek);      // utworzenie okna głównego aplikacji
                o.setDefaultCloseOperation(o.EXIT_ON_CLOSE);
            } // koniec run
        }); // koniec Runnable
    } // koniec main

    /**
     * metoda odczytująca z pliku dotychczasowe czasy gry oraz dmuchania
     * @param nazwaPliku - ścieżka do pliku z danymi
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static void odczyt(String nazwaPliku) throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = null;
        CzasDoZapisu cdz = null;
        try
        {
            ois = new ObjectInputStream(new FileInputStream(nazwaPliku));
            cdz = (CzasDoZapisu) ois.readObject();

        }
        catch (EOFException ex)
        {
            System.out.println("Koniec pliku");
        }
        finally
        {
            if (ois != null)
                ois.close();
        }
        lacznyCzasOdpaleniaAplikacji = cdz.lacznyCzasGry;
        lacznyCzasDmuchania = cdz.lacznyCzasDmuchania;
    } // koniec odczyt
}