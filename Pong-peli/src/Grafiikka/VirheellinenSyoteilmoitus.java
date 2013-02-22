/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafiikka;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Tama ikkuna tulee nakyviin, jos kayttajan syote on virheellinen.
 *
 */
public class VirheellinenSyoteilmoitus extends JInternalFrame {

    /**
     * virheilmoitustaulun nappi, jolla se sulkeutuu
     */
    private JButton ok;
    /**
     * paneeli joka sisaltaa virheilmoituksen komponentit
     */
    private JPanel virhePanel;
    /**
     * virheilmoituksen tekstikentta
     */
    private JLabel virheilmoitus;

    /**
     * Luokan VirheellinenSyoteIlmoitus konstruktori.
     *
     * Luokka perii JInternalFrame-luokan, sen ilmentyma on pieni ikkuna
     * PaaFramen sisalla, jota ei voi liikuttaa eika sen kokoa voi muuttaa.
     */
    public VirheellinenSyoteilmoitus() {
        super("", false, false, false, false);

        this.virhePanel = new JPanel(new GridLayout(2, 1));
        virhePanel.setPreferredSize(new Dimension(400, 100));

        this.virheilmoitus = new JLabel("");
        virhePanel.add(virheilmoitus);

        this.ok = new JButton("Ok");
        virhePanel.add(ok);

        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }

        this.add(virhePanel);
        this.setPreferredSize(new Dimension(320, 100));
        this.pack();
        this.setLocation(170, 220);
    }

    /**
     * Asettaa virheilmoituksen tekstin.
     *
     * @param virheilmoitus on String muotoinen virheilmoitus, jonka tyypin
     * maaraa ValikoidenKuuntelija
     */
    public void setVirheilmoitus(String virheilmoitus) {
        this.virheilmoitus.setText(virheilmoitus);
    }

    public JButton getOkNappi() {
        return ok;
    }
}
