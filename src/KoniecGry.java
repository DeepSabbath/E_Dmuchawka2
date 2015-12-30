import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Date;

/**
 * Created by Amadeusz on 28.12.2015.
 */
public class KoniecGry extends JPanel {

    int punkty;
    int poziomTrudnosci;
    String nazwaUżytkownika;
    Date data;

    JLabel punktyLBL;
    JLabel koniecGryLBL;
    JLabel restartLBL;
    JLabel zakonczGreLBL;
    JLabel nazwaUzytkownikaLBL;
    JTextField nazwaUzytkownikaTF;

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

    public void init()
    {

        try {
            odczytCalego("dane.txt");
        }
        catch (Exception e)
        {
            System.out.println("Błąd odczytu");
        }

        Font font = new Font("Helvetica", Font.BOLD, 20);
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

        zakonczGreLBL = new JLabel("Zakoncz gre");
        zakonczGreLBL.setSize(200,50);
        zakonczGreLBL.setLocation(950,600);
        zakonczGreLBL.setForeground(Color.red);
        zakonczGreLBL.setFont(font);
        zakonczGreLBL.addMouseListener(new ZakonczGreClick());
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
        restartLBL.addMouseListener(new RestartClick());
        add(restartLBL);
    }

    class ZakonczGreClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            zapiszDoPliku();
            removeAll();
            EkranStartowy es = new EkranStartowy(Main.SZEROKOSC, Main.WYSOKOSC);
            es.setFocusable(true);
            add(es);
            repaint();
        }
    }

    class RestartClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            int wymaganaMocDmuchniecia = 40;
            int potrzebnyCzasDmuchniecia = 40;
            int czasNaPoziom = 20;
            String tlo = "image//kopalnia1.jpg";
            zapiszDoPliku();

            switch (poziomTrudnosci) {
                case 1:
                    wymaganaMocDmuchniecia = 30;
                    potrzebnyCzasDmuchniecia = 20;
                    czasNaPoziom = 30;
                    break;
                case 2:
                    wymaganaMocDmuchniecia = 45;
                    potrzebnyCzasDmuchniecia = 40;
                    czasNaPoziom = 22;
                    break;
                case 3:
                    wymaganaMocDmuchniecia = 60;
                    potrzebnyCzasDmuchniecia = 60;
                    czasNaPoziom = 15;
                    break;
            }
            removeAll();
            Poziom1 p1 = new Poziom1(poziomTrudnosci, wymaganaMocDmuchniecia, potrzebnyCzasDmuchniecia, czasNaPoziom, tlo, 1);
            add(p1);
            repaint();
            p1.requestFocusInWindow();
        }
    }

    public void zapiszDoPliku()
    {
        try{
            zapiszDoPliku2();
        }
        catch (Exception e)
        {
            System.out.println("Błąd IO");
        }
    }

    public void zapiszDoPliku2() throws IOException, ClassNotFoundException{

        String nazwaUzytkownika = nazwaUzytkownikaTF.getText();
        DaneDoZapisu ddz = new DaneDoZapisu(nazwaUzytkownika, punkty, data, poziomTrudnosci);

        DaneDoZapisu[] tab = new DaneDoZapisu[100]; // do tablicy
        ObjectInputStream ois = null;
        try{
            ois=new ObjectInputStream(new FileInputStream("dane.txt"));
            int l=0;
            while(true)
                tab[l++]=(DaneDoZapisu)ois.readObject();

        } catch (EOFException ex) {
            // Program przeskakuje w to miejsce, kiedy dojdzie do końca pliku,
            // czyli kiedy wszystko już odczyta.
            // Teraz zamykamy plik, otwieramy w trybie zapisu i wpisujemy
            // do niego wszystko oraz dopisujemy to, co ma być dodane
            if(ois!=null)
                ois.close();

            ObjectOutputStream oos=null;

            try{
                oos=new ObjectOutputStream(new FileOutputStream("dane.txt"));
                // Wpisujemy do pliku to, co w nim już było
                for(int i=0; tab[i]!=null; i++)
                    oos.writeObject(tab[i]);

                // i dopisujemy nowy obiekt
                oos.writeObject(ddz);
                oos.flush();
            }
            catch (Exception e)
            {
                System.out.println("Błąd IO");
            }
            finally{
                if(oos!=null)
                    oos.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("Błąd IO");
        }
    }

    public static void odczytCalego2(String nazwaPl)throws IOException,ClassNotFoundException{
        ObjectInputStream ois=null;
        DaneDoZapisu[] tablicaDanych = new DaneDoZapisu[100];
        try{
            ois = new ObjectInputStream(new FileInputStream(nazwaPl));
            int l=0;
            while(true){
                tablicaDanych[l]=(DaneDoZapisu)ois.readObject();
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

    public static void odczytCalego(String nazwaPliku)
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
}
