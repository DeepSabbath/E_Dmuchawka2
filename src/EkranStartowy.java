/**
 * Created by Amadeusz on 26.12.2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EkranStartowy extends JPanel{

    JLabel rozpocznijGre;
    JLabel infoOAutorze;
    JLabel poziomLatwy;
    JLabel poziomSredni;
    JLabel poziomTrudny;
    JLabel wybierzPoziomTrudnosci;
    JLabel tlo;
    JLabel wyjscieZAplikacji;
    JLabel rekordy;
    int poziomTrudnosci = 1;

    javax.swing.Timer czasGry = new javax.swing.Timer(1000, new LiczCzasGry());

    public EkranStartowy(int width, int height)
    {
        setLayout(null);
        setSize(width, height);
        init();
        setVisible(true);
    }

    private void init()
    {
        Font font = new Font("Helvetica", Font.BOLD, 30);
        Font font2 = new Font("Helvetica", Font.BOLD, 15);

        wybierzPoziomTrudnosci = new JLabel("Wybierz poziom trudności");
        wybierzPoziomTrudnosci.setSize(300,40);
        wybierzPoziomTrudnosci.setLocation(950,150);
        wybierzPoziomTrudnosci.setForeground(Color.black);
        wybierzPoziomTrudnosci.setFont(font2);
        add(wybierzPoziomTrudnosci);

        poziomLatwy = new JLabel("łatwy");
        poziomLatwy.setSize(100,40);
        poziomLatwy.setLocation(950,200);
        poziomLatwy.setForeground(Color.green);
        poziomLatwy.setFont(font);
        poziomLatwy.addMouseListener(new PoziomLatwyClick());
        add(poziomLatwy);

        poziomSredni = new JLabel("średni");
        poziomSredni.setSize(100,40);
        poziomSredni.setLocation(950,280);
        poziomSredni.setForeground(Color.red);
        poziomSredni.setFont(font);
        poziomSredni.addMouseListener(new PoziomSredniClick());
        add(poziomSredni);

        poziomTrudny = new JLabel("trudny");
        poziomTrudny.setSize(100,40);
        poziomTrudny.setLocation(950,360);
        poziomTrudny.setForeground(Color.red);
        poziomTrudny.setFont(font);
        poziomTrudny.addMouseListener(new PoziomTrudnyClick());
        add(poziomTrudny);

        rozpocznijGre = new JLabel("Graj");
        rozpocznijGre.setSize(200,40);
        rozpocznijGre.setLocation(950,90);
        rozpocznijGre.setFont(font);
        rozpocznijGre.setForeground(Color.yellow);
        rozpocznijGre.addMouseListener(new RozpocznijGreClick());
        add(rozpocznijGre);

        infoOAutorze = new JLabel("Info o autorze");
        infoOAutorze.setSize(200,40);
        infoOAutorze.setLocation(950,440);
        infoOAutorze.setFont(font);
        infoOAutorze.setForeground(Color.yellow);
        infoOAutorze.addMouseListener(new oAutorzeClick());
        add(infoOAutorze);

        rekordy = new JLabel("Rekordy");
        rekordy.setSize(200,40);
        rekordy.setLocation(950,510);
        rekordy.setFont(font);
        rekordy.setForeground(Color.yellow);
        rekordy.addMouseListener(new WyswietlRekordyClick());
        add(rekordy);

        wyjscieZAplikacji = new JLabel("Wyjście");
        wyjscieZAplikacji.setSize(200, 40);
        wyjscieZAplikacji.setLocation(950,580);
        wyjscieZAplikacji.setFont(font);
        wyjscieZAplikacji.setForeground(Color.yellow);
        wyjscieZAplikacji.addMouseListener(new WyjscieClick());
        add(wyjscieZAplikacji);

        ustawTlo("image//EkranStartowy.jpg");

        czasGry.start();
    }

    public void ustawTlo(String plik)
    {
        try {
            tlo = new JLabel(new ImageIcon(plik));
            tlo.setOpaque(true);
            tlo.setBounds(0, 0, 1280, 1024);
            add(tlo);
        } catch (Exception e)
        {
            System.out.println("Blad" + e);
        }
    }

    private class LiczCzasGry implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            Main.lacznyCzasOdpaleniaAplikacji++;
            if(Poziom.czyRosnie)
            {
                Main.lacznyCzasDmuchania++;
            }
        }
    }

    class PoziomLatwyClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 1;
            poziomLatwy.setForeground(Color.green);
            poziomSredni.setForeground(Color.red);
            poziomTrudny.setForeground(Color.red);
        }
    }

    class PoziomSredniClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 2;
            poziomSredni.setForeground(Color.green);
            poziomLatwy.setForeground(Color.red);
            poziomTrudny.setForeground(Color.red);
        }
    }
    class PoziomTrudnyClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 3;
            poziomTrudny.setForeground(Color.green);
            poziomSredni.setForeground(Color.red);
            poziomLatwy.setForeground(Color.red);
        }
    }

    class oAutorzeClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            InfoOAutorze i = new InfoOAutorze();
            i.init();
        }
    }

    class RozpocznijGreClick extends MouseAdapter              // definicja dzia�ania buttona
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
            }
            removeAll();
            String tlo = "image//kopalnia1.jpg";
            Poziom1 p1 = new Poziom1(poziomTrudnosci, wymaganaMocDmuchniecia, potrzebnyCzasDmuchniecia, czasNaPoziom, tlo, 1);
            add(p1);
            repaint();
            p1.requestFocusInWindow();
        }
    }
    class WyjscieClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {

            CzasDoZapisu cdz = new CzasDoZapisu(Main.lacznyCzasOdpaleniaAplikacji, Main.lacznyCzasDmuchania);

            try {
                zapis("czas.txt", cdz);
            } catch (IOException e1) {
                System.out.println("Błąd zapisu");
            }
            System.exit(0);
        }
    }

    public static void zapis(String nazwaPl, CzasDoZapisu cdz)throws IOException {
        ObjectOutputStream pl=null;
        try{
            pl=new ObjectOutputStream(new FileOutputStream(nazwaPl));
            pl.writeObject(cdz);
            pl.flush();
        }
        finally{
            if(pl!=null)
                pl.close();
        }
    }

    class WyswietlRekordyClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {

            removeAll();
            OknoZRekordami ozr = new OknoZRekordami();
            add(ozr);
            repaint();
            ozr.requestFocusInWindow();
        }
    }
}
