package Grafiikka;

import domain.Pallo;
import domain.Pelaaja;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Piirtoalusta extends JPanel {

    private Pallo pallo;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;

    public Piirtoalusta(Pallo pallo, Pelaaja pelaaja1, Pelaaja pelaaja2) {
        super.setBackground(Color.BLACK);
        this.pallo = pallo;
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        pallo.piirra(graphics);
        pelaaja1.piirra(graphics);
        pelaaja2.piirra(graphics);
    }

    public Pelaaja getPelaaja1() {
        return pelaaja1;
    }

    public Pelaaja getPelaaja2() {
        return pelaaja2;
    }

    public Pallo getPallo() {
        return pallo;
    }
}
