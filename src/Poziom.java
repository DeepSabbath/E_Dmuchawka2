import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;

/**
 * Klasa abstrakcyjna odpowiedzialna za obsługę poszczególnych poziomów
 * @author Amadeusz Kardasz
 */
public abstract class Poziom extends JPanel implements KeyListener
{
    /** ustawia maksymalna wartosc czasu dmuchniecia */
    static final double maksymalnyCzasDmuchniecia = 100;
    /** zmienna określająca maksymalne odstępstwo od wyznaczonego czasu dmuchnięcia, przy którym poziom zostaje zaliczony */
    final int blad = 8;
    /** timer odliczający czas naciśnięcia na dynamit */
    javax.swing.Timer czasDmuchniecia = new javax.swing.Timer(50, new CzasDmuchniecia());
    /** timer odliczający czas do końca poziomu */
    javax.swing.Timer czasDoKoncaTimer = new javax.swing.Timer(1000, new LiczCzasDoKonca());
    /** timer wg, którego wskazania dokonują się animacje po skończonym poziomie */
    javax.swing.Timer czasDoNastepnegoPoziomu = new javax.swing.Timer(500, new LiczCzasDoNastepnegoPoziomu());

    /** czas od zakończenia poziomu do pojawienia się okna odpowiedzialnego za przejście między poziomami - wartosc 2 oznacza sekundę */
    int czasZapaleniaDynamitu = 4;
    /** maksymalny czas na przejście danego poziomu */
    int czasNaPoziom;
    /** zmienna przechowująca czas do zakończenia poziomu */
    int pozostalyCzasNaPoziom;
    /** zmienna przechowująca wyagany czas kliknięcia w dynamit, aby poziom został ukończony - wartość 20 oznacza 1 sekundę */
    int potrzebnyCzasDmuchniecia;
    /** zmienna przechowująca aktulany czas kliknięcia w dynamit */
    double aktualnyCzasDmuchniecia;
    /** zmienna przechowująca aktualnie ustawioną siłę dmuchnięcia */
    int aktualnaMocDmuchniecia;
    /** wyświetla aktualną i wymaganą moc dmuchnięcia */
    JLabel aktualnaMocDmuchnieciaLBL;
    /** zmienna przechowująca siłę dmuchnięcia potrzebną do ukończenia poziomu */
    int wymaganaMocDmuchniecia;
    /** przechowuje poziom trudnosci */
    int poziomTrudnosci;
    /** przechowuje łączny wynik punktowy */
    int punkty;
    /** przechowuje wynik punktowy za altualny poziom */
    int punktyZaPoziom = 0;
    /** zmienna określająca czy wskaźnik czasu dmuchnięcia rośnie */
    static boolean czyRosnie = false;
    /** zmienna przyjmująca wartość true w momencie ukończenia poziomu */
    boolean czyWygrano = false;
    /** zmiennia określająca ścieżkę do pliku */
    String plik;
    /** zmienna przechowuje numer aktualnego poziomu */
    int aktualnyPoziom;
    /** zmienna określa maksymalną liczbę poziomów */
    public static final int LICZBAPOZIOMOW = 10;
    /** wyświetla aktualny poziom */
    JLabel poziomLBL;
    /** pozwala na zakończnie gry */
    JLabel zakonczGreLBL;
    /** wyświetla czas do końca poziomu */
    JLabel czasDoKoncaLBL;
    /** wyświetla łączną liczbę zdobytych punktów */
    JLabel punktyLBL;
    /** wyświetla dynamit, który należy zapalić */
    JLabel dynamit;
    /** wyświetla obrazek symulujący oddech smoka zionącego ogniem */
    JLabel ogien;
    /** instancja obiektu WskaznikDmuchniecia - wyswietla wskaźnik czasu dmuchnięcia */
    WskaznikDmuchniecia wsk;
    PasekGryGora pgg;
    PasekGryDol pgd;

    /**
     * konstruktor umożliwiający podanie parametrów poziomu
     * inicjowane są tutaj etykiet z odpowiednimi wartościami
     * w oknie umieszczne są potrzebne elementy
     */

