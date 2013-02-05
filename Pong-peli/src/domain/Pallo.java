package domain;

import java.awt.Color;
import java.awt.Graphics;

public class Pallo {

    private int paikkaX;
    private int paikkaY;
    private int nopeusX;
    private int nopeusY;

    public Pallo(int paikkaX, int paikkaY, int nopeus) {
        this.paikkaX = paikkaX;
        this.paikkaY = paikkaY;
        this.nopeusX = nopeus;
        this.nopeusY = nopeus;
    }

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
