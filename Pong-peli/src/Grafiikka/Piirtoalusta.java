package Grafiikka;

import Sovelluslogiikka.Pong;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Piirtoalusta vastaa pelin graafikan piirtamisesta. Perii luokan JPanel
 *
 * @see JPanel
 */
public class Piirtoalusta extends JPanel {

    /**
     * @see Grafiikka.Kayttoliittyma#pong
     *
     */
    private Pong pong;

    /**
     * Konstruktori asettaa taustan varin mustaksi ja oliomuuttujan arvoksi
     * Pong-luokan ilmentyman.
     *
     * @param pong Pong-luokan ilmentyma, jolta piirtoalusta kutsuu piirrettavia
     * komponentteja.
     */
    public Piirtoalusta(Pong pong) {
        super.setBackground(Color.BLACK);
        this.pong = pong;

    }

    /**
     * Piirtaa graafiset komponentit eli Pelaajat ja pallon ikkunaan.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        pong.getPallo().piirra(graphics);
        pong.getPelaaja1().piirra(graphics);
        pong.getPelaaja2().piirra(graphics);
    }
}
