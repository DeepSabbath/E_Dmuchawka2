import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Created by Amadeusz on 26.12.2015.
 */
public abstract class Poziom extends JPanel implements KeyListener
{

    static final int WYSOKOSC = 800;
    static final int SZEROKOSC = 1280;
    static final double maksymalnyCzasDmuchniecia = 100;
    final int blad = 8;

    javax.swing.Timer timer1 = new javax.swing.Timer(50, new czasDmuchniecia());
    javax.swing.Timer czasDoKoncaTimer = new javax.swing.Timer(1000, new liczCzasDoKonca());

    int czasNaPoziom;
    int pozostalyCzasNaPoziom;
    int potrzebnyCzasDmuchniecia;
    JLabel potrzebnyCzasDmuchnieciaLBL;
    double aktualnyCzasDmuchniecia;
    JLabel aktualnyCzasDmuchnieciaLBL;
    int aktualnaMocDmuchniecia;
    JLabel aktualnaMocDmuchnieciaLBL;
    int wymaganaMocDmuchniecia;
    JLabel wymaganaMocDmuchnieciaLBL;
    int poziomTrudnosci;
    int punkty;
    int punktyZaPoziom = 0;
    boolean czyRosnie = false;
    boolean czyWygrano = false;
    String plik;

    JLabel wynikGry;

    JLabel czasDoKoncaLBL;
    JLabel punktyLBL;
    JLabel dynamit;
    WskaznikDmuchniecia wsk;

    public Poziom (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom, String plik)
    {
        this.plik = plik;
        this.poziomTrudnosci = poziomTrudnosci;
        this.wymaganaMocDmuchniecia = wymaganaMocDmuchniecia;
        this.potrzebnyCzasDmuchniecia = potrzebnyCzasDmuchniecia;
        this.czasNaPoziom = czasNaPoziom;
        setLayout(null);
        setSize(SZEROKOSC,WYSOKOSC);

        init();
        testLabel();
        wsk = new WskaznikDmuchniecia();
        add(wsk);
        ustawTlo(plik);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    }

    public void ustawTlo(String plik)
    {
        try {
            JLabel tlo = new JLabel(new ImageIcon(plik));
            tlo.setOpaque(false);
            tlo.setBounds(0, 0, 1280, 800);
            add(tlo);
        } catch (Exception e)
        {
            System.out.println("Blad" + e);
        }
    }

    public void testLabel()
    {
        potrzebnyCzasDmuchnieciaLBL = new JLabel();
        potrzebnyCzasDmuchnieciaLBL.setSize(200,40);
        potrzebnyCzasDmuchnieciaLBL.setLocation(50,100);
        potrzebnyCzasDmuchnieciaLBL.setForeground(Color.yellow);
        potrzebnyCzasDmuchnieciaLBL.setText("Potrzebny czas dmuchniecia" + potrzebnyCzasDmuchniecia);
        add(potrzebnyCzasDmuchnieciaLBL);

        aktualnyCzasDmuchnieciaLBL = new JLabel();
        aktualnyCzasDmuchnieciaLBL.setSize(200,40);
        aktualnyCzasDmuchnieciaLBL.setLocation(250,100);
        aktualnyCzasDmuchnieciaLBL.setForeground(Color.yellow);
        aktualnyCzasDmuchnieciaLBL.setText("Aktualny czas dmuchniecia" + aktualnyCzasDmuchniecia);
        add(aktualnyCzasDmuchnieciaLBL);

        wymaganaMocDmuchnieciaLBL = new JLabel();
        wymaganaMocDmuchnieciaLBL.setSize(200,40);
        wymaganaMocDmuchnieciaLBL.setLocation(50,200);
        wymaganaMocDmuchnieciaLBL.setForeground(Color.yellow);
        wymaganaMocDmuchnieciaLBL.setText("WYmagana moc dmuchniecia" + wymaganaMocDmuchniecia);
        add(wymaganaMocDmuchnieciaLBL);

        aktualnaMocDmuchnieciaLBL = new JLabel();
        aktualnaMocDmuchnieciaLBL.setSize(200,40);
        aktualnaMocDmuchnieciaLBL.setLocation(250,200);
        aktualnaMocDmuchnieciaLBL.setForeground(Color.yellow);
        aktualnaMocDmuchnieciaLBL.setText("Aktualna moc dmuchniecia" + aktualnaMocDmuchniecia);
        add(aktualnaMocDmuchnieciaLBL);

        wynikGry = new JLabel();
        wynikGry.setSize(200,40);
        wynikGry.setLocation(50,300);
        wynikGry.setForeground(Color.yellow);
        wynikGry.setText("Wynik gry");
        add(wynikGry);
    }

