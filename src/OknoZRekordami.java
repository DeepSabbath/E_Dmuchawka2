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
 * Created by Amadeusz on 28.12.2015.
 */
public class OknoZRekordami extends JPanel{

    DaneDoZapisu[] tablicaDanych = new DaneDoZapisu[100];

    JLabel rekordLBL;
    JLabel rekordNaLatwymLBL;
    JLabel rekordNaSrednimLBL;
    JLabel rekordNaTrudnymLBL;
    JLabel wyswietlaLatwy;
    JLabel wyswietlaSredni;
    JLabel wyswietlaTrudny;
    JLabel wrocDoMenu;

    int najwyzszyWynikLatwy = 0;
    int najwyzszyWynikSredni = 0;
    int najwyzszyWynikTrudny = 0;
    String nazwaRekordzistyLatwy;
    String nazwaRekordzistySredni;
    String nazwaRekordzistyTrudny;


    OknoZRekordami()
    {
        setLayout(null);
        setSize(Main.SZEROKOSC,Main.WYSOKOSC);
        init();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    }

    public void init()
    {
        odczytCalego("dane.txt");

        Font font = new Font("Helvetica", Font.BOLD, 20);
        Font font2 = new Font("Helvetica", Font.BOLD, 40);

        rekordLBL = new JLabel("REKORDY ");
        rekordLBL.setSize(300,50);
        rekordLBL.setLocation(550,50);
        rekordLBL.setFont(font2);
        add(rekordLBL);

        rekordNaLatwymLBL = new JLabel("Poziom łatwy ");
        rekordNaLatwymLBL.setSize(200,30);
        rekordNaLatwymLBL.setLocation(550,150);
        add(rekordNaLatwymLBL);

        wyswietlaLatwy = new JLabel();
        wyswietlaLatwy.setSize(200,30);
        wyswietlaLatwy.setLocation(550,200);
        add(wyswietlaLatwy);

        rekordNaSrednimLBL = new JLabel("Poziom średni ");
        rekordNaSrednimLBL.setSize(200, 30);
        rekordNaSrednimLBL.setLocation(550, 350);
        add(rekordNaSrednimLBL);

        wyswietlaSredni = new JLabel();
        wyswietlaSredni.setSize(200,30);
        wyswietlaSredni.setLocation(550,400);
        add(wyswietlaSredni);

        rekordNaTrudnymLBL = new JLabel("Poziom trudny ");
        rekordNaTrudnymLBL.setSize(200, 30);
        rekordNaTrudnymLBL.setLocation(550, 550);
        add(rekordNaSrednimLBL);

        wyswietlaTrudny = new JLabel();
        wyswietlaTrudny.setSize(200,30);
        wyswietlaTrudny.setLocation(550,600);
        add(wyswietlaTrudny);

        wrocDoMenu = new JLabel("Zakoncz gre");
        wrocDoMenu.setSize(200,30);
        wrocDoMenu.setLocation(950,600);
        wrocDoMenu.setForeground(Color.red);
        wrocDoMenu.setFont(font);
        wrocDoMenu.addMouseListener(new WrocDoMenuClick());
        add(wrocDoMenu);

        //System.out.println("Dlugosc tablic " + tablicaDanych.length + "poziom  " + tablicaDanych[0].poziomTrudnosci +
         //       "nazwa " + tablicaDanych[0].nazwaUzytkownika);

        //posortuj();

    }
    public void odczytCalego2(String nazwaPl)throws IOException,ClassNotFoundException{
        ObjectInputStream ois=null;

        try{
            ois = new ObjectInputStream(new FileInputStream(nazwaPl));
            int l=0;
            while(true){
                tablicaDanych[l]=(DaneDoZapisu)ois.readObject();
                System.out.println("Dane do zapisu " + l + " " + tablicaDanych[l].nazwaUzytkownika + tablicaDanych[l].punkty + "poziom tr " + tablicaDanych[l].poziomTrudnosci);
                l++;
            }

        } catch (EOFException ex) {
            System.out.println("Koniec pliku");
        }
        finally{
            if(ois!=null)
                ois.close();
        }
    }

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
    }


    public void posortuj()
    {

        for (int i = 0; i < 3; i++) {
            switch (tablicaDanych[i].poziomTrudnosci)
            {
                case 1:
                    if (najwyzszyWynikLatwy < tablicaDanych[i].punkty)
                    {
                        najwyzszyWynikLatwy = tablicaDanych[i].punkty;
                        nazwaRekordzistyLatwy = tablicaDanych[i].nazwaUzytkownika;
                    }
                    break;

                case 2:
                    if (najwyzszyWynikSredni < tablicaDanych[i].punkty)
                    {
                        najwyzszyWynikSredni = tablicaDanych[i].punkty;
                        nazwaRekordzistySredni= tablicaDanych[i].nazwaUzytkownika;
                    }

                    break;

                case 3:
                    if (najwyzszyWynikTrudny < tablicaDanych[i].punkty)
                    {
                        najwyzszyWynikTrudny = tablicaDanych[i].punkty;
                        nazwaRekordzistyTrudny = tablicaDanych[i].nazwaUzytkownika;
                    }
                    break;
            }
            wyswietlaLatwy.setText("1." + nazwaRekordzistyLatwy + " " + najwyzszyWynikLatwy + "p");
            wyswietlaSredni.setText("1." + nazwaRekordzistySredni + " " + najwyzszyWynikSredni + "p");
            wyswietlaTrudny.setText("1." + nazwaRekordzistyTrudny + " " + najwyzszyWynikTrudny + "p");
        }
    }

    class WrocDoMenuClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            removeAll();
            EkranStartowy es = new EkranStartowy(Main.SZEROKOSC, Main.WYSOKOSC);
            es.setFocusable(true);
            add(es);
            repaint();
        }
    }

}
