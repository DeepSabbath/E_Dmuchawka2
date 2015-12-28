import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Amadeusz on 28.12.2015.
 */
public class PrzejscieMiedzyPoziomami extends JPanel{

    int aktualnyPoziom;
    int punkty;
    int punktyZaPoziom;
    int poziomTrudnosci;
    JLabel nastepnyPoziom;
    JLabel punktyLBL;
    JLabel punktyZaPoziomLBL;

    PrzejscieMiedzyPoziomami(int aktualnyPoziom, int punkty, int punktyZaPoziom, int poziomTrudnosci)
    {
        this.aktualnyPoziom = aktualnyPoziom;
        this.punkty = punkty;
        this.punktyZaPoziom = punktyZaPoziom;
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
        Font font = new Font("Helvetica", Font.BOLD, 30);

        nastepnyPoziom = new JLabel("Nastepny poziom");
        nastepnyPoziom.setSize(400,50);
        nastepnyPoziom.setLocation(300,420);
        nastepnyPoziom.setForeground(Color.black);
        nastepnyPoziom.setFont(font);
        nastepnyPoziom.addMouseListener(new NastepnyPoziomClick());
        add(nastepnyPoziom);

        punktyLBL = new JLabel();
        punktyLBL.setSize(400,50);
        punktyLBL.setLocation(150,100);
        punktyLBL.setForeground(Color.black);
        punktyLBL.setFont(font);
        punktyLBL.setText("Punkty lacznie: " + punkty);
        add(punktyLBL);

        punktyZaPoziomLBL = new JLabel();
        punktyZaPoziomLBL.setSize(400,50);
        punktyZaPoziomLBL.setLocation(150,200);
        punktyZaPoziomLBL.setForeground(Color.black);
        punktyZaPoziomLBL.setFont(font);
        punktyZaPoziomLBL.setText("Punkty za poziom " + punktyZaPoziom);
        add(punktyZaPoziomLBL);
    }

    class NastepnyPoziomClick extends MouseAdapter              // definicja dzia³ania buttona
    {
        @Override
        public void mouseClicked(MouseEvent e) {

            int wymaganaMocDmuchniecia = 0;
            int potrzebnyCzasDmuchniecia = 0;
            int czasNaPoziom = 0;

            switch (aktualnyPoziom){
                case 1:
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 50;
                            potrzebnyCzasDmuchniecia = 30;
                            czasNaPoziom = 25;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 60;
                            potrzebnyCzasDmuchniecia = 35;
                            czasNaPoziom = 19;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 80;
                            potrzebnyCzasDmuchniecia = 70;
                            czasNaPoziom = 16;
                            break;
                    }
                    removeAll();
                    Poziom2 p2 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty);
                    add(p2);
                    repaint();
                    p2.requestFocusInWindow();
                    break;
                case 2:
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 45;
                            potrzebnyCzasDmuchniecia = 40;
                            czasNaPoziom = 22;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 65;
                            potrzebnyCzasDmuchniecia = 50;
                            czasNaPoziom = 17;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 85;
                            potrzebnyCzasDmuchniecia = 80;
                            czasNaPoziom = 14;
                            break;
                    }
                    removeAll();
                    Poziom3 p3 = new Poziom3(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty);
                    add(p3);
                    repaint();
                    p3.requestFocusInWindow();
                    break;
            }
        }
    }

}
