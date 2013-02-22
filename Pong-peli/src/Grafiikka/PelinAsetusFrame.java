package Grafiikka;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Tahan ikkunaan syotetaan pelin tiedot New Game- napin painalluksen jalkeen.
 *
 * Tietoihin kuuluu pelaajien nimet sekä mihin pelataan( eli voittava
 * pistemäärä)
 *
 */
public class PelinAsetusFrame extends JInternalFrame {

    /**
     * Paneeli johon pelin tietojen syottoon tarvittavat elementit on lisatty
     */
    private JPanel pelinTiedot;
    /**
     * PelinAsetusFramen Play -nappi
     */
    private JButton aloitaPeli;
    /**
     * PelinAsetusFramen Back -nappi
     */
    private JButton takaisin;
    /**
     * Kentta johon pelaajan 1 nimi syotetaan
     */
    private JTextField pelaajan1NimiKentta;
    /**
     * Kentta johon pelaajan 2 nimi syotetaan
     */
    private JTextField pelaajan2NimiKentta;
    /**
     * Kentta johon syotetaan voittava pistemaara
     */
    private JTextField mihinPelataanKentta;

    /**
     * Luokan PelinAsetusFrame konstruktori.
     *
     * Luokka perii JInternalFrame-luokan, sen ilmentyma on pieni ikkuna
     * PaaFramen sisalla, jota ei voi liikuttaa eika sen kokoa voi muuttaa.
     */
    public PelinAsetusFrame() {
        super("", false, false, false, false);
        pelinTiedot = new JPanel(new GridLayout(4, 2));
        pelinTiedot.setPreferredSize(new Dimension(300, 100));

        JLabel pelaajan1Nimi = new JLabel("Player 1 name:");
        pelinTiedot.add(pelaajan1Nimi);
        this.pelaajan1NimiKentta = new JTextField();
        pelinTiedot.add(pelaajan1NimiKentta);

        JLabel pelaajan2Nimi = new JLabel("Player 2 name:");
        pelinTiedot.add(pelaajan2Nimi);
        this.pelaajan2NimiKentta = new JTextField();
        pelinTiedot.add(pelaajan2NimiKentta);

        JLabel mihinPelataanLabel = new JLabel("Winning Score [ 1-20] ?:");
        pelinTiedot.add(mihinPelataanLabel);
        this.mihinPelataanKentta = new JTextField();
        pelinTiedot.add(mihinPelataanKentta);

        this.takaisin = new JButton("Back");
        pelinTiedot.add(takaisin);

        this.aloitaPeli = new JButton("Play!");
        pelinTiedot.add(aloitaPeli);

        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }

        this.add(pelinTiedot);
        this.setPreferredSize(new Dimension(350, 120));
        this.pack();
        this.setLocation(155, 200);
    }

    public JButton getAloitaPeliNappi() {
        return aloitaPeli;
    }

    public JButton getTakaisinNappi() {
        return takaisin;
    }

    public JTextField getPelaajan1NimiKentta() {
        return pelaajan1NimiKentta;
    }

    public JTextField getPelaajan2NimiKentta() {
        return pelaajan2NimiKentta;
    }

    public JTextField getMihinPelataanKentta() {
        return mihinPelataanKentta;
    }

    /**
     * Tyhjentaa tekstikentat ikkunasta, kun liikutaan takaisin paavalikkoon,
     * tai aloitetaan uusi peli.
     */
    public void nollaaKentat() {
        this.pelaajan1NimiKentta.setText("");
        this.pelaajan2NimiKentta.setText("");
        this.mihinPelataanKentta.setText("");
    }
}
