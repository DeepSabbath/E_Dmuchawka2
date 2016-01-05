import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Klasa abstrakcyjna odpowiedzialna za obsługę poszczególnych poziomów
 * @Author Amadeusz Kardasz
 */
public abstract class Poziom extends JPanel implements KeyListener
{
    static final double maksymalnyCzasDmuchniecia = 100;
    final int blad = 8;  // zmienna określająca maksymalne odstępstwo od wyznaczonego czasu dmuchnięcia, przy którym poziom zostaje zaliczony

    javax.swing.Timer czasDmuchniecia = new javax.swing.Timer(50, new CzasDmuchniecia()); // timer odliczający czas naciśnięcia na dynamit
    javax.swing.Timer czasDoKoncaTimer = new javax.swing.Timer(1000, new LiczCzasDoKonca());  // timer odliczający czas do końca poziomu
    javax.swing.Timer czasDoNastepnegoPoziomu = new javax.swing.Timer(1000, new LiczCzasDoNastepnegoPoziomu()); // timer wg, którego wskazania dokonują się animacje po skończonym poziomie


    int czasZapaleniaDynamitu = 2; // czas od zakończenia poziomu do pojawienia się okna odpowiedzialnego za przejście między poziomami
    int czasNaPoziom;       // maksymalny czas na przejście danego poziomu
    int pozostalyCzasNaPoziom;  // zmienna przechowująca czas do zakończenia poziomu
    int potrzebnyCzasDmuchniecia;   // zmienna przechowująca wyagany czas kliknięcia w dynamit, aby poziom został ukończony - wartość 20 oznacza 1 sekundę
    double aktualnyCzasDmuchniecia;  // zmienna przechowująca aktulany czas kliknięcia w dynamit
    int aktualnaMocDmuchniecia;     // zmienna przechowująca aktualnie ustawioną siłę dmuchnięcia
    JLabel aktualnaMocDmuchnieciaLBL;
    int wymaganaMocDmuchniecia; // zmienna przechowująca siłę dmuchnięcia potrzebną do ukończenia poziomu
    int poziomTrudnosci;
    int punkty;
    int punktyZaPoziom = 0;
    static boolean czyRosnie = false;   // zmienna określająca czy wskaźnik czasu dmuchnięcia rośnie
    boolean czyWygrano = false;     // zmienna przyjmująca wartość true w mimencie ukończenia poziomu
    String plik;    // zmiennia określająca ścieżkę do pliku
    int aktualnyPoziom;

    JLabel poziomLBL;
    JLabel zakonczGreLBL;
    JLabel czasDoKoncaLBL;
    JLabel punktyLBL;
    JLabel dynamit;
    JLabel ogien;
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

        init();
        wsk = new WskaznikDmuchniecia();
        add(wsk);
        pgg = new PasekGryGora();
        add(pgg);
        pgd = new PasekGryDol();
        add(pgd);

