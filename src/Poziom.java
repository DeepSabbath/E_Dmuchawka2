import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Created by Amadeusz on 26.12.2015.
 */
public class Poziom extends JFrame implements KeyListener
{

    static final int WYSOKOSC = 800;
    static final int SZEROKOSC = 1280;
    static final int maksymalnyCzasDmuchniecia = 100;
    final int blad = 8;

    javax.swing.Timer timer1 = new javax.swing.Timer(300, new czasDmuchniecia());

    int czasNaPoziom;
    int pozostalyCzasNaPoziom;
    int potrzebnyCzasDmuchniecia;
    JLabel potrzebnyCzasDmuchnieciaLBL;
    int aktualnyCzasDmuchniecia;
    JLabel aktualnyCzasDmuchnieciaLBL;
    int aktualnaMocDmuchniecia;
    JLabel aktualnaMocDmuchnieciaLBL;
    int wymaganaMocDmuchniecia;
    JLabel wymaganaMocDmuchnieciaLBL;
    int poziomTrudnosci;
    boolean czyRosnie = false;

    JLabel wynikGry;

    JLabel dynamit;
    WskaznikDmuchniecia wsk;

    public Poziom ()
    {

    }

    public Poziom (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia)
    {
        super("Smok Wawelski");

        System.out.println(czyRosnie);

        this.poziomTrudnosci = poziomTrudnosci;
        this.wymaganaMocDmuchniecia = wymaganaMocDmuchniecia;
        this.potrzebnyCzasDmuchniecia = potrzebnyCzasDmuchniecia;
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

        timer1.start();
    }

   /*public void paint (Graphics g)
    {
        int prosX = 1050;
        int prosY = 450;
        int prosSzer = 25;
        int prosWys = 200;
        double prosWysD = prosWys;
        int wypelnienie = (int)((aktualnyCzasDmuchniecia/maksymalnyCzasDmuchniecia)*prosWysD);
        g.drawRect(prosX,prosY-1,prosSzer,prosWys+1);
        if(potrzebnyCzasDmuchniecia + blad >= aktualnyCzasDmuchniecia && potrzebnyCzasDmuchniecia - blad <= aktualnyCzasDmuchniecia)
        {
            g.setColor(Color.green);
        }
        else if (potrzebnyCzasDmuchniecia - 20 <= aktualnyCzasDmuchniecia && aktualnyCzasDmuchniecia < potrzebnyCzasDmuchniecia)
        {
            g.setColor((Color.orange));
        }
        else {
            g.setColor(Color.red);
        }
        g.fillRect(prosX+1,(prosY+prosWys-wypelnienie),prosSzer-1,wypelnienie);
    }*/

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
            System.out.println(czyRosnie);
        }

        public void mouseReleased(MouseEvent e) {
            czyRosnie = false;
            System.out.println(czyRosnie);
            if (sprawdzWynik() && sprawdzMocDmuchniecia()) {
                wynikGry.setText("Wygrales");
                dynamit.setIcon(new ImageIcon("image//dynamit.png"));
            }
            else
            {
                aktualnyCzasDmuchniecia = 0;
                wynikGry.setText("Przegrales");
            }
        }
    }

    private boolean sprawdzMocDmuchniecia()
    {
        if (aktualnaMocDmuchniecia == wymaganaMocDmuchniecia)
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
                System.out.println(czyRosnie);
                System.out.println("czas "+aktualnyCzasDmuchniecia);
            }
        }
    }


    public class WskaznikDmuchniecia extends JPanel {

        public WskaznikDmuchniecia()
        {
            setBounds(850, 450, 500, 210);

        }

        public void paint (Graphics g)
        {
            int prosX = 0;
            int prosY = 1;
            int prosSzer = 25;
            int prosWys = 200;
            double prosWysD = prosWys;
            int wypelnienie = (int)((aktualnyCzasDmuchniecia/maksymalnyCzasDmuchniecia)*prosWysD);
            g.drawRect(prosX,prosY - 1 ,prosSzer,prosWys + 2);
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
            g.fillRect(prosX + 1,(prosY + prosWys-wypelnienie),prosSzer - 1,wypelnienie);

        }
    }
}

