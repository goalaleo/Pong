package Sovelluslogiikka;

import Grafiikka.Piirtoalusta;
import domain.Pallo;
import domain.Pelaaja;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NappaimistonKuuntelija implements KeyListener {

    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
  

    public NappaimistonKuuntelija(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pelaaja2.setNopeus(-5);
            //
            System.out.println("Pelaajan2 suunta: YLÖS");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pelaaja2.setNopeus(5);
            //
            System.out.println("Pelaajan2 suunta: ALAS");
        }
        if (e.getKeyChar() == 'e') {
            pelaaja1.setNopeus(-5);
            System.out.println("Pelaajan1 suunta: YLÖS");
        } else if (e.getKeyChar() == 'd') {
            pelaaja1.setNopeus(5);
            //
            System.out.println("Pelaajan1 suunta: ALAS");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            pelaaja2.setNopeus(0);
            //
            System.out.println("Pelaaja2 PAIKALLAAN");
        }
        if (e.getKeyChar() == 'e' || e.getKeyChar() == 'd') {
            pelaaja1.setNopeus(0);
            System.out.println("Pelaaja1 PAIKALLAAN");
        }
    }
}
