/**
 * Created by Amadeusz on 26.12.2015.
 */
public class Main {

    static final int WYSOKOSC = 800;
    static final int SZEROKOSC = 1280;
    public static OknoGlowne o;

    public static void main(String[] args) {

        o = new OknoGlowne(SZEROKOSC,WYSOKOSC);
        o.setDefaultCloseOperation( o.EXIT_ON_CLOSE );
    }
}
