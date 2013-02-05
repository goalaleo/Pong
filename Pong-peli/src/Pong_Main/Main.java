package Pong_Main;

import Grafiikka.Kayttoliittyma;
import domain.Pallo;
import domain.Pelaaja;
import javax.swing.SwingUtilities;
/**
 *Main-luokka, joka ajettaessa luo Kayttoliittyman
 * ja ajaa sen
 */
public class Main {

    /**
     *Luo ja ajaa Kayttoliittyman 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Kayttoliittyma());
    }
}
