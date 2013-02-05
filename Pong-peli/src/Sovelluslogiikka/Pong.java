package Sovelluslogiikka;

import Grafiikka.Piirtoalusta;
import domain.Pallo;
import domain.Pelaaja;

public class Pong implements Runnable {

    private Piirtoalusta piirtoalusta;
    private int nopeus;
    private Pallo pallo;
    private Pelaaja pelaaja1;
    private Pelaaja pelaaja2;
    private int pallonOsumatPelaajiin;
    private int pelaajan1Pisteet;
    private int pelaajan2Pisteet;

    public Pong() {
        this.nopeus = 5;
        this.pallo = new Pallo(320, 215, nopeus);
        this.pelaaja1 = new Pelaaja(20, 165);
        this.pelaaja2 = new Pelaaja(580, 165);
        this.pallonOsumatPelaajiin = 1;
        this.pelaajan1Pisteet = 0;
        this.pelaajan2Pisteet = 0;
    }

    @Override
    public void run() {

        odota(2000);

        while (true) {
            if (palloMeniMaaliin()) {
                palautaAlkutilaan();
            }

            liikutaKomponentteja();

            piirtoalusta.repaint();
            odota(1000 / 60);

            nopeutetaankoPelia();
        }
    }

    private void liikutaKomponentteja() {
        for (int i = 0; i < nopeus; i++) {
            if (palloTormaaSeinaan()) {
                pallo.setNopeusY(-pallo.getNopeusY());
                piirtoalusta.repaint();
            }
            if (palloTormaaPelaajaan()) {
                pallo.setNopeusX(-pallo.getNopeusX());
                piirtoalusta.repaint();
            }
            pelaaja1.siirry();
            pelaaja2.siirry();
            pallo.siirry();

        }
    }

    private boolean palloTormaaSeinaan() {
        if (pallo.getY() == 0 || pallo.getY() == 430) {
            return true;
        }
        return false;
    }

    private boolean palloTormaaPelaajaan() {
        //pallo tormaa pelaajaan2
        if (pallo.getX() == 560 && pallo.getY() > pelaaja2.getY() - 7 && pallo.getY() < (pelaaja2.getY() + 135)) {
            pallonOsumatPelaajiin += 1;
            return true;
        }
        //pallo tormaa pelaajaan1 
        if (pallo.getX() == 30 && pallo.getY() > pelaaja1.getY() - 7 && pallo.getY() < (pelaaja1.getY() + 135)) {
            pallonOsumatPelaajiin += 1;
            return true;
        }
        return false;
    }

    public Pallo getPallo() {
        return pallo;
    }

    public Pelaaja getPelaaja1() {
        return pelaaja1;
    }

    public Pelaaja getPelaaja2() {
        return pelaaja2;
    }

    public int getNopeus() {
        return nopeus;
    }

    public int getPelaaja1Pisteet() {
        return pelaajan1Pisteet;
    }

    public int getPelaaja2Pisteet() {
        return pelaajan2Pisteet;
    }

    public void setPiirtoalusta(Piirtoalusta piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
    }

    private boolean palloMeniMaaliin() {
        if (pallo.getX() < 0) {
            pelaajan2Pisteet++;
            return true;
        } else if (pallo.getX() > 640) {
            pelaajan1Pisteet++;
            return true;
        }
        return false;
    }

    private void palautaAlkutilaan() {
        this.nopeus = 5;
        this.pallonOsumatPelaajiin = 1;
        pallo.setX(320);
        pallo.setY(215);
        pallo.setNopeusY(nopeus);
        if ((pelaajan1Pisteet + pelaajan2Pisteet) % 2 != 0) {
            pallo.setNopeusX(-nopeus);
        } else {
            pallo.setNopeusX(nopeus);
        }

        pelaaja1.setPaikkaX(20);
        pelaaja1.setPaikkaY(165);

        pelaaja2.setPaikkaX(580);
        pelaaja2.setPaikkaY(165);


        piirtoalusta.repaint();
        System.out.println("1. :" + pelaajan1Pisteet + ", " + "2. :" + pelaajan2Pisteet);
        odota(2000);
    }

    private void nopeutetaankoPelia() {
        if (pallonOsumatPelaajiin % 6 == 0) {
            nopeus++;
            pallonOsumatPelaajiin = 1;
        }
    }

    private void odota(int millisekunteja) {
        try {
            Thread.sleep(millisekunteja);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
