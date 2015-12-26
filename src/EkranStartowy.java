/**
 * Created by Amadeusz on 26.12.2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EkranStartowy extends JFrame{

    JLabel rozpocznijGre;
    JLabel infoOAutorze;
    JLabel poziomLatwy;
    JLabel poziomSredni;
    JLabel poziomTrudny;
    JLabel wybierzPoziomTrudnosci;
    JLabel tlo;
    int poziomTrudnosci = 1;

    public EkranStartowy(int width, int height)
    {
        setLayout(null);
        setSize(width, height);
        setResizable(false);
        init();
        setVisible(true);
    }

    private void init()
    {
        Font font = new Font("Helvetica", Font.BOLD, 30);
        Font font2 = new Font("Helvetica", Font.BOLD, 15);



        wybierzPoziomTrudnosci = new JLabel("Wybierz poziom trudnosci");
        wybierzPoziomTrudnosci.setSize(300,40);
        wybierzPoziomTrudnosci.setLocation(950,170);
        wybierzPoziomTrudnosci.setForeground(Color.black);
        wybierzPoziomTrudnosci.setFont(font2);
        add(wybierzPoziomTrudnosci);

        poziomLatwy = new JLabel("Latwy");
        poziomLatwy.setSize(100,40);
        poziomLatwy.setLocation(950,250);
        poziomLatwy.setForeground(Color.green);
        poziomLatwy.setFont(font);
        poziomLatwy.addMouseListener(new PoziomLatwyClick());
        add(poziomLatwy);

        poziomSredni = new JLabel("Sredni");
        poziomSredni.setSize(100,40);
        poziomSredni.setLocation(950,330);
        poziomSredni.setForeground(Color.red);
        poziomSredni.setFont(font);
        poziomSredni.addMouseListener(new PoziomSredniClick());
        add(poziomSredni);

        poziomTrudny = new JLabel("Trudny");
        poziomTrudny.setSize(100,40);
        poziomTrudny.setLocation(950,410);
        poziomTrudny.setForeground(Color.red);
        poziomTrudny.setFont(font);
        poziomTrudny.addMouseListener(new PoziomTrudnyClick());
        add(poziomTrudny);

        rozpocznijGre = new JLabel("Graj");
        rozpocznijGre.setSize(200,40);
        rozpocznijGre.setLocation(950,570);
        rozpocznijGre.setFont(font);
        rozpocznijGre.setForeground(Color.yellow);
        //rozpocznijGre.addMouseListener(new RozpocznijGreClick());
        add(rozpocznijGre);

        infoOAutorze = new JLabel("Info o autorze");
        infoOAutorze.setSize(200,40);
        infoOAutorze.setLocation(950,490);
        infoOAutorze.setFont(font);
        infoOAutorze.setForeground(Color.yellow);
        infoOAutorze.addMouseListener(new oAutorzeClick());
        add(infoOAutorze);

        try {
            tlo = new JLabel(new ImageIcon("image//EkranStartowy.jpg"));
            tlo.setOpaque(true);
            tlo.setBounds(0, 0, 1280, 800);
            add(tlo);
        } catch (Exception e)
        {
            System.out.println("Blad" + e);
        }
    }

    class PoziomLatwyClick extends MouseAdapter              // definicja dzia³ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 1;
            poziomLatwy.setForeground(Color.green);
            poziomSredni.setForeground(Color.red);
            poziomTrudny.setForeground(Color.red);
        }
    }
    class PoziomSredniClick extends MouseAdapter              // definicja dzia³ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 2;
            poziomSredni.setForeground(Color.green);
            poziomLatwy.setForeground(Color.red);
            poziomTrudny.setForeground(Color.red);
        }
    }
    class PoziomTrudnyClick extends MouseAdapter              // definicja dzia³ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            poziomTrudnosci = 3;
            poziomTrudny.setForeground(Color.green);
            poziomSredni.setForeground(Color.red);
            poziomLatwy.setForeground(Color.red);
        }
    }

    class oAutorzeClick extends MouseAdapter              // definicja dzia³ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            InfoOAutorze i = new InfoOAutorze();
            i.init();
        }
    }
}
