import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <b>PrzejscieMiedzyPoziomami</b> - klasa odpowiadająca za obsługę przejścia pomiędzy kolejnymi poziomami gry
 * @author Amadeusz Kardasz
 */
public class PrzejscieMiedzyPoziomami extends JPanel{

    static int aktualnyPoziom;
    int punkty;
    int punktyZaPoziom;
    int poziomTrudnosci;
    JLabel nastepnyPoziom;
    JLabel punktyLBL;
    JLabel punktyZaPoziomLBL;
    JLabel tlo;
    JLabel ukonczylesLBL;
    JLabel brawoLBL;

    /**
     * konstruktora pozwalający na wywołanie panelu z odpowiednimi parametrami oraz wyrysowujące ten panel
     */

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
    } // koniec konstruktora

    /**
     * metoda wyrysowująca początkową zawartość okna
     */

    public void init()
    {
        nastepnyPoziom = new JLabel("Następny poziom");
        nastepnyPoziom.setSize(400,50);
        nastepnyPoziom.setLocation(600,600);
        nastepnyPoziom.setForeground(Color.black);
        nastepnyPoziom.setFont(Main.ustawCzcionke(40));
        nastepnyPoziom.addMouseListener(new NastepnyPoziomKlik());
        add(nastepnyPoziom);

        brawoLBL = new JLabel("Brawo!");
        brawoLBL.setSize(300,70);
        brawoLBL.setLocation(530,40);
        brawoLBL.setForeground(Color.black);
        brawoLBL.setFont(Main.ustawCzcionke(60));
        add(brawoLBL);

        ukonczylesLBL = new JLabel("Ukończyłeś " + aktualnyPoziom + " poziom");
        ukonczylesLBL.setSize(600,50);
        ukonczylesLBL.setLocation(530,180);
        ukonczylesLBL.setForeground(Color.black);
        ukonczylesLBL.setFont(Main.ustawCzcionke(45));
        add(ukonczylesLBL);

        punktyLBL = new JLabel();
        punktyLBL.setSize(400,50);
        punktyLBL.setLocation(530,400);
        punktyLBL.setForeground(Color.black);
        punktyLBL.setFont(Main.ustawCzcionke(35));
        punktyLBL.setText("Punkty łącznie: " + punkty);
        add(punktyLBL);

        punktyZaPoziomLBL = new JLabel();
        punktyZaPoziomLBL.setSize(400,50);
        punktyZaPoziomLBL.setLocation(530,300);
        punktyZaPoziomLBL.setForeground(Color.black);
        punktyZaPoziomLBL.setFont(Main.ustawCzcionke(35));
        punktyZaPoziomLBL.setText("Punkty za poziom " + punktyZaPoziom);
        add(punktyZaPoziomLBL);

        add(Poziom.ustawTlo("image//wybuch3.jpg"));
    } // koniec init


    /**
     * metoda ustawiająca odpowiednie parametry następnego poziomu oraz wywołująca obiekt odpowiedzialny za jego obsługę
     */

    class NastepnyPoziomKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {

            int wymaganaMocDmuchniecia = 0;
            int potrzebnyCzasDmuchniecia = 0;
            int czasNaPoziom = 0;
            String tlo = "";

            switch (aktualnyPoziom){
                case 1:
                    tlo = "image//kopalnia2.jpg";
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 20;
                            potrzebnyCzasDmuchniecia = 20;
                            czasNaPoziom = 25;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 40;
                            potrzebnyCzasDmuchniecia = 30;
                            czasNaPoziom = 20;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 75;
                            potrzebnyCzasDmuchniecia = 40;
                            czasNaPoziom = 15;
                            break;
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    Poziom2 p2 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(p2);
                    repaint();
                    p2.requestFocusInWindow();
                    break;
                case 2:
                    tlo = "image//kopalnia3.jpg";
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 20;
                            potrzebnyCzasDmuchniecia = 25;
                            czasNaPoziom = 20;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 50;
                            potrzebnyCzasDmuchniecia = 30;
                            czasNaPoziom = 18;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 90;
                            potrzebnyCzasDmuchniecia = 40;
                            czasNaPoziom = 14;
                            break;
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    Poziom2 p3 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(p3);
                    repaint();
                    p3.requestFocusInWindow();
                    break;
                case 3:
                    tlo = "image//kopalnia1.jpg";
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 25;
                            potrzebnyCzasDmuchniecia = 25;
                            czasNaPoziom = 20;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 40;
                            potrzebnyCzasDmuchniecia = 40;
                            czasNaPoziom = 18;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 60;
                            potrzebnyCzasDmuchniecia = 60;
                            czasNaPoziom = 14;
                            break;
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    Poziom2 p4 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(p4);
                    repaint();
                    p4.requestFocusInWindow();
                    break;
                case 4:
                    tlo = "image//kopalnia2.jpg";
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 30;
                            potrzebnyCzasDmuchniecia = 30;
                            czasNaPoziom = 18;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 50;
                            potrzebnyCzasDmuchniecia = 40;
                            czasNaPoziom = 14;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 75;
                            potrzebnyCzasDmuchniecia = 60;
                            czasNaPoziom = 13;
                            break;
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    Poziom2 p5 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(p5);
                    repaint();
                    p5.requestFocusInWindow();
                    break;
                case 5:
                    tlo = "image//kopalnia3.jpg";
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 35;
                            potrzebnyCzasDmuchniecia = 30;
                            czasNaPoziom = 18;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 60;
                            potrzebnyCzasDmuchniecia = 40;
                            czasNaPoziom = 14;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 90;
                            potrzebnyCzasDmuchniecia = 60;
                            czasNaPoziom = 12;
                            break;
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    Poziom2 p6 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(p6);
                    repaint();
                    p6.requestFocusInWindow();
                    break;
                case 6:
                    tlo = "image//kopalnia1.jpg";
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 40;
                            potrzebnyCzasDmuchniecia = 30;
                            czasNaPoziom = 16;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 50;
                            potrzebnyCzasDmuchniecia = 50;
                            czasNaPoziom = 12;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 50;
                            potrzebnyCzasDmuchniecia = 70;
                            czasNaPoziom = 10;
                            break;
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    Poziom2 p7 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(p7);
                    repaint();
                    p7.requestFocusInWindow();
                    break;
                case 7:
                    tlo = "image//kopalnia2.jpg";
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 35;
                            potrzebnyCzasDmuchniecia = 40;
                            czasNaPoziom = 18;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 60;
                            potrzebnyCzasDmuchniecia = 50;
                            czasNaPoziom = 12;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 65;
                            potrzebnyCzasDmuchniecia = 70;
                            czasNaPoziom = 10;
                            break;
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    Poziom2 p8 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(p8);
                    repaint();
                    p8.requestFocusInWindow();
                    break;
                case 8:
                    tlo = "image//kopalnia3.jpg";
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 40;
                            potrzebnyCzasDmuchniecia = 40;
                            czasNaPoziom = 16;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 70;
                            potrzebnyCzasDmuchniecia = 50;
                            czasNaPoziom = 10;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 80;
                            potrzebnyCzasDmuchniecia = 70;
                            czasNaPoziom = 9;
                            break;
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    Poziom2 p9 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(p9);
                    repaint();
                    p9.requestFocusInWindow();
                    break;
                case Poziom.LICZBAPOZIOMOW - 1:
                    tlo = "image//kopalnia3.jpg";
                    switch (poziomTrudnosci)
                    {
                        case 1:
                            wymaganaMocDmuchniecia = 45;
                            potrzebnyCzasDmuchniecia = 40;
                            czasNaPoziom = 16;
                            break;
                        case 2 :
                            wymaganaMocDmuchniecia = 60;
                            potrzebnyCzasDmuchniecia = 60;
                            czasNaPoziom = 10;
                            break;
                        case 3 :
                            wymaganaMocDmuchniecia = 80;
                            potrzebnyCzasDmuchniecia = 80;
                            czasNaPoziom = 8;
                            break;
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    PoziomOstatni pO = new PoziomOstatni(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(pO);
                    repaint();
                    pO.requestFocusInWindow();
                    break;
                default:
                    tlo = "image//kopalnia3.jpg";
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
                    } // koniec switch
                    removeAll();
                    aktualnyPoziom++;
                    PoziomOstatni po = new PoziomOstatni(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty, tlo, aktualnyPoziom);
                    add(po);
                    repaint();
                    po.requestFocusInWindow();
                    break;
            } // koniec switch
        }
    } // koniec NastepnyPoziomKlik
} // koniec PrzejscieMiedzyPoziomami
