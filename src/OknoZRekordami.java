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
    JLabel wyswietlaLatwy [] = new JLabel[3];
    JLabel wyswietlaSredni []= new JLabel[3];
    JLabel wyswietlaTrudny []= new JLabel[3];
    JLabel wrocDoMenu;

    int najwyzszyWynikSredni = 0;
    int najwyzszyWynikTrudny = 0;
    String nazwaRekordzistySredni;
    String nazwaRekordzistyTrudny;
    Date data = new Date();

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
        DaneDoZapisu dzp = new DaneDoZapisu("c",0,data,2);
        Arrays.fill(tablicaDanych, dzp);
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
        rekordNaLatwymLBL.setLocation(150,150);
        add(rekordNaLatwymLBL);

        for (int i = 0; i < 3; i++)
        {
            wyswietlaLatwy[i] = new JLabel();
            wyswietlaLatwy[i].setSize(200, 30);
            wyswietlaLatwy[i].setLocation(150, 200 + (i *100));
            wyswietlaLatwy[i].setText("tekst");
            add(wyswietlaLatwy[i]);
        }

        rekordNaSrednimLBL = new JLabel("Poziom średni ");
        rekordNaSrednimLBL.setSize(200, 30);
        rekordNaSrednimLBL.setLocation(500, 150);
        add(rekordNaSrednimLBL);

        for (int i = 0; i < 3; i++)
        {
            wyswietlaSredni[i] = new JLabel();
            wyswietlaSredni[i].setSize(200, 30);
            wyswietlaSredni[i].setLocation(500, 200 + (i *100));
            add(wyswietlaSredni[i]);
        }

        rekordNaTrudnymLBL = new JLabel("Poziom trudny ");
        rekordNaTrudnymLBL.setSize(200, 30);
        rekordNaTrudnymLBL.setLocation(900, 150);
        add(rekordNaTrudnymLBL);

        for (int i = 0; i < 3; i++)
        {
            wyswietlaTrudny[i] = new JLabel();
            wyswietlaTrudny[i].setSize(200, 30);
            wyswietlaTrudny[i].setLocation(900, 200 + (i *100));
            add(wyswietlaTrudny[i]);
        }

        wrocDoMenu = new JLabel("Wróć do menu");
        wrocDoMenu.setSize(200,30);
        wrocDoMenu.setLocation(950,600);
        wrocDoMenu.setForeground(Color.red);
        wrocDoMenu.setFont(font);
        wrocDoMenu.addMouseListener(new WrocDoMenuClick());
        add(wrocDoMenu);

        posortuj();

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


        for (int i = 0; i < tablicaDanych.length; i++) {
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
            }
            for (int j = 0; j < 3; j++) {
                wyswietlaLatwy[j].setText(j + 1 + ". " + latwy[j].nazwaUzytkownika + " " + latwy[j].punkty + "p");
                wyswietlaSredni[j].setText(j + 1 + ". " + sredni[j].nazwaUzytkownika + " " + sredni[j].punkty + "p");
                wyswietlaTrudny[j].setText(j + 1 + ". " + trudny[j].nazwaUzytkownika + " " + trudny[j].punkty + "p");
            }

        }
    }

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
