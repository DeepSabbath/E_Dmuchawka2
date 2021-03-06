import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;

/**
 * <b>Main</b> - klasa główna aplikajci.
 * Jest to prosta gra symulująca aplikację na platformę E-Dmuchawka
 * @author Amadeusz Kardasz
 */
public class Main {

    /** okresla wysokosc okna */
    static final int WYSOKOSC = 700;
    /** okresla szerokosc okna */
    static final int SZEROKOSC = 1280;
   /** przechowuje calkowity czas odpalenia aplikacji */
    public static int lacznyCzasOdpaleniaAplikacji;
    /** przechowuje calkowity czas dmuchania (klikania dynamitu) */
    public static int lacznyCzasDmuchania;

    /**
     * metoda uruchamiająca grę. Najpierw pobierane są dane na temat czasu gry.
     * Potem następuje pobranie parametrów ekranu i ustalenie jego środka,
     * na koniec tworzony jest obiekt klasy OknoGlowne, gdzie wykonuje się dalsza część programu
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
                /** przechowuje szerokosc ekranu */
                int szerokoscEkranu=Toolkit.getDefaultToolkit().getScreenSize().width;
                /** przechowuje wysokosc ekranu */
                int wysokoscEkranu=Toolkit.getDefaultToolkit().getScreenSize().height;

                //oblicz współrzędne narożnika tak, aby pole gry było wyśrodkowane
                /** przechowuje srodek szerokosci ekranu */
                int xSrodek=(szerokoscEkranu - SZEROKOSC)/2;
                /** przechowuje srodek wyskokosci ekranu */
                int ySrodek=(wysokoscEkranu - WYSOKOSC)/2;

                OknoGlowne o = new OknoGlowne(SZEROKOSC, WYSOKOSC, xSrodek, ySrodek);      // utworzenie okna głównego aplikacji
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
            //System.out.println("Koniec pliku");
        }
        finally
        {
            if (ois != null)
                ois.close();
        }
        lacznyCzasOdpaleniaAplikacji = cdz.lacznyCzasGry;
        lacznyCzasDmuchania = cdz.lacznyCzasDmuchania;
    } // koniec odczyt

    /**
     * metoda pozwalająca ustawić rozmiar czcionki
     */

    public static Font ustawCzcionke(int rozmiar)
    {
        /** ustawia czcionke */
        Font font = new Font("Helvetica", Font.BOLD, rozmiar);
        return font;
    }

    /**
     * metoda oddtwarzająca dźwięk
     * @param f - ścieżka do pliku
     */

    public static synchronized void grajDzwiek(final File f) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }// koniec grajDzwiek
}