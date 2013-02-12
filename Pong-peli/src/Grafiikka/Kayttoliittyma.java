package Grafiikka;

import Grafiikka.Piirtoalusta;
import Sovelluslogiikka.NappaimistonKuuntelija;
import Sovelluslogiikka.Pong;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Maarittelee ja luo visuaaliset komponentit
 */
public class Kayttoliittyma implements Runnable {

    /**
     * Ikkuna, joka sisaltaa visuaaliset komponentit
     */
    private JFrame frame;
    /**
     * Ikkunan leveys
     *
     * @see Grafiikka.Kayttoliittyma#frame
     */
    private final int LEVEYS = 640;
    /**
     * Ikkunan korkeus
     *
     * @see Grafiikka.Kayttoliittyma#frame
     */
    private final int KORKEUS = 480;
    /*
     * @see Grafiikka.Piirtoalusta
     */
    private Piirtoalusta piirtoalusta;
    /**
     * @see Sovelluslogiikka.Pong
     */
    private Pong pong;

    public Kayttoliittyma() {
    }

    /**
     * Metodin suorittaminen johtaa pelin komponenttien luomiseen
     *
     * Metodi luo ensin uuden ikkunan nimeltä "Pong", ja asettaa sille
     * sulkuoperaation. Ikkunan koko maaritellaan ja asetetaan muuttumattomaksi.
     * Taman jalkeen metodi luo Pong-luokan ilmentyman, joka asetetaan alustetun
     * oliomuuttujan arvoksi.
     *
     * Tämän jälkeen suoritetaan luoKomponentit()- metodi
     *
     * Viimeiseksi ikkuna asetetaan nakyvaksi, ja aloitetaan uusi lanka, jolle
     * annetaan parametrina oliomuuttuja pong.
     *
     * @see Grafiikka.Kayttoliittyma#luoKomponentit(java.awt.Container)
     * @see Sovelluslogiikka.Pong#Pong()
     *
     *
     */
    @Override
    public void run() {
        frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setPreferredSize(new Dimension(LEVEYS, KORKEUS));

        this.pong = new Pong();

        luoKomponentit(frame.getContentPane());

        pong.setPiirtoalusta(piirtoalusta);

        frame.pack();
        frame.setVisible(true);

        new Thread(pong).start();
    }

    /**
     * Luo piirtoalustan ja asettaa sen oliomuuttujan arvoksi. Taman jalkeen
     * lisaa piirtoalustan containeriin, ja luo seka lisaa
     * Nappaimistonkuuntelijan ikkunaan
     *
     * @param container framen sisaltama Container-luokan ilmentyma
     *
     * @see Grafiikka.Piirtoalusta
     * @see Grafiikka.Kayttoliittyma#frame
     * @see Container
     * @see Sovelluslogiikka.NappaimistonKuuntelija
     * @see KeyListener
     */
    private void luoKomponentit(Container container) {
        Piirtoalusta piirtoalusta = new Piirtoalusta(pong);
        this.piirtoalusta = piirtoalusta;
        container.add(piirtoalusta);

        frame.addKeyListener(new NappaimistonKuuntelija(pong.getPelaaja1(), pong.getPelaaja2()));

    }

    public JFrame getFrame() {
        return frame;
    }
}
