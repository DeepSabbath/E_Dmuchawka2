import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Date;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * <b>KoniecGry</b> - klasa definiująca dzaiłania podczas zakończenia gry
 * @Author Amadeusz Kardasz
 */
public class KoniecGry extends JPanel {

    int punkty;
    int poziomTrudnosci;
    Date data;

    JLabel punktyLBL;
    JLabel koniecGryLBL;
    JLabel restartLBL;
    JLabel zakonczGreLBL;
    JLabel nazwaUzytkownikaLBL;
    JTextField nazwaUzytkownikaTF;

    /**
     * konstrukor odpowiedzialny za ustawienie odpowiednich parametrów oraz inicjalizację zawartości okna
     * @param punkty - określa liczbę zdobytych punktów
     * @param poziomTrudnosci - określa poziom trudności na jakim grano
     */

    KoniecGry(int punkty, int poziomTrudnosci)
    {
        this.punkty = punkty;
        this.poziomTrudnosci = poziomTrudnosci;
        setLayout(null);
        setSize(Main.SZEROKOSC, Main.WYSOKOSC);
        init();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    }

    /**
     * funkcja wyrysowująca początkową zawartość okna oraz pobierające dane z pliku
     */

    public void init()
    {
        /*try {
            odczytCalego("dane.txt");
        }
        catch (Exception e)
        {
            System.out.println("Błąd odczytu");
        }*/

        Font font = new Font("Helvetica", Font.BOLD, 30);
        Font font2 = new Font("Helvetica", Font.BOLD, 40);
        data = new Date();

        nazwaUzytkownikaTF = new JTextField(10);
        nazwaUzytkownikaTF.setSize(200,25);
        nazwaUzytkownikaTF.setLocation(570,200);
        add(nazwaUzytkownikaTF);

        nazwaUzytkownikaLBL = new JLabel("Nick: ");
        nazwaUzytkownikaLBL.setSize(60,25);
        nazwaUzytkownikaLBL.setLocation(530,200);
        add(nazwaUzytkownikaLBL);

        koniecGryLBL = new JLabel("Koniec gry");
        koniecGryLBL.setSize(250,60);
        koniecGryLBL.setLocation(550,100);
        koniecGryLBL.setFont(font2);
        add(koniecGryLBL);

        zakonczGreLBL = new JLabel("Zakończ grę");
        zakonczGreLBL.setSize(200,50);
        zakonczGreLBL.setLocation(950,600);
        zakonczGreLBL.setForeground(Color.red);
        zakonczGreLBL.setFont(font);
        zakonczGreLBL.addMouseListener(new ZakonczGreKlik());
        add(zakonczGreLBL);

        punktyLBL = new JLabel();
        punktyLBL.setSize(400,50);
        punktyLBL.setLocation(550,250);
        punktyLBL.setForeground(Color.black);
        punktyLBL.setFont(font);
        punktyLBL.setText("Punkty łącznie: " + punkty);
        add(punktyLBL);

        restartLBL = new JLabel("Zacznij od nowa");
        restartLBL.setSize(400,50);
        restartLBL.setLocation(150,600);
        restartLBL.setForeground(Color.red);
        restartLBL.setFont(font);
        restartLBL.addMouseListener(new RestartKlik());
        add(restartLBL);
    } // koniec init

    /**
     * klasa definiuje działanie po wybraniu opcji zakończ grę - dane zostają zapisnae do pliku, następuje powrót do ekranu startowego
     */

    class ZakonczGreKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {

