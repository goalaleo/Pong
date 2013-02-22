package Grafiikka;

import Sovelluslogiikka.Pong;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Tulostaulu perii luokan JPanel ja sijaitsee paaikkunan ylalaidassa pelin
 * aikana nayttaen pelin tilanteen.
 *
 */
public class Tulostaulu extends JPanel {

    /**
     * pong- peli, jota tulostaulu seuraa
     */
    private Pong pong;
    /**
     * kohta johon pelaajan 1 nimi tulee
     */
    private JLabel pelaaja1;
    /**
     * kohta johon pelaajan 2 nimi tulee
     */
    private JLabel pelaaja2;
    /**
     * kohta johon tulee voittava pistetilanne
     */
    private JLabel mihinPelataan;
    /**
     * tulostaulun nappi, josta paasee takaisin paavalikkoon (lopettaa
     * meneillaan olevan pelin)
     */
    private JButton menu;
    /**
     * pelaajan 1 pistetilanne
     */
    private int pelaajan1Pisteet;
    /**
     * pelaajan 2 pistetilanne
     */
    private int pelaajan2Pisteet;

    /**
     * Tulostaulu- luokan konstruktori
     *
     * @param pong pong-peli, jolta tulostaulu kysyy tilannetta
     */
    public Tulostaulu(Pong pong) {
        this.pong = pong;

        this.pelaajan1Pisteet = 0;
        this.pelaajan2Pisteet = 0;

        this.setLayout(new GridLayout(1, 4));

        this.pelaaja1 = new JLabel("");
        this.add(pelaaja1);

        this.pelaaja2 = new JLabel("");
        this.add(pelaaja2);

        this.mihinPelataan = new JLabel("");
        this.add(mihinPelataan);

        this.menu = new JButton("Back to Menu");
        this.add(menu);
    }

    /**
     * Asettaa tulostauluun syotetyt pelaajien nimet ja pisteiksi 0.
     */
    public void asetaTulostauluunAlkutiedot() {
        pelaaja1.setText(pong.getPelaaja1Nimi() + ": 0");
        pelaaja2.setText(pong.getPelaaja2Nimi() + ": 0");
        mihinPelataan.setText("Winning Score: " + pong.getMihinPelataan());
    }

    /**
     * Paivittaa tulostauluun pelin pistetilanteen
     */
    public void paivitaPisteet() {
        pelaaja1.setText(pong.getPelaaja1Nimi() + ": " + pong.getPelaaja1Pisteet());
        pelaaja2.setText(pong.getPelaaja2Nimi() + ": " + pong.getPelaaja2Pisteet());

    }

    public JButton getMenuNappi() {
        return menu;
    }
}
