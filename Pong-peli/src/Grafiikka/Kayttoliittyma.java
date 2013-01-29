package Grafiikka;

import Grafiikka.Piirtoalusta;
import Sovelluslogiikka.NappaimistonKuuntelija;
import Sovelluslogiikka.Pong;
import domain.Pallo;
import domain.Pelaaja;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private final int LEVEYS = 640;
    private final int KORKEUS = 480;
    private Pallo pallo;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    private Piirtoalusta piirtoalusta;
    private Pong pong;

    public Kayttoliittyma(Pallo pallo, Pelaaja pelaaja1, Pelaaja pelaaja2) {
        this.pallo = pallo;
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }

    @Override
    public void run() {
        frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setPreferredSize(new Dimension(LEVEYS, KORKEUS));

        luoKomponentit(frame.getContentPane());

        
        
        frame.pack();
        frame.setVisible(true);
        this.pong = new Pong(piirtoalusta);
        // tähän new thread pong
        new Thread(pong).start();
    }

    private void luoKomponentit(Container container) {
        Piirtoalusta piirtoalusta = new Piirtoalusta(pallo, pelaaja1, pelaaja2);
        this.piirtoalusta = piirtoalusta;
        container.add(piirtoalusta);

        frame.addKeyListener(new NappaimistonKuuntelija(pelaaja1, pelaaja2));
        
    }

    public JFrame getFrame() {
        return frame;
    }
}
