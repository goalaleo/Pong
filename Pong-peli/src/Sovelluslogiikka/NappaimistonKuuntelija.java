package Sovelluslogiikka;

import domain.Pelaaja;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * NappaimistonKuuntelija tarkkailee pelaajien syötteitä.
 *
 */
public class NappaimistonKuuntelija implements KeyListener {

    /**
     * @see Pelaaja
     */
    private Pelaaja pelaaja1;
    /**
     * @see Pelaaja
     */
    private Pelaaja pelaaja2;

    /**
     * @see Sovelluslogiikka.Pong
     */
    /**
     * Konstruktori, jossa luokka saa pong-oliolta pelaajat.
     *
     * @param pelaaja1 vasemmanpuoleinen pelaaja
     * @param pelaaja2 oikeanpuoleinen pelaaja
     */
    public NappaimistonKuuntelija(Pelaaja pelaaja1, Pelaaja pelaaja2) {
        this.pelaaja1 = pelaaja1;
        this.pelaaja2 = pelaaja2;
    }

    /**
     * Tyhja metodi, joka ei tee mitaan.
     */
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    /**
     * Tarkkailee kayttajien tekemia nappainten painalluksia.
     *
     * Metodi asettaa Pelaaja-luokan ilmentyman oliomuuttujan 'nopeus' arvoksi
     * joko -1 tai 1. Pelaaja-luokan siirry()-metodi tarkkailee tata arvoa ja
     * valitsee siten liikkeen suunnan.
     *
     * @param e nappain, jota on painettu.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pelaaja2.setNopeus(-1);
            //
            System.out.println("Pelaajan2 suunta: YLÖS");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pelaaja2.setNopeus(1);
            //
            System.out.println("Pelaajan2 suunta: ALAS");
        }
        if (e.getKeyChar() == 'e') {
            pelaaja1.setNopeus(-1);
            System.out.println("Pelaajan1 suunta: YLÖS");
        } else if (e.getKeyChar() == 'd') {
            pelaaja1.setNopeus(1);
            //
            System.out.println("Pelaajan1 suunta: ALAS");
        }

    }

    /**
     * Tarkkailee millon kayttajat vapauttavat painetut napit.
     *
     * Kun nappia ei enaa paineta pohjassa, asettaa metodi oliomuuttujan
     * 'nopeus' arvoksi 0.
     *
     * @see
     * Sovelluslogiikka.NappaimistonKuuntelija#keyPressed(java.awt.event.KeyEvent)
     * @param e nappain, joka vapautettiin.
     */
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