    public Poziom (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, String plik, int aktualnyPoziom)
    {
        this.aktualnyPoziom = aktualnyPoziom;
        this.plik = plik;
        this.poziomTrudnosci = poziomTrudnosci;
        this.wymaganaMocDmuchniecia = wymaganaMocDmuchniecia;
        this.potrzebnyCzasDmuchniecia = potrzebnyCzasDmuchniecia;
        this.czasNaPoziom = czasNaPoziom;
        setLayout(null);
        setSize(Main.SZEROKOSC,Main.WYSOKOSC);
        czyRosnie = false;

        wsk = new WskaznikDmuchniecia();
        add(wsk);
        init();
        pgg = new PasekGryGora();
        add(pgg);
        pgd = new PasekGryDol();
        add(pgd);

        add(ustawTlo(plik));

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    } // koniec konstruktora

    /**
     * w tej metodzie ustawiane jest tło aplikacji
     * @param plik - ścieżka do pliku
     */

    public static JLabel ustawTlo(String plik)
    {
        JLabel tlo = new JLabel();
        try {
            tlo.setIcon(new ImageIcon(plik));
            tlo.setOpaque(false);
            tlo.setBounds(0, 0, Main.SZEROKOSC, Main.WYSOKOSC);

        } catch (Exception e)
        {
            System.out.println("Blad" + e);
        }
        return tlo;
    } // koniec ustawTlo

    /**
     * funkcja wyrysowująca początkową zawartość okna, tutaj jest losowane położenie dynamitu
     */

    public void init()
    {
        ogien = new JLabel();
        ogien.setSize(200,550);
        ogien.setLocation(600,200);
        add(ogien);

        dynamit = new JLabel(new ImageIcon("image//dynamitOFF.png"));
        dynamit.setSize(60,120);
        dynamit.setLocation(losujPolozenie(Main.SZEROKOSC,Main.WYSOKOSC));
        dynamit.addMouseListener(new DynamitKlik());
        add(dynamit);

        poziomLBL = new JLabel("Poziom " + aktualnyPoziom);
        poziomLBL.setSize(200,40);
        poziomLBL.setLocation(50,0);
        poziomLBL.setForeground(Color.yellow);
        poziomLBL.setFont(Main.ustawCzcionke(30));
        add(poziomLBL);

        zakonczGreLBL = new JLabel("Zakończ grę");
        zakonczGreLBL.setSize(200,40);
        zakonczGreLBL.setLocation(120,650);
        zakonczGreLBL.setForeground(Color.yellow);
        zakonczGreLBL.setFont(Main.ustawCzcionke(30));
        zakonczGreLBL.addMouseListener(new ZakonczGreKlik());
        add(zakonczGreLBL);

        czasDoKoncaLBL = new JLabel();
        czasDoKoncaLBL.setSize(500,50);
        czasDoKoncaLBL.setLocation(500, 0);
        czasDoKoncaLBL.setFont(Main.ustawCzcionke(30));
        czasDoKoncaLBL.setForeground(Color.yellow);
        czasDoKoncaLBL.setText("Pozostały czas: " + pozostalyCzasNaPoziom);
        add(czasDoKoncaLBL);

        punktyLBL = new JLabel();
        punktyLBL.setSize(200,50);
        punktyLBL.setLocation(950,0);
        punktyLBL.setForeground(Color.yellow);
        punktyLBL.setFont(Main.ustawCzcionke(30));
        punktyLBL.setText("Punkty: " + punkty);
        add(punktyLBL);

        aktualnaMocDmuchnieciaLBL = new JLabel();
        aktualnaMocDmuchnieciaLBL.setSize(500,40);
        aktualnaMocDmuchnieciaLBL.setLocation(700,650);
        aktualnaMocDmuchnieciaLBL.setFont(Main.ustawCzcionke(30));
        aktualnaMocDmuchnieciaLBL.setForeground(Color.yellow);
        aktualnaMocDmuchnieciaLBL.setText("Aktualna moc dmuchnięcia: " + aktualnaMocDmuchniecia + "/" + wymaganaMocDmuchniecia);
        add(aktualnaMocDmuchnieciaLBL);

        pozostalyCzasNaPoziom = czasNaPoziom;
        czasDmuchniecia.start();
        czasDoKoncaTimer.start();
    } // koniec init

