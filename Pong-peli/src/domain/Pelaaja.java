package domain;

import java.awt.Color;
import java.awt.Graphics;

public class Pelaaja {

    private int paikkaX;
    private int paikkaY;
    private int nopeus;

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
    
    public int getNopeus(){
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

    public void siirry() {
        if (nopeus < 0 && paikkaY > 0) {
            paikkaY -= 1;
        } else if (nopeus > 0 && paikkaY < 326) {
            paikkaY += 1;
        }
    }

    public void piirra(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(paikkaX, paikkaY, 10, 128);
    }
}
