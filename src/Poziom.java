import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Created by Amadeusz on 26.12.2015.
 */
public class Poziom extends JPanel implements KeyListener
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

    JLabel wynikGry;

    JLabel czasDoKoncaLBL;
    JLabel punktyLBL;
    JLabel dynamit;
    WskaznikDmuchniecia wsk;

    public Poziom (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom)
    {
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

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);


    }

    public void testLabel()
    {
        potrzebnyCzasDmuchnieciaLBL = new JLabel();
        potrzebnyCzasDmuchnieciaLBL.setSize(200,40);
        potrzebnyCzasDmuchnieciaLBL.setLocation(50,100);
        potrzebnyCzasDmuchnieciaLBL.setText("Potrzebny czas dmuchniecia" + potrzebnyCzasDmuchniecia);
        add(potrzebnyCzasDmuchnieciaLBL);

        aktualnyCzasDmuchnieciaLBL = new JLabel();
        aktualnyCzasDmuchnieciaLBL.setSize(200,40);
        aktualnyCzasDmuchnieciaLBL.setLocation(250,100);
        aktualnyCzasDmuchnieciaLBL.setText("Aktualny czas dmuchniecia" + aktualnyCzasDmuchniecia);
        add(aktualnyCzasDmuchnieciaLBL);

        wymaganaMocDmuchnieciaLBL = new JLabel();
        wymaganaMocDmuchnieciaLBL.setSize(200,40);
        wymaganaMocDmuchnieciaLBL.setLocation(50,200);
        wymaganaMocDmuchnieciaLBL.setText("WYmagana moc dmuchniecia" + wymaganaMocDmuchniecia);
        add(wymaganaMocDmuchnieciaLBL);

        aktualnaMocDmuchnieciaLBL = new JLabel();
        aktualnaMocDmuchnieciaLBL.setSize(200,40);
        aktualnaMocDmuchnieciaLBL.setLocation(250,200);
        aktualnaMocDmuchnieciaLBL.setText("Aktualna moc dmuchniecia" + aktualnaMocDmuchniecia);
        add(aktualnaMocDmuchnieciaLBL);

        wynikGry = new JLabel();
        wynikGry.setSize(200,40);
        wynikGry.setLocation(50,300);
        wynikGry.setText("Wynik gry");
        add(wynikGry);
    }

    public void init()
    {
        dynamit = new JLabel(new ImageIcon("image//dynamitOFF.png"));
        dynamit.setSize(60,120);
        dynamit.setLocation(losujPolozenie(1280,800));
        dynamit.setOpaque(false);
        dynamit.addMouseListener(new DynamitClick());
        add(dynamit);

        czasDoKoncaLBL = new JLabel();
        czasDoKoncaLBL.setSize(200,60);
        czasDoKoncaLBL.setLocation(500, 100);
        czasDoKoncaLBL.setText("Pozostaly czas: " + pozostalyCzasNaPoziom);
        add(czasDoKoncaLBL);

        punktyLBL = new JLabel();
        punktyLBL.setSize(200,60);
        punktyLBL.setLocation(800,100);
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
        int a = e.getKeyCode();
        System.out.println(a);
    }

    public void keyPressed(KeyEvent e) {
        int a = e.getKeyCode();

        if (a == KeyEvent.VK_UP)
        {
            aktualnaMocDmuchniecia++;
        }

        if (a == KeyEvent.VK_DOWN)
        {
            aktualnaMocDmuchniecia--;
        }
        aktualnaMocDmuchnieciaLBL.setText("Aktualna moc dmuchniecia " + aktualnaMocDmuchniecia);
    }

    class DynamitClick extends MouseAdapter
    {
        public void mousePressed(MouseEvent e) {
            czyRosnie = true;
        }

        public void mouseReleased(MouseEvent e) {
            czyRosnie = false;
            if (sprawdzWynik() && sprawdzMocDmuchniecia()) {
                wynikGry.setText("Wygrales");
                czyWygrano = true;
                dynamit.setIcon(new ImageIcon("image//dynamit.png"));
                wygrana();
                System.out.println("Ounkty: " + punkty);
            }
            else
            {
                aktualnyCzasDmuchniecia = 0;
                wynikGry.setText("Przegrales");
                przegrana();
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

            if (pozostalyCzasNaPoziom == 0 && czyWygrano)
            {
                przegrana();
            }
        }
    }

    public void przegrana()
    {
        liczPunkty();
    }

    public void wygrana()
    {
        liczPunkty();
    }

    public void liczPunkty()
    {
        if (czyWygrano)
        {
            punktyZaPoziom = 300 + pozostalyCzasNaPoziom * poziomTrudnosci * 10;
        }

        punkty +=  punktyZaPoziom;
        punktyLBL.setText("Punkty: " + punkty);
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


