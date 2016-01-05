import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Date;

/**
 * <b>OknoZRekordami</b> definiuje okno, w którym wyświetlane są rekorodwe wyniki
 * @Author Amadeusz Kardasz
 */
public class OknoZRekordami extends JPanel{

    DaneDoZapisu[] tablicaDanych = new DaneDoZapisu[1000];

    JLabel rekordLBL;
    JLabel rekordNaLatwymLBL;
    JLabel rekordNaSrednimLBL;
    JLabel rekordNaTrudnymLBL;
    JLabel wyswietlaLatwy [] = new JLabel[3];
    JLabel wyswietlaSredni []= new JLabel[3];
    JLabel wyswietlaTrudny []= new JLabel[3];
    JLabel wrocDoMenu;

    Date data = new Date();

    /**
     * konstruktor klasy ustawia odpowiednie wartości parametrów okna oraz wyrysowuje jego zawartość
     */

    OknoZRekordami()
    {
        setLayout(null);
        setSize(Main.SZEROKOSC,Main.WYSOKOSC);
        init();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    } // koniec konstrukotra

    /**
     * funkcja wyrysowująca początkową zawartość okna
     */

    public void init()
    {
        DaneDoZapisu dzp = new DaneDoZapisu("c",0,data,2);
        Arrays.fill(tablicaDanych, dzp);
        odczytCalego("dane.txt");

        Font font20 = new Font("Helvetica", Font.BOLD, 20);
        Font font30 = new Font("Helvetica", Font.BOLD, 30);
        Font font40 = new Font("Helvetica", Font.BOLD, 40);

        rekordLBL = new JLabel("REKORDY ");
        rekordLBL.setSize(300,50);
        rekordLBL.setLocation(550,50);
        rekordLBL.setFont(font40);
        add(rekordLBL);

        rekordNaLatwymLBL = new JLabel("Poziom łatwy ");
        rekordNaLatwymLBL.setSize(200,30);
        rekordNaLatwymLBL.setLocation(150,150);
        rekordNaLatwymLBL.setFont(font20);
        add(rekordNaLatwymLBL);

        for (int i = 0; i < 3; i++)
        {
            wyswietlaLatwy[i] = new JLabel();
            wyswietlaLatwy[i].setSize(200, 30);
            wyswietlaLatwy[i].setLocation(150, 200 + (i *100));
            wyswietlaLatwy[i].setText("tekst");
            add(wyswietlaLatwy[i]);
        } // koniec for

        rekordNaSrednimLBL = new JLabel("Poziom średni ");
        rekordNaSrednimLBL.setSize(200, 30);
        rekordNaSrednimLBL.setLocation(500, 150);
        rekordNaSrednimLBL.setFont(font20);
        add(rekordNaSrednimLBL);

        for (int i = 0; i < 3; i++)
        {
            wyswietlaSredni[i] = new JLabel();
            wyswietlaSredni[i].setSize(200, 30);
            wyswietlaSredni[i].setLocation(500, 200 + (i *100));
            add(wyswietlaSredni[i]);
        } // koniec for

        rekordNaTrudnymLBL = new JLabel("Poziom trudny ");
        rekordNaTrudnymLBL.setSize(200, 30);
        rekordNaTrudnymLBL.setLocation(900, 150);
        rekordNaTrudnymLBL.setFont(font20);
        add(rekordNaTrudnymLBL);

        for (int i = 0; i < 3; i++)
        {
            wyswietlaTrudny[i] = new JLabel();
            wyswietlaTrudny[i].setSize(200, 30);
            wyswietlaTrudny[i].setLocation(900, 200 + (i *100));
            add(wyswietlaTrudny[i]);
        } // koniec for

        wrocDoMenu = new JLabel("Wróć do menu");
        wrocDoMenu.setSize(200,30);
        wrocDoMenu.setLocation(950,600);
        wrocDoMenu.setForeground(Color.red);
        wrocDoMenu.setFont(font20);
        wrocDoMenu.addMouseListener(new WrocDoMenuKlik());
        add(wrocDoMenu);

        posortuj();

    } // koniec init

