import javax.swing.*;

/**
 * Created by Amadeusz on 27.12.2015.
 */
public class OknoGlowne extends JFrame {


    public OknoGlowne(int width, int height)
    {
        super("Smok wawelski");
        setLayout(null);
        setUndecorated(true);
        setSize(width, height);
        setResizable(false);
        setVisible(true);
        EkranStartowy e = new EkranStartowy(Main.SZEROKOSC, Main.WYSOKOSC);
        e.setFocusable(true);
        add(e);
        repaint();
    }

}