        ustawTlo(plik);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    } // koniec konstruktora

    /**
     * w tej metodzie ustawiane jest tło aplikacji
     * @param plik - ścieżka do pliku
     */

    public void ustawTlo(String plik)
    {
        try {
            JLabel tlo = new JLabel(new ImageIcon(plik));
            tlo.setOpaque(false);
            tlo.setBounds(0, 0, Main.SZEROKOSC, Main.WYSOKOSC);
            add(tlo);
        } catch (Exception e)
        {
            System.out.println("Blad" + e);
        }
    } // koniec ustawTlo

    /**
     * funkcja wyrysowująca początkową zawartość okna, tutaj jest losowane położenie dynamitu
     */

    public void init()
    {
        Font font = new Font("Helvetica", Font.BOLD, 30);

        dynamit = new JLabel(new ImageIcon("image//dynamitOFF.png"));
        dynamit.setSize(60,120);
        dynamit.setLocation(losujPolozenie(Main.SZEROKOSC,Main.WYSOKOSC));
        dynamit.addMouseListener(new DynamitKlik());
        add(dynamit);

        poziomLBL = new JLabel("Poziom " + aktualnyPoziom);
        poziomLBL.setSize(200,40);
        poziomLBL.setLocation(50,0);
        poziomLBL.setForeground(Color.yellow);
        poziomLBL.setFont(font);
        add(poziomLBL);

        ogien = new JLabel();
        ogien.setSize(200,550);
        ogien.setLocation(600,200);
        add(ogien);

        zakonczGreLBL = new JLabel("Zakończ grę");
        zakonczGreLBL.setSize(200,40);
        zakonczGreLBL.setLocation(120,650);
        zakonczGreLBL.setForeground(Color.yellow);
        zakonczGreLBL.setFont(font);
        zakonczGreLBL.addMouseListener(new ZakonczGreKlik());
        add(zakonczGreLBL);

        czasDoKoncaLBL = new JLabel();
        czasDoKoncaLBL.setSize(500,50);
        czasDoKoncaLBL.setLocation(500, 0);
        czasDoKoncaLBL.setFont(font);
        czasDoKoncaLBL.setForeground(Color.yellow);
        czasDoKoncaLBL.setText("Pozostały czas: " + pozostalyCzasNaPoziom);
        add(czasDoKoncaLBL);

        punktyLBL = new JLabel();
        punktyLBL.setSize(200,50);
        punktyLBL.setLocation(950,0);
        punktyLBL.setForeground(Color.yellow);
        punktyLBL.setFont(font);
        punktyLBL.setText("Punkty: " + punkty);
        add(punktyLBL);

        aktualnaMocDmuchnieciaLBL = new JLabel();
        aktualnaMocDmuchnieciaLBL.setSize(500,40);
        aktualnaMocDmuchnieciaLBL.setLocation(700,650);
        aktualnaMocDmuchnieciaLBL.setFont(font);
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

            if (sprawdzWynik() && sprawdzMocDmuchniecia())  // sprawdzenie czy gracz wygrał po zwolnieniu przycisku myszki
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

            if (czasZapaleniaDynamitu>0)  // dekrementuje, co sekundę zmienną odliczająca czas do następnego poziomu
            {
                czasZapaleniaDynamitu--;
            }

            switch (czasZapaleniaDynamitu) // zmiana wyświetlanego obrazu w czasie - na razie brak animacji poza zapaleniem dynamitu
            {
                case 0:
                    przejdzDoNastepnegoPoziomu();
                    czasDoNastepnegoPoziomu.stop();
            }
        }
    } // koniec LiczCzasDoNasteonegoPoziomu

    /**
     * metoda odpowiadająca za zmianę wyświetlanego dynamitu (na zapalony)
     */

    private void zapalDynamit()
    {
        ImageIcon icon = new ImageIcon("image//dynamit.png");
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

            g.drawRect(prosX, prosY - 1 , prosSzer, prosWys);

            if(potrzebnyCzasDmuchniecia + blad >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - blad <= aktualnyCzasDmuchniecia)
            { // zamalowanie wskaźnika na zielono i ustawienie najwyższego ognia przy uzyskaniu potrzebnego czasu dmuchnięcia
                g.setColor(Color.green);
                ogien.setLocation(500,219);
                ogien.setSize(200,431);
                ogien.setIcon(new ImageIcon("image//ogien3.png"));
            }
            else if (potrzebnyCzasDmuchniecia - 20 <= aktualnyCzasDmuchniecia && aktualnyCzasDmuchniecia < potrzebnyCzasDmuchniecia)
            { // zamalowanie wskaźnika na pomarańczowo i ustawienie średniego ognia przy zbliżeniu się do potrzebnego czasu dmuchnięcia
                g.setColor((Color.orange));
                if (czyRosnie)
                {
                    ogien.setLocation(533,397);
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
                    ogien.setLocation(570,527);
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


