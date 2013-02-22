package Grafiikka;

import Sovelluslogiikka.Pong;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ikkuna joka nayttaa pelin voittajan
 *
 */
public class Voittaja extends JInternalFrame {

    /**
     * voittajataulun sulkeva Ok -nappi
     */
    private JButton ok;
    /**
     * voittajataulun paneeli, johon taulun elementit lisataan
     */
    private JPanel voittajaPanel;
    /**
     * Label, jossa on voittoteksti
     */
    private JLabel voittoteksti;
    /**
     * pong-luokan ilmentyma, jolta luokka kysyy voittajan
     */
    private Pong pong;

    /**
     * Voittaja-luokan konstruktori
     *
     * @param pong peli, josta luokka saa voittajan pelin paatyttya.
     */
    public Voittaja(Pong pong) {


        super("", false, false, false, false);

        this.pong = pong;

        this.voittajaPanel = new JPanel(new GridLayout(2, 1));
        voittajaPanel.setPreferredSize(new Dimension(400, 100));

        this.voittoteksti = new JLabel("");
        voittajaPanel.add(voittoteksti);

        this.ok = new JButton("Ok");
        voittajaPanel.add(ok);

        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }

        this.add(voittajaPanel);
        this.setPreferredSize(new Dimension(320, 100));
        this.pack();
        this.setLocation(170, 220);
    }

    /**
     * Asettaa voittoteksin voittaneen pelaajan mukaan
     *
     * @param voittanutPelaaja
     */
    public void setVoittoteksti(String voittanutPelaaja) {
        this.voittoteksti.setText(voittanutPelaaja + " has won The Game!");
    }

    public JButton getVoittoilmoituksenOkNappi() {
        return ok;
    }
}
