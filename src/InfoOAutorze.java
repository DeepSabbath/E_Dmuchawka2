import javax.swing.*;
import java.awt.*;

/**
 * <b>InfoOAutorze</b>Definicja klasy wyświetlającej dane o autorze
 * @Author Amadeusz Kardasz
 */
public class InfoOAutorze extends JDialog{

    JTextArea oAutorze;

    InfoOAutorze ()
    {
        setLayout(null);
        setBounds(150, 70, 600, 600);
        setModal(true);
        setResizable(false);
        init();
        setVisible(true);
    } // koniec konstruktora

    /**
     * funkcja wyrysowująca początkową zawartość okna
     */

    public void init()
    {
        Font font = new Font("Helvetica", Font.BOLD, 20);

        String tekst = "Twórcą gry jest Amadeusz Kardasz.";
        oAutorze= new JTextArea(tekst);
        oAutorze.setSize(500,200);
        oAutorze.setLocation(100,100);
        oAutorze.setOpaque(false);
        oAutorze.setForeground(Color.black);
        oAutorze.setFont(font);
        add(oAutorze);
    } // init
}
