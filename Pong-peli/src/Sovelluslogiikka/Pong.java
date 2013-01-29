package Sovelluslogiikka;

import Grafiikka.Piirtoalusta;
import domain.Pallo;
import domain.Pelaaja;
import javax.swing.Timer;

public class Pong implements Runnable {
    
    private Piirtoalusta piirtoalusta;
    
    public Pong(Piirtoalusta piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
    }
    
    @Override
    public void run() {
        while (true) {
            
            
            siirraPalloa(piirtoalusta.getPallo(), piirtoalusta.getPelaaja1(), piirtoalusta.getPelaaja2());
            siirraPelaajaa(piirtoalusta.getPelaaja1());
            siirraPelaajaa(piirtoalusta.getPelaaja2());
            
            
            piirtoalusta.repaint();
            try {
                Thread.sleep(2000 / 60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void siirraPelaajaa(Pelaaja pelaaja) {
        for (int i = 0; i < Math.abs(pelaaja.getNopeus()); i++) {
            pelaaja.siirry();
        }
    }
    
    private void siirraPalloa(Pallo pallo, Pelaaja pelaaja1, Pelaaja pelaaja2) {
        if ((pallo.getY() + pallo.getNopeusY()) > pelaaja2.getY()
                && (pallo.getY() + pallo.getNopeusY()) < (pelaaja2.getY() + 128)
                && pallo.getX() + pallo.getNopeusX() + 20 > pelaaja2.getX()+ 32) {
            pallo.setNopeusX(-1 * pallo.getNopeusX());
            pallo.setX(570);
        }
        if (pallo.getY() <= 0
                || pallo.getY() >= 430) {
            pallo.setNopeusY(-1 * pallo.getNopeusY());
        }
        pallo.setX(pallo.getX() + pallo.getNopeusX());
        pallo.setY(pallo.getY() + pallo.getNopeusY());
        
    }
}
