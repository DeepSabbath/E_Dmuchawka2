import javax.swing.*;
import java.awt.*;

/**
 * <b>InfoOAutorze</b>Definicja klasy wyświetlającej dane o autorze
 * @author Amadeusz Kardasz
 */
public class InfoOAutorze extends JDialog{

    /** przechowuje informacje o autorze gry*/
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
        String tekst = "Twórcą gry jest Amadeusz Kardasz," +'\n' +  "student 3 roku inżynierii biomedycznej" +'\n' +
        "na Politechnice Gdańskiej";
        oAutorze= new JTextArea(tekst);
        oAutorze.setSize(500,200);
        oAutorze.setLocation(100,100);
        oAutorze.setOpaque(false);
        oAutorze.setForeground(Color.black);
        oAutorze.setFont(Main.ustawCzcionke(20));
        add(oAutorze);
    } // init
}
