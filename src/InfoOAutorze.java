import javax.swing.*;
import java.awt.*;

/**
 * Created by Amadeusz on 26.12.2015.
 */
public class InfoOAutorze extends JDialog{

    JLabel oAutorze;

    InfoOAutorze ()
    {
        setLayout(null);
        setBounds(150, 70, 600, 600);
        setModal(true);
        setResizable(false);
        init();
        setVisible(true);
    }

    public void init()
    {
        Font font = new Font("Helvetica", Font.BOLD, 20);

        String tekst = "Tworca gry jest Amadeusz Kardasz.";
        oAutorze= new JLabel(tekst);
        oAutorze.setSize(500,200);
        oAutorze.setAlignmentX(10);
        oAutorze.setAlignmentY(10);
        oAutorze.setLocation(100,100);
        oAutorze.setForeground(Color.black);
        oAutorze.setFont(font);
        add(oAutorze);
    }
}
