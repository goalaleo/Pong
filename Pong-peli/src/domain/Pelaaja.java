package domain;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Pelissa on 2 Pelaaja-luokan ilmentymaa: pelaaja1 ja pelaaja2.
 *
 * Luokka sailyttaa pelaajien koordinaatit ja nopeuden suunnan (+:alas,-:ylos).
 *
 */
public class Pelaaja {

    /**
     * Pelaajan X-koordinaatti.
     */
    private int paikkaX;
    /**
     * Pelaajan Y-koordinaatti
     */
    private int paikkaY;
    /**
     * Nopeuden suunta.
     */
    private int nopeus;

    /**
     * Konstruktori, jolle annetaan pelaajan alkupaikka.
     *
     * @param paikkaX X-koordinaatti
     * @param paikkaY Y-koordinaatti
     */
    public Pelaaja(int paikkaX, int paikkaY) {
        this.paikkaX = paikkaX;
        this.paikkaY = paikkaY;
        this.nopeus = 0;
    }

    public int getX() {
        return paikkaX;
    }

    public int getY() {
        return paikkaY;
    }

    public int getNopeus() {
        return nopeus;
    }

    public void setPaikkaX(int uusiPaikkaX) {
        this.paikkaX = uusiPaikkaX;
    }

    public void setPaikkaY(int uusiPaikkaY) {
        this.paikkaY = uusiPaikkaY;
    }

    public void setNopeus(int nopeus) {
        this.nopeus = nopeus;
    }

    /**
     * Muuttaa pelaajan Y-Koordinaattia yhdella(pikselilla) nopeuden suunnasta
     * riippuen. Jos nopeus on negatiivinen(-1), niin Y-koordinaatista
     * vahennetaan 1. Jos nopeus on positiivinen niin siihen lisataan 1. Jos
     * nopeus on 0, niin ei tapahdu mitaan.
     *
     * @see Sovelluslogiikka.NappaimistonKuuntelija
     */
    public void siirry() {
        if (nopeus < 0 && paikkaY > 0) {
            paikkaY -= 1;
        } else if (nopeus > 0 && paikkaY < 326) {
            paikkaY += 1;
        }
    }

    /**
     * Maarittelee milta pelaajan kontrolloima 'maila' nayttaa. Piirtoalusta
     * kutsuu piirra()- metodia kun se paivitetaan.
     *
     * @param graphics Graphics luokan ilmentyma.
     * @see Graphics
     */
    public void piirra(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(paikkaX, paikkaY, 10, 128);
    }
}