    /**
     * metoda pobierająca z pliku dane na temat wynikó gier
     * @param nazwaPliku - ścieżkia do pliku
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void odczytCalego2(String nazwaPliku)throws IOException,ClassNotFoundException{
        ObjectInputStream ois=null;

        try
        {
            ois = new ObjectInputStream(new FileInputStream(nazwaPliku));
            int l=0;
            while(true) // odczytanie danych z pliku i zapisanie ich do tablicy
            {
                tablicaDanych[l]=(DaneDoZapisu)ois.readObject();
                //System.out.println("Dane do zapisu " + l + " " + tablicaDanych[l].nazwaUzytkownika + tablicaDanych[l].punkty + "poziom tr " + tablicaDanych[l].poziomTrudnosci);
                l++;
            } // koniec while

        } catch (EOFException ex)
        {
            System.out.println("Koniec pliku");
        }
        finally
        {
            if(ois!=null)
                ois.close();
        }
    } // koniec odczyCalego2

    /**
     * metoda uruchamiająca metodę odczytCalego2 po obsłużeniu wyjątku
     * @param nazwaPliku
     */

    public void odczytCalego(String nazwaPliku)
    {
        try
        {
            odczytCalego2(nazwaPliku);
        }
        catch(Exception e)
        {
            System.out.println("Błąd odczytu");
        }
    } // koniec odczytCalego

    /**
     * metoda wywołująca sortowanie oraz wyświetlająca wyniki na ekranie
     */

    public void posortuj()
    {

        DaneDoZapisu[] latwy = new DaneDoZapisu[3];
        DaneDoZapisu[] sredni = new DaneDoZapisu[3];
        DaneDoZapisu[] trudny = new DaneDoZapisu[3];

        latwy[0] = new DaneDoZapisu("nick", 0, data, 1);
        latwy[1] =  new DaneDoZapisu("nick", 0, data, 1);
        latwy[2] =  new DaneDoZapisu("nick", 0, data, 1);
        sredni[0] = new DaneDoZapisu("nick", 0, data, 1);
        sredni[1] =  new DaneDoZapisu("nick", 0, data, 2);
        sredni[2] =  new DaneDoZapisu("nick", 0, data, 2);
        trudny[0] = new DaneDoZapisu("nick", 0, data, 1);
        trudny[1] =  new DaneDoZapisu("nick", 0, data, 3);
        trudny[2] =  new DaneDoZapisu("nick", 0, data, 3);


        for (int i = 0; i < tablicaDanych.length; i++)
        {
            switch (tablicaDanych[i].poziomTrudnosci)
            {
                case 1:
                    sortuj3(i, latwy);
                    break;

                case 2:
                    sortuj3(i,sredni);
                    break;

                case 3:
                    sortuj3(i,trudny);
                    break;
            }// koniec switch
            for (int j = 0; j < 3; j++) {
                wyswietlaLatwy[j].setText(j + 1 + ". " + latwy[j].nazwaUzytkownika + " " + latwy[j].punkty + "p");
                wyswietlaSredni[j].setText(j + 1 + ". " + sredni[j].nazwaUzytkownika + " " + sredni[j].punkty + "p");
                wyswietlaTrudny[j].setText(j + 1 + ". " + trudny[j].nazwaUzytkownika + " " + trudny[j].punkty + "p");
            } // koniec for
        } // koniec for
    } // konoec posortuj

    /**
     * metoda sortująca trzy najlepsze wyniki
     * @param i - indeks w tabeli danego obiektu klasy DaneDoZapisu
     * @param d - przyporządkowuje wyniki pod odpowiedni poziom trudności
     */

    public void sortuj3(int i, DaneDoZapisu [] d)
    {
        if (d[0].punkty < tablicaDanych[i].punkty)
        {
            d[2].punkty = d[1].punkty;
            d[2].nazwaUzytkownika = d[1].nazwaUzytkownika;
            d[1].punkty = d[0].punkty;
            d[1].nazwaUzytkownika = d[0].nazwaUzytkownika;
            d[0].punkty = tablicaDanych[i].punkty;
            d[0].nazwaUzytkownika = tablicaDanych[i].nazwaUzytkownika;
        }
        else if (d[1].punkty < tablicaDanych[i].punkty) {
            d[2].punkty = d[1].punkty;
            d[2].nazwaUzytkownika = d[1].nazwaUzytkownika;
            d[1].punkty = tablicaDanych[i].punkty;
            d[1].nazwaUzytkownika = tablicaDanych[i].nazwaUzytkownika;
        }
            else if (d[2].punkty < tablicaDanych[i].punkty)
        {
            d[2].punkty = tablicaDanych[i].punkty;
            d[2].nazwaUzytkownika = tablicaDanych[i].nazwaUzytkownika;
        }
    } // koniec posortuj3

    /**
     * definiuje działanie po wybraniu opcji wróć do menu - następuje powrót do menu startowego
     */

    class WrocDoMenuKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            removeAll();
            EkranStartowy es = new EkranStartowy(Main.SZEROKOSC, Main.WYSOKOSC);
            es.setFocusable(true);
            add(es);
            repaint();
        }
    } // koniec WrocDoMenuKlik

}
