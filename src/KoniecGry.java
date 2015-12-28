import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Amadeusz on 28.12.2015.
 */
public class KoniecGry extends JPanel {

    int punkty;
    int poziomTrudnosci;

    JLabel punktyLBL;
    JLabel restartLBL;
    JLabel zakonczGreLBL;
    TextField nazwaUzytkownika;

    KoniecGry(int punkty, int poziomTrudnosci)
    {
        this.punkty = punkty;
        this.poziomTrudnosci = poziomTrudnosci;
        setLayout(null);
        setSize(Main.SZEROKOSC,Main.WYSOKOSC);
        init();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    }

    public void init()
    {
        Font font = new Font("Helvetica", Font.BOLD, 20);

        zakonczGreLBL = new JLabel("Zakoncz gre");
        zakonczGreLBL.setSize(200,50);
        zakonczGreLBL.setLocation(900,600);
        zakonczGreLBL.setForeground(Color.red);
        zakonczGreLBL.setFont(font);
        zakonczGreLBL.addMouseListener(new ZakonczGreClick());
        add(zakonczGreLBL);

        punktyLBL = new JLabel();
        punktyLBL.setSize(400,50);
        punktyLBL.setLocation(150,100);
        punktyLBL.setForeground(Color.black);
        punktyLBL.setFont(font);
        punktyLBL.setText("Punkty lacznie: " + punkty);
        add(punktyLBL);

        restartLBL = new JLabel("Zacznij od nowa");
        restartLBL.setSize(400,50);
        restartLBL.setLocation(150,600);
        restartLBL.setForeground(Color.red);
        restartLBL.setFont(font);
        restartLBL.addMouseListener(new RestartClick());
        add(restartLBL);
    }

    class ZakonczGreClick extends MouseAdapter              // definicja dzia³ania buttona
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

    class RestartClick extends MouseAdapter              // definicja dzia³ania buttona
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
            Poziom1 p1 = new Poziom1(poziomTrudnosci, wymaganaMocDmuchniecia, potrzebnyCzasDmuchniecia, czasNaPoziom);
            add(p1);
            repaint();
            p1.requestFocusInWindow();
        }
    }
}
