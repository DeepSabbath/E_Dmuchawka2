/**
 * Created by Amadeusz on 26.12.2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        wybierzPoziomTrudnosci = new JLabel("Wybierz poziom trudnosci");
        wybierzPoziomTrudnosci.setSize(300,40);
        wybierzPoziomTrudnosci.setLocation(950,180);
        wybierzPoziomTrudnosci.setForeground(Color.black);
        wybierzPoziomTrudnosci.setFont(font2);
        add(wybierzPoziomTrudnosci);

        poziomLatwy = new JLabel("łatwy");
        poziomLatwy.setSize(100,40);
        poziomLatwy.setLocation(950,230);
        poziomLatwy.setForeground(Color.green);
        poziomLatwy.setFont(font);
        poziomLatwy.addMouseListener(new PoziomLatwyClick());
        add(poziomLatwy);

        poziomSredni = new JLabel("średni");
        poziomSredni.setSize(100,40);
        poziomSredni.setLocation(950,310);
        poziomSredni.setForeground(Color.red);
        poziomSredni.setFont(font);
        poziomSredni.addMouseListener(new PoziomSredniClick());
        add(poziomSredni);

        poziomTrudny = new JLabel("trudny");
        poziomTrudny.setSize(100,40);
        poziomTrudny.setLocation(950,390);
        poziomTrudny.setForeground(Color.red);
        poziomTrudny.setFont(font);
        poziomTrudny.addMouseListener(new PoziomTrudnyClick());
        add(poziomTrudny);

        rozpocznijGre = new JLabel("Graj");
        rozpocznijGre.setSize(200,40);
        rozpocznijGre.setLocation(950,120);
        rozpocznijGre.setFont(font);
        rozpocznijGre.setForeground(Color.yellow);
        rozpocznijGre.addMouseListener(new RozpocznijGreClick());
        add(rozpocznijGre);

        infoOAutorze = new JLabel("Info o autorze");
        infoOAutorze.setSize(200,40);
        infoOAutorze.setLocation(950,470);
        infoOAutorze.setFont(font);
        infoOAutorze.setForeground(Color.yellow);
        infoOAutorze.addMouseListener(new oAutorzeClick());
        add(infoOAutorze);

        rekordy = new JLabel("Rekordy");
        rekordy.setSize(200,40);
        rekordy.setLocation(950,540);
        rekordy.setFont(font);
        rekordy.setForeground(Color.yellow);
        rekordy.addMouseListener(new WyswietlRekordyClick());
        add(rekordy);

        wyjscieZAplikacji = new JLabel("Wyjście");
        wyjscieZAplikacji.setSize(200, 40);
        wyjscieZAplikacji.setLocation(950,610);
        wyjscieZAplikacji.setFont(font);
        wyjscieZAplikacji.setForeground(Color.yellow);
        wyjscieZAplikacji.addMouseListener(new WyjscieClick());
        add(wyjscieZAplikacji);

        ustawTlo("image//EkranStartowy.jpg");
    }

    public void ustawTlo(String plik)
    {
        try {
            tlo = new JLabel(new ImageIcon(plik));
            tlo.setOpaque(true);
            tlo.setBounds(0, 0, 1280, 800);
            add(tlo);
        } catch (Exception e)
        {
            System.out.println("Blad" + e);
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
            String tlo = "image//kopalnia1.jpg";
            Poziom1 p1 = new Poziom1(poziomTrudnosci, wymaganaMocDmuchniecia, potrzebnyCzasDmuchniecia, czasNaPoziom, tlo);
            add(p1);
            repaint();
            p1.requestFocusInWindow();
        }
    }
    class WyjscieClick extends MouseAdapter              // definicja dzia�ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
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
