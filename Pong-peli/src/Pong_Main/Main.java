package Pong_Main;

import Grafiikka.Kayttoliittyma;
import domain.Pallo;
import domain.Pelaaja;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Pelaaja pelaaja1 = new Pelaaja(20, 165);
        Pelaaja pelaaja2 = new Pelaaja(580, 165);
        int PallonNopeusX = 3;
        int PallonNopeusY = -5;
        Pallo pallo = new Pallo(60, 140, PallonNopeusX, PallonNopeusY);


        SwingUtilities.invokeLater(new Kayttoliittyma(pallo, pelaaja1, pelaaja2));
        // TODO code application logic here
    }
}