    /**
     * metoda zwracająca punkt w obrębie okna aplikacji, w którym umieszczany jest dynamit
     * @return metoda zwraca punkt
     */

    public Point losujPolozenie(int szerokosc, int wysokosc)
    {
        Dimension d = dynamit.getSize(); // pobiera wymiary labela z dynamitem
        int w = d.height;
        int s = d.width;
        Random rand = new Random();
        int sze = rand.nextInt(szerokosc - s);
        int wys = rand.nextInt(wysokosc - 2*w)+50;
        Point pol = new Point(sze, wys);
        return pol;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * metoda obsługująca zwiększenie/zmniejszenie siły dmuchnięcia po wybraniu odpowiedniej strzałki
     * oraz wpisująca wartość tej siły do odpowiedniego labela
     */

    @Override
    public void keyPressed(KeyEvent e)
    {
        int a = e.getKeyCode(); // pobranie wartości wciśnietego guzika

        if (!czyRosnie)
        {
            if (a == KeyEvent.VK_UP && aktualnaMocDmuchniecia < 100)
            {
                aktualnaMocDmuchniecia++;
            }

            if (a == KeyEvent.VK_DOWN && aktualnaMocDmuchniecia > 0)
            {
                aktualnaMocDmuchniecia--;
            }
            aktualnaMocDmuchnieciaLBL.setText("Aktualna moc dmuchnięcia: " + aktualnaMocDmuchniecia + "/" + wymaganaMocDmuchniecia);
        }
    } // koniec keyPressed

    /**
     * definicja działania po wyborze opcji zakończ grę
     */

    class ZakonczGreKlik extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            zakonczenieGry();
        }
    } // koniec ZakonczGreKlik

    /**
     * definicja zachowania aplikacji po wciśnięciu na dynamit
     */

    class DynamitKlik extends MouseAdapter
    {
        public void mousePressed(MouseEvent e) {
            czyRosnie = true;  // ustawienie na true zmiennej odpowiadającej za wzrost czasu dmuchnięcia
        }

        public void mouseReleased(MouseEvent e) {

            czyRosnie = false;
            ogien.setIcon(new ImageIcon("image//pusty.png"));

            if (sprawdzWynik() && sprawdzMocDmuchniecia() && !czyWygrano)  // sprawdzenie czy gracz wygrał po zwolnieniu przycisku myszki
            {
                wygrana();
            }
            else    // wyzerowanie wartości czasu dmuchnięcia w momencie, gdy nie wygrano
            {
                aktualnyCzasDmuchniecia = 0;
            }
        }
    } // koniec DynamitKlik


    /**
     * funkcja sprawdzająca czy ustawiono odpowiednią moc dmuchnięcia
     */

    private boolean sprawdzMocDmuchniecia()
    {
        if (aktualnaMocDmuchniecia >= wymaganaMocDmuchniecia)
            return  true;
        else
            return  false;
    } // koniec sprawdzMocDmuchniecia

    /**
     * funkcja sprawdzająca czy wciskona przycisk myszki wystarczająco długo
     */

    private boolean sprawdzWynik()
    {
        if(potrzebnyCzasDmuchniecia + blad >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - blad <= aktualnyCzasDmuchniecia)
            return true;

        else
            return false;

    } // koniec sprawdzWynik

    /**
     * definiuje zachowanie timera
     */

    private class CzasDmuchniecia implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(czyRosnie && aktualnyCzasDmuchniecia < maksymalnyCzasDmuchniecia)  // inkrementacja zmiennej, w momencie wciśnięcia na dynamit
            {                                                                     // gdy maksmymalny czas dmuchnięcia nie jest przekroczony
                aktualnyCzasDmuchniecia++;
                repaint();
            }
        }
    } // koniec czasDmuchniecia

    /**
     * zachownie timera odpowiadającego za odliczanie czasu do zakończenia poziomu
     */

    private class LiczCzasDoKonca implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {

            pozostalyCzasNaPoziom--;
            czasDoKoncaLBL.setText("Pozostały czas: " + pozostalyCzasNaPoziom);

            if (pozostalyCzasNaPoziom == 0 && !czyWygrano) // konczy gre w momencie, gdy czas sie skonczy, a gracz nie wygral
            {
                zakonczenieGry();
            }
        }
    } // koniec LiczCzasDoKonca

    /**
     * określa zachowanie aplikacji od momentu ukończenia poziomu do wyświetlenia okna pomiędzy poziomami
     */

    private class LiczCzasDoNastepnegoPoziomu implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {

            ImageIcon icon;

            if (czasZapaleniaDynamitu > 0)  // dekrementuje, co pół seundy zmienną odliczająca czas do następnego poziomu
            {
                czasZapaleniaDynamitu--;
            }

            switch (czasZapaleniaDynamitu) // zmiana wyświetlanego obrazu dynamitu w czasie zapalenia
            {
                case 3:
                    icon = new ImageIcon("image//dynamit1.2.png");
                    dynamit.setIcon(icon);
                    break;
                case 2:
                    icon = new ImageIcon("image//dynamit1.3.png");
                    dynamit.setIcon(icon);
                    break;
                case 1:
                    icon = new ImageIcon("image//dynamit1.4.png");
                    dynamit.setIcon(icon);
                    break;
                case 0:
                    Main.grajDzwiek(new File("sounds//wybuch.wav"));
                    przejdzDoNastepnegoPoziomu();
                    czasDoNastepnegoPoziomu.stop();
                    break;
            }
        }
    } // koniec LiczCzasDoNasteonegoPoziomu

    /**
     * metoda odpowiadająca za zmianę wyświetlanego dynamitu (na zapalony)
     */

    private void zapalDynamit()
    {
        czasDoKoncaTimer.stop();
        ImageIcon icon = new ImageIcon("image//dynamit1.1.png");
        Main.grajDzwiek(new File("sounds//lont.wav"));
        dynamit.setIcon(icon);
    } // koniec zapalDynamit

    /**
     * metoda tworząca obiekt klasy KoniecGry odpowiedzialny za obsługę zakończenia gry
     */

    public void zakonczenieGry()
    {
        removeAll();
        KoniecGry kg = new KoniecGry(punkty, poziomTrudnosci);
        add(kg);
        repaint();
        kg.requestFocusInWindow();
    } // koniec zakonczenieGry

    public abstract void przejdzDoNastepnegoPoziomu();

    /**
     * metoda uruchamiania w momencie przejścia poziomu
     */

    public void wygrana()
    {
        czasDoKoncaTimer.stop();
        czyWygrano = true;
        liczPunkty();
        czasDoNastepnegoPoziomu.start();
        zapalDynamit();
    } // koniec wygrana

    /**
     * metoda licząca punkty w momencie przejścia poziomu
     */

    public void liczPunkty()
    {
        if (czyWygrano)
        {
            punktyZaPoziom = (aktualnyPoziom * poziomTrudnosci * 3) + (pozostalyCzasNaPoziom * poziomTrudnosci) * 2;
        }

        punkty +=  punktyZaPoziom;
        punktyLBL.setText("Punkty: " + punkty);
    } // koniec liczPunkty

    /**
     * klasa wyrysowująca czarny pasek na górze ekranu
     */

    public  class PasekGryGora extends JPanel
    {
        /**
         * konstruktor wyrysowujący czarny pasek w odpowiednim miejscu
         */

        public PasekGryGora()
        {
            setBounds(0, 0, 1280, 50);
            setOpaque(false);
        } // koniec klonstruktora

        public void paint (Graphics g)
        {
            //g.setColor(Color.black.BLACK);
            g.fillRect(0, 0, 1280, 50);
        }// koniec paint
    } // koniec PasekGryGora

    /**
     * klasa wyrysowująca czarny pasek na dole ekranu
     */

    public  class PasekGryDol extends JPanel
    {

        /**
         * konstruktor wyrysowujący czarny pasek w odpowiednim miejscu
         */

        public PasekGryDol()
        {
            setBounds(0, 650, 1280, 50);
            setOpaque(false);
        } // koniec konstruktora

        public void paint (Graphics g)
        {
            //g.setColor(Color.black.BLACK);
            g.fillRect(0, 0, 1280, 50);
        } // koniec paint
    } // koniec PasekGryDol

    /**
     * klasa wyrysowująca wskaźniki dmuchnięcia
     */

    public class WskaznikDmuchniecia extends JPanel {

        int prosX = 0;
        int prosY = 1;
        int prosSzer = 25;
        int prosWys = 250;
        double prosWysD = prosWys;
        int wypelnienie;

        /**
         * konstruktor, w którym ustalane jest położenie i wielkość panelu
         */

        public WskaznikDmuchniecia()
        {
            setBounds(1100, 370, 100, prosWys + 2);
        }

        public void paint (Graphics g)
        {
            wypelnienie = (int)((aktualnyCzasDmuchniecia/maksymalnyCzasDmuchniecia)*prosWysD);  // zmienna oznaczająca wypełnienie wskaźnika dmucjnięcia
            int szerokosc = dynamit.getLocation().x; // zmienna odpowiadająca za szerokość na jakiej pojawi się ogień

            g.drawRect(prosX, prosY - 1 , prosSzer, prosWys);

            if(potrzebnyCzasDmuchniecia + blad >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - blad <= aktualnyCzasDmuchniecia)
            { // zamalowanie wskaźnika na zielono i ustawienie najwyższego ognia przy uzyskaniu potrzebnego czasu dmuchnięcia
                g.setColor(Color.green);
                if (czyRosnie)
                {
                    ogien.setLocation(szerokosc - 70, 219);
                    ogien.setSize(200, 431);
                    ogien.setIcon(new ImageIcon("image//ogien3.png"));
                }
                else
                {
                    ogien.setIcon(new ImageIcon("image//pusty.png"));
                }
            }
            else if (potrzebnyCzasDmuchniecia - 20 <= aktualnyCzasDmuchniecia && aktualnyCzasDmuchniecia < potrzebnyCzasDmuchniecia)
            { // zamalowanie wskaźnika na pomarańczowo i ustawienie średniego ognia przy zbliżeniu się do potrzebnego czasu dmuchnięcia
                g.setColor((Color.orange));
                if (czyRosnie)
                {
                    ogien.setLocation(szerokosc - 37 ,397);
                    ogien.setSize(150,253);
                    ogien.setIcon(new ImageIcon("image//ogien2.png"));
                }
                else  // brak ognia na początku jeśli nie kliknięto w dynamit - poziom 1, poziom trudności - łatwy
                {
                    ogien.setIcon(new ImageIcon("image//pusty.png"));
                } // koniec if else
            }
            else
            { // w przeciwnym wypadku ustawienie koloru wskaźnika na czerwony
                g.setColor(Color.red);
                if (czyRosnie)  // wyświetlenie obrazka z ogniem tylko w momencie, gdy naciśnieto na dynamit
                {
                    ogien.setLocation(szerokosc,527);
                    ogien.setSize(70,123);
                    ogien.setIcon(new ImageIcon("image//ogien1.png"));
                }
                else
                {
                    ogien.setIcon(new ImageIcon("image//pusty.png"));
                } // koniec if else
            } // koniec if else
            g.fillRect(prosX + 1,(prosY + prosWys - wypelnienie),prosSzer - 1 , wypelnienie - 1); // narysowanie prostokąta wypełnionego w określonym stopniu
        } // koniec paint
    } // koniec WskaznikDmuchniecia
} // koniec Poziom


