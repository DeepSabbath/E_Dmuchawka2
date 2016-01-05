import javax.swing.*;

/**
 * <b>OknoGlowne</b> - klasa definiujące główne parametry okna aplikacji
 * @Author Amadeusz Kardasz
 */
public class OknoGlowne extends JFrame {

    /**
     * konstruktor klasy ustawia odpowiednie wartości parametrów
     */

    public OknoGlowne(int szerokosc, int wysokosc, int xSrodek, int ySrodek)
    {
        super("Smok wawelski");
        setLayout(null);
        setUndecorated(true);
        setSize(szerokosc, wysokosc);
        setLocation(xSrodek, ySrodek);
        setResizable(false);
        setVisible(true);
        EkranStartowy e = new EkranStartowy(szerokosc, wysokosc);
        e.setFocusable(true);
        add(e);
        repaint();
    }
}
