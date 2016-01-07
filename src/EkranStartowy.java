import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * <b>EkranStartowy</b> - klasa definiująca ekran startowy gry
 * @author Amadeusz Kardasz
 */

public class EkranStartowy extends JPanel{

    /** umozliwia rozpoczecie gry */
    JLabel rozpocznijGre;
    /** umozliwia podejrzenie informacji o autorze */
    JLabel infoOAutorze;
    /** pozwala na wybor poziomu latwego */
    JLabel poziomLatwy;
    /** pozwala na wybor poziomu sredniego */
    JLabel poziomSredni;
    /** pozwala na wybor poziomu trudnego */
    JLabel poziomTrudny;
    /** wyswietla tekst */
    JLabel wybierzPoziomTrudnosci;
    /** wyswietla tlo aplikacji */
    JLabel tlo;
    /** pozwala na wyjscie z aplikacji */
    JLabel wyjscieZAplikacji;
    /** pozwala na wyswietlenie tabeli z rekordami */
    JLabel rekordy;
    /** przechowuje poziom trudnosci gry, inicjowana jako poziom latwy */
    int poziomTrudnosci = 1;
    /** liczy laczny czas odpalenia aplikacji */
    javax.swing.Timer czasGry = new javax.swing.Timer(1000, new LiczCzasGry()); // timer liczący łączny czas gry

    /**
     * Konstruktor umożliwia podanie właściwości obiektu oraz odpowiadający za ustawienie parametrów okna
     */

    public EkranStartowy(int szerokosc, int wysokosc)
    {
        setLayout(null);
        setSize(szerokosc, wysokosc);
        init();
        setVisible(true);
    } // koniec konstruktora

    /**
     * funkcja wyrysowująca początkową zawartość okna
     */

    private void init()
    {
        wybierzPoziomTrudnosci = new JLabel("Wybierz poziom trudności");
        wybierzPoziomTrudnosci.setSize(300,40);
        wybierzPoziomTrudnosci.setLocation(950,150);
        wybierzPoziomTrudnosci.setForeground(Color.black);
        wybierzPoziomTrudnosci.setFont(Main.ustawCzcionke(15));
        add(wybierzPoziomTrudnosci);

        poziomLatwy = new JLabel("łatwy");
        poziomLatwy.setSize(300,40);
        poziomLatwy.setLocation(950,200);
        poziomLatwy.setForeground(Color.green);
        poziomLatwy.setFont(Main.ustawCzcionke(33));
        poziomLatwy.addMouseListener(new PoziomLatwyKlik());
        add(poziomLatwy);

        poziomSredni = new JLabel("średni");
        poziomSredni.setSize(300,40);
        poziomSredni.setLocation(950,280);
        poziomSredni.setForeground(Color.red);
        poziomSredni.setFont(Main.ustawCzcionke(33));
        poziomSredni.addMouseListener(new PoziomSredniKlik());
        add(poziomSredni);

        poziomTrudny = new JLabel("trudny");
        poziomTrudny.setSize(300,40);
        poziomTrudny.setLocation(950,360);
        poziomTrudny.setForeground(Color.red);
        poziomTrudny.setFont(Main.ustawCzcionke(33));
        poziomTrudny.addMouseListener(new PoziomTrudnyKlik());
        add(poziomTrudny);

        rozpocznijGre = new JLabel("Graj");
        rozpocznijGre.setSize(300,60);
        rozpocznijGre.setLocation(950,90);
        rozpocznijGre.setFont(Main.ustawCzcionke(45));
        rozpocznijGre.setForeground(Color.yellow);
        rozpocznijGre.addMouseListener(new RozpocznijGreKlik());
        add(rozpocznijGre);

        infoOAutorze = new JLabel("Info o autorze");
        infoOAutorze.setSize(300,40);
        infoOAutorze.setLocation(950,440);
        infoOAutorze.setFont(Main.ustawCzcionke(33));
        infoOAutorze.setForeground(Color.yellow);
        infoOAutorze.addMouseListener(new oAutorzeKlik());
        add(infoOAutorze);

        rekordy = new JLabel("Rekordy");
        rekordy.setSize(300,40);
        rekordy.setLocation(950,510);
        rekordy.setFont(Main.ustawCzcionke(33));
        rekordy.setForeground(Color.yellow);
        rekordy.addMouseListener(new WyswietlRekordyKlik());
        add(rekordy);

        wyjscieZAplikacji = new JLabel("Wyjście");
        wyjscieZAplikacji.setSize(300, 40);
        wyjscieZAplikacji.setLocation(950,580);
        wyjscieZAplikacji.setFont(Main.ustawCzcionke(33));
        wyjscieZAplikacji.setForeground(Color.yellow);
        wyjscieZAplikacji.addMouseListener(new WyjscieKlik());
        add(wyjscieZAplikacji);

        ustawTlo("image//EkranStartowy.jpg");

        czasGry.start();
    } // koniec init