            if (sprawdzNick()) // sprawdzenie dlugosci nicku
            {
                zapiszDoPliku();
                removeAll();
                EkranStartowy es = new EkranStartowy(Main.SZEROKOSC, Main.WYSOKOSC);
                es.setFocusable(true);
                add(es);
                repaint();
            }
        }
    } // koniec ZakonczGreKlik

    /**
     * klasa definująca działanie po wybraniu opcji zacznij od nowa - dane zostają zapisnae do pliku, zostaje włączony poziom 1
     */

    class RestartKlik extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (sprawdzNick())      // spradzenie długości nicku
            {
                int wymaganaMocDmuchniecia = 40;
                int potrzebnyCzasDmuchniecia = 40;
                int czasNaPoziom = 20;
                String tlo = "image//kopalnia1.jpg";
                zapiszDoPliku();

                switch (poziomTrudnosci) {      // ustawienie parmetrów nowej gry w zależności od aktulanego poziomu trudności
                    case 1:
                        wymaganaMocDmuchniecia = 10;
                        potrzebnyCzasDmuchniecia = 10;
                        czasNaPoziom = 25;
                        break;
                    case 2:
                        wymaganaMocDmuchniecia = 30;
                        potrzebnyCzasDmuchniecia = 30;
                        czasNaPoziom = 20;
                        break;
                    case 3:
                        wymaganaMocDmuchniecia = 60;
                        potrzebnyCzasDmuchniecia = 40;
                        czasNaPoziom = 16;
                        break;
                } // koniec switch

                removeAll();
                Poziom1 p1 = new Poziom1(poziomTrudnosci, wymaganaMocDmuchniecia, potrzebnyCzasDmuchniecia, czasNaPoziom, tlo, 1);
                add(p1);
                repaint();
                p1.requestFocusInWindow();
            } // koniec if
        }
    } // koniec RestartKlik

    /**
     * funkcja uruchamiająca funkcję do zapisu do pliku po obsłudze wyjątku
     */

    public void zapiszDoPliku()
    {
            try
            {
                 zapiszDoPliku2();
            }
            catch (Exception e)
            {
                System.out.println("Błąd IO");
            }
    }

    /**
     * funkcja realizująca zapis danych na temat danej gry do pliku
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void zapiszDoPliku2() throws IOException, ClassNotFoundException{

        String nazwaUzytkownika = nazwaUzytkownikaTF.getText();

        DaneDoZapisu ddz = new DaneDoZapisu(nazwaUzytkownika, punkty, data, poziomTrudnosci);       // stworzenie obiektu klasy DanaDoZapisu

        DaneDoZapisu[] tab = new DaneDoZapisu[1000]; // stworzenie tablicy obiektów klasy DaneDoZapisu
        ObjectInputStream ois = null;
        try{
            ois=new ObjectInputStream(new FileInputStream("dane.txt"));
            int l=0;
                while(true)
                    tab[l++]=(DaneDoZapisu)ois.readObject();            // odczytanie obiektów z pliku do tablicy

        }// koniec try
        catch (EOFException ex)
        {
            // Program przeskakuje w to miejsce, kiedy dojdzie do końca pliku,
            // czyli kiedy wszystko już odczyta.
            // Teraz zamykamy plik, otwieramy w trybie zapisu i wpisujemy
            // do niego wszystko oraz dopisujemy to, co ma być dodane
            if(ois!=null)
                ois.close();            // zamknięcie strumienia odczytu danych

            ObjectOutputStream oos=null;

            try
            {
                oos=new ObjectOutputStream(new FileOutputStream("dane.txt"));        // Wpisujemy do pliku to, co w nim już było
                for(int i=0; tab[i]!=null; i++)
                    oos.writeObject(tab[i]);                // dopisujemy nowy obiekt do pliku
                oos.writeObject(ddz);
                oos.flush();
            } // koniec try
            catch (Exception e)
            {
                System.out.println("Błąd IO");
            } // koniec catch
            finally
            {
                if(oos!=null)
                    oos.close();        // zamknięcie strumienia zapisu danych
            } // koniec finally
        } // koniec catch (EOFException ex)
        catch (Exception e)
        {
            System.out.println("Błąd IO");
        }// koniec catch
    } // koniec ZapiszDoPliku2

    /**
     * funkcja sprawdzająca długość nicku
     * @return zwraca true jeśli nick ma co najmniej 2 znaki
     */

    public boolean sprawdzNick()
    {
        if (nazwaUzytkownikaTF.getText().length() > 2)  // zwraca true jeśli długość nicku wynosi powyżej 2 znaków
        {
            return true;
        } // koniec if
        else // w przeciwnym wypadku zwraca false i wyświetla odpowiedni komunikat
        {
            showMessageDialog(null, "Nick powinien mieć co najmniej 3 znaki.");
            return false;
        } // koniec else
    }// koniec sprawdzNick
}
