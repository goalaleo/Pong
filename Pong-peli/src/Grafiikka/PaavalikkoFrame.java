package Grafiikka;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 * Tama ikkuna tulee nakyviin ohjelman alussa (ns Paavalikko).
 *
 */
public class PaavalikkoFrame extends JInternalFrame {

    /**
     * paavalikon New Game- nappi
     */
    private JButton newGame;
    /**
     * paavalikon Match History- nappi
     */
    private JButton matchHistory;
    /**
     * paavalikon Quit- nappi, joka sulkee ohjelman
     */
    private JButton quit;
    /**
     * paavalikon paneeli, johon napit lisataan
     */
    private JPanel buttonPanel;

    /**
     * Luokan PaavalikkoFrame konstruktori.
     *
     * Luokka perii JInternalFrame-luokan, sen ilmentyma on pieni ikkuna
     * PaaFramen sisalla, jota ei voi liikuttaa eika sen kokoa voi muuttaa.
     */
    public PaavalikkoFrame() {
        super("", false, false, false, false);

        this.buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setPreferredSize(new Dimension(300, 100));

        this.newGame = new JButton("New Game");
        buttonPanel.add(newGame).setPreferredSize(new Dimension(300, 100));

        this.matchHistory = new JButton("Match History");
        buttonPanel.add(matchHistory).setPreferredSize(new Dimension(300, 100));

        this.quit = new JButton("Quit");
        buttonPanel.add(quit).setPreferredSize(new Dimension(300, 100));

        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }

        this.add(buttonPanel);
        this.setPreferredSize(new Dimension(300, 100));
        this.pack();
        this.setLocation(180, 200);


    }

    public JButton getNewGameNappi() {
        return newGame;
    }

    public JButton getMatchHistoryNappi() {
        return matchHistory;
    }

    public JButton getQuitNappi() {
        return quit;
    }
}