    /**
     * funkcja ustawiajca tło
     * @param plik - ścieżka do pliku z obrazkiem
     */

    public void ustawTlo(String plik)
    {
        try {
            tlo = new JLabel(new ImageIcon(plik));
            tlo.setOpaque(true);
            tlo.setBounds(0, 0, Main.SZEROKOSC, Main.WYSOKOSC);
            add(tlo);
        } catch (Exception e)
        {
            System.out.println("Blad" + e);
        }
    } // koniec ustaw tło

    /**
     * Klasa inkrementująca łączny czas dmuchania
     */

    private class LiczCzasGry implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            Main.lacznyCzasOdpaleniaAplikacji++;
            if(Poziom.czyRosnie)
            {
                Main.lacznyCzasDmuchania++;
            }
        }
    } // koniec LiczCzasGRy

    /**
     * Definiuje działania po wybraniu poziomu łatwego
     */

    class PoziomLatwyKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 1;
            poziomLatwy.setForeground(Color.green);
            poziomSredni.setForeground(Color.red);
            poziomTrudny.setForeground(Color.red);
        }
    } // koniec PoziomLatwyKlik

    /**
     * Definiuje działania po wybraniu poziomu średniego
     */

    class PoziomSredniKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 2;
            poziomSredni.setForeground(Color.green);
            poziomLatwy.setForeground(Color.red);
            poziomTrudny.setForeground(Color.red);
        }
    } // koniec PoziomSredniKlik

    /**
     * Definiuje działania po wybraniu poziomu trudnego
     */

    class PoziomTrudnyKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 3;
            poziomTrudny.setForeground(Color.green);
            poziomSredni.setForeground(Color.red);
            poziomLatwy.setForeground(Color.red);
        }
    } // koniec PoziomTrudnyKlik

    /**
     * Definiuje działania po wybraniu z menu opcji info o autorze
     */

    class oAutorzeKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            InfoOAutorze i = new InfoOAutorze();
        }
    } // koniec oAutorzeKlik

    /**
     * Definiuje działania po wybraniu rozpoczęcia nowej gry.
     * ustawia odpowiednie pararmetry nowej gry w zależności od wybranego poziomu trudności
     */

    class RozpocznijGreKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            int wymaganaMocDmuchniecia = 40;
            int potrzebnyCzasDmuchniecia = 40;
            int czasNaPoziom = 20;
            switch (poziomTrudnosci) {
                case 1:
                    potrzebnyCzasDmuchniecia = 10;
                    wymaganaMocDmuchniecia = 10;
                    czasNaPoziom = 25;
                    break;
                case 2:
                    potrzebnyCzasDmuchniecia = 30;
                    wymaganaMocDmuchniecia = 30;
                    czasNaPoziom = 20;
                    break;
                case 3:
                    potrzebnyCzasDmuchniecia = 40;
                    wymaganaMocDmuchniecia = 60;
                    czasNaPoziom = 16;
                    break;
            } // koniec switch
            removeAll();
            String tlo = "image//kopalnia1.jpg";
            Poziom1 p1 = new Poziom1(poziomTrudnosci, wymaganaMocDmuchniecia, potrzebnyCzasDmuchniecia, czasNaPoziom, tlo, 1);
            add(p1);
            repaint();
            p1.requestFocusInWindow();
        }
    } // koniec RozpocznijGreKlik

    /**
     * definiuje zachowanie po wybraniu z menu opcji wyjście z gry
     */

    class WyjscieKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {

            CzasDoZapisu cdz = new CzasDoZapisu(Main.lacznyCzasOdpaleniaAplikacji, Main.lacznyCzasDmuchania); // stworzenie obiektu do zapisu danych na temat czasu gry

            try {
                zapis("czas.txt", cdz);     // zapis danych na temat czasu do pliku
            } catch (IOException e1) {
                System.out.println("Błąd zapisu");
            }
            System.exit(0);
        }
    }

    /**
     * funkcja zapisująca pojedynczy obiekt do pliku
      * @param nazwaPliku - wskazuje plik do, którego mają być zapisane dane
     * @param cdz - przekazywany jest obiekt klasy CzasDoZapisu
     * @throws IOException
     */

    public static void zapis(String nazwaPliku, CzasDoZapisu cdz)throws IOException {
        ObjectOutputStream pl=null;
        try{
            pl=new ObjectOutputStream(new FileOutputStream(nazwaPliku));
            pl.writeObject(cdz);
            pl.flush();
        }
        finally{
            if(pl!=null)
                pl.close();
        }
    } // koniec zapis

    /**
     * Definiuje działanie po wybraniu opcji wyświetl rekordy
     */

    class WyswietlRekordyKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {

            removeAll();
            OknoZRekordami ozr = new OknoZRekordami(); // tworzony jest obiekt wyświetlający rekordy
            add(ozr);
            repaint();
            ozr.requestFocusInWindow();
        }
    }
}
