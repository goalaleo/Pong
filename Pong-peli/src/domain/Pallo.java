package domain;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Pallo-luokka sailyttaa pallon koordinaatit, seka X- ja Y-suuntaisen nopeuden
 * suunnan(ylos, alas, oikealle, vasemmalle).
 */
public class Pallo {

    /**
     * pallon X-koordinaatti
     */
    private int paikkaX;
    /**
     * pallon Y-koordinaatti
     */
    private int paikkaY;
    /**
     * pallon X-suuntainen nopeus
     */
    private int nopeusX;
    /**
     * pallon Y-suuntainen nopeus
     */
    private int nopeusY;

    /**
     * Konstruktori, jossa annetaan pallon alkupaikka ja nopeus.
     *
     * @param paikkaX pallon X-koordinaatti
     * @param paikkaY pallon Y-koordinaatti
     * @param nopeus pallon nopeus. Huomioi, etta pallon nopeudella on 2
     * erisuuntaista komponenttia X ja Y, mutta nopeus annetaan pallon luonnin
     * yhteydessa silti yhtena parametrina. Tama johtuuu siita, etta nopeuden
     * komponentit ovat koko pelin yhta suuret, eli pallo liikkuu 45 asteen
     * kulmassa vaakatasoon nahden. Kun pallo luodaan se lahtee siis
     * ensimmaisella kerralla oikealle ja alas AINA. Pallo vaihtaa
     * aloitussuuntaa jokaisen maalin jalkeen.
     */
    public Pallo(int paikkaX, int paikkaY, int nopeus) {
        this.paikkaX = paikkaX;
        this.paikkaY = paikkaY;
        this.nopeusX = nopeus;
        this.nopeusY = nopeus;
    }

    /**
     * Muuttaa pallon X- ja Y-koordinaatteja yhdella(pikselilla) nopeuden X- ja
     * Y-komponenttien mukaiseen suuntaan.
     *
     * @see domain.Pelaaja#siirry()
     */
    public void siirry() {
        if (nopeusY < 0) {
            paikkaY -= 1;
        } else if (nopeusY > 0) {
            paikkaY += 1;
        }
        if (nopeusX < 0) {
            paikkaX -= 1;
        }
        if (nopeusX > 0) {
            paikkaX += 1;
        }
    }

    public int getX() {
        return paikkaX;
    }

    public int getY() {
        return paikkaY;
    }

    public int getNopeusX() {
        return nopeusX;
    }

    public int getNopeusY() {
        return nopeusY;
    }

    public void setNopeusX(int uusiNopeusX) {
        nopeusX = uusiNopeusX;
    }

    public void setNopeusY(int uusiNopeusY) {
        nopeusY = uusiNopeusY;
    }

    /**
     * Maarittelee milta 'pallo' nayttaa.
     *
     * piirtoalusta kutsuu piirra()-metodia kun se paivitetaan.
     *
     * @param graphics Graphics-luokan ilmentyma
     * @see Graphics
     */
    public void piirra(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(paikkaX, paikkaY, 20, 20);
    }

    public void setX(int paikkaX) {
        this.paikkaX = paikkaX;
    }

    public void setY(int paikkaY) {
        this.paikkaY = paikkaY;
    }
}
