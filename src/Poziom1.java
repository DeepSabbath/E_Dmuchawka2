import java.awt.event.KeyEvent;

/**
 * Created by Amadeusz on 26.12.2015.
 */
public class Poziom1 extends Poziom {

    //int punkty = 0;

    public  Poziom1 (int poziomTrudnosci, int wymaganaMocDmuchniecia, int potrzebnyCzasDmuchniecia, int czasNaPoziom)
    {
        super(poziomTrudnosci,  wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom);
    }

    public void wygrana()
    {
        liczPunkty();

        switch (poziomTrudnosci)
        {
            case 1:
                wymaganaMocDmuchniecia = 40;
                potrzebnyCzasDmuchniecia = 50;
                czasNaPoziom = 25;
                break;
            case 2 :
                wymaganaMocDmuchniecia = 60;
                potrzebnyCzasDmuchniecia = 65;
                czasNaPoziom = 19;
                break;
            case 3 :
                wymaganaMocDmuchniecia = 80;
                potrzebnyCzasDmuchniecia = 75;
                czasNaPoziom = 16;
                break;
        }
        removeAll();
        Poziom2 p2 = new Poziom2(poziomTrudnosci, wymaganaMocDmuchniecia,  potrzebnyCzasDmuchniecia,  czasNaPoziom, punkty);
        add(p2);
        repaint();
        p2.requestFocusInWindow();
    }
}