    public void init()
    {
        dynamit = new JLabel(new ImageIcon("image//dynamitOFF.png"));
        dynamit.setSize(60,120);
        dynamit.setLocation(losujPolozenie(1280,800));
        dynamit.setForeground(Color.yellow);
        dynamit.addMouseListener(new DynamitClick());
        add(dynamit);

        czasDoKoncaLBL = new JLabel();
        czasDoKoncaLBL.setSize(200,60);
        czasDoKoncaLBL.setLocation(500, 100);
        czasDoKoncaLBL.setForeground(Color.yellow);
        czasDoKoncaLBL.setText("Pozostaly czas: " + pozostalyCzasNaPoziom);
        add(czasDoKoncaLBL);

        punktyLBL = new JLabel();
        punktyLBL.setSize(200,60);
        punktyLBL.setLocation(800,100);
        punktyLBL.setForeground(Color.yellow);
        punktyLBL.setText("Punkty: " + punkty);
        add(punktyLBL);

        pozostalyCzasNaPoziom = czasNaPoziom;
        timer1.start();
        czasDoKoncaTimer.start();
    }

    public Point losujPolozenie(int width, int height)
    {
        Dimension d = dynamit.getSize();
        int w = d.height;
        int s = d.width;
        Random rand = new Random();
        int sze = rand.nextInt(width - s);
        int wys = rand.nextInt(height - 2*w);
        Point pol = new Point(sze, wys);
        return pol;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int a = e.getKeyCode();

        if (!czyRosnie) {
            if (a == KeyEvent.VK_UP) {
                aktualnaMocDmuchniecia++;
            }

            if (a == KeyEvent.VK_DOWN) {
                aktualnaMocDmuchniecia--;
            }
            aktualnaMocDmuchnieciaLBL.setText("Aktualna moc dmuchniecia " + aktualnaMocDmuchniecia);
        }
    }

    class DynamitClick extends MouseAdapter
    {
        public void mousePressed(MouseEvent e) {
            czyRosnie = true;
        }

        public void mouseReleased(MouseEvent e) {

            czyRosnie = false;

            if (sprawdzWynik() && sprawdzMocDmuchniecia())
            {
                wygrana();
            }
            else
            {
                aktualnyCzasDmuchniecia = 0;
            }
        }
    }

    private boolean sprawdzMocDmuchniecia()
    {
        if (aktualnaMocDmuchniecia >= wymaganaMocDmuchniecia)
            return  true;
        else
            return  false;
    }

    private boolean sprawdzWynik()
    {
        if(potrzebnyCzasDmuchniecia + blad >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - blad <= aktualnyCzasDmuchniecia)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private class czasDmuchniecia implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(czyRosnie && aktualnyCzasDmuchniecia < maksymalnyCzasDmuchniecia)
            {
                aktualnyCzasDmuchniecia++;
                aktualnyCzasDmuchnieciaLBL.setText("Aktualny czas dmuchniecia " + aktualnyCzasDmuchniecia);
                repaint();
            }
        }
    }

    private class liczCzasDoKonca implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {

            pozostalyCzasNaPoziom--;
            czasDoKoncaLBL.setText("Pozostaly czas: " + pozostalyCzasNaPoziom);

            if (pozostalyCzasNaPoziom == 0 && !czyWygrano)
            {
                zakonczenieGry();
            }
        }
    }

    public void zakonczenieGry()
    {
        opoznij(500);
        removeAll();
        KoniecGry kg = new KoniecGry(punkty, poziomTrudnosci);
        add(kg);
        repaint();
        kg.requestFocusInWindow();

    }

    public abstract void wygrana();

    public void liczPunkty()
    {
        if (czyWygrano)
        {
            punktyZaPoziom = (50 * poziomTrudnosci) + (pozostalyCzasNaPoziom * poziomTrudnosci) * 5;
        }

        punkty +=  punktyZaPoziom;
        punktyLBL.setText("Punkty: " + punkty);
    }

    public  void opoznij (int opoznienie)
    {
        try {
            Thread.sleep(opoznienie);
        }

        catch ( InterruptedException e) {

            System.out.println("np. zostalem obudzony przedwczesnie");
        }
    }


    public class WskaznikDmuchniecia extends JPanel {

        int prosX = 0;
        int prosY = 1;
        int prosSzer = 25;
        int prosWys = 200;
        double prosWysD = prosWys;
        int wypelnienie;

        public WskaznikDmuchniecia()
        {
            setBounds(1050, 450, 100, 204);
        }

        public void paint (Graphics g)
        {
            wypelnienie = (int)((aktualnyCzasDmuchniecia/maksymalnyCzasDmuchniecia)*prosWysD);

            g.drawRect(prosX, prosY - 1 , prosSzer, prosWys);

            if(potrzebnyCzasDmuchniecia + blad >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - blad <= aktualnyCzasDmuchniecia)
            {
                g.setColor(Color.green);
            }
            else if (potrzebnyCzasDmuchniecia - 20 <= aktualnyCzasDmuchniecia && aktualnyCzasDmuchniecia < potrzebnyCzasDmuchniecia)
            {
                g.setColor((Color.orange));
            }
            else
            {
                g.setColor(Color.red);
            }
            g.fillRect(prosX + 1,(prosY + prosWys - wypelnienie),prosSzer - 1 , wypelnienie - 1);
        }
    }
}


