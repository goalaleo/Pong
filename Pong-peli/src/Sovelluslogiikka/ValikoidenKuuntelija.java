package Sovelluslogiikka;

import Grafiikka.PaavalikkoFrame;
import Grafiikka.PelinAsetusFrame;
import Grafiikka.Tulostaulu;
import Grafiikka.VirheellinenSyoteilmoitus;
import Grafiikka.Voittaja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * implements ActionListener, tehtavana kuunnella kaikkien taulujen toimintaa,
 * ja kontrolloida mm pelin aloitusta, valikkotaulujen nakyvyytta, syotteiden
 * lukemista yms.
 *
 */
public class ValikoidenKuuntelija implements ActionListener {

    /**
     * paavalikon ikkuna
     */
    private PaavalikkoFrame paavalikko;
    /**
     * pelinAsetuksien ikkuna
     */
    private PelinAsetusFrame pelinAsetukset;
    /**
     * Tulostaulupaneeli
     */
    private Tulostaulu tulostaulu;
    /**
     * Pelihistorian ikkuna ja tallettava olio
     */
    private MatchHistory peliHistoria;
    /**
     * Voittajanilmoittaja- ikkuna
     */
    private Voittaja voittaja;
    /**
     * Pong- luokan ilmentyma
     */
    private Pong pong;
    /**
     * Paaikkuna
     */
    private JFrame frame;
    /**
     * Virheellisen syotteen ikkuna
     */
    private VirheellinenSyoteilmoitus virheellinenSyoteilmoitus;
    /**
     * Virheellisen syotteen ikkunaan sijoitettava teksti
     */
    private String virheTeksti;

    /**
     * ValikoidenKuuntelija-luokan konstruktori
     *
     * @param paavalikko pelin paavalikko
     * @param pelinAsetukset valikkotaulu johon syotetaan asetukset
     * @param virheellinenSyoteilmoitus virheilmoitustaulu
     * @param tulostaulu pelin tulostaulu
     * @param pong pong peli, jonka aloitusta ja lopetusta ValikoidenKuuntelija
     * koontrolloi
     * @param frame ohjelman paaframe.
     */
    public ValikoidenKuuntelija(PaavalikkoFrame paavalikko, PelinAsetusFrame pelinAsetukset, VirheellinenSyoteilmoitus virheellinenSyoteilmoitus, Tulostaulu tulostaulu, Pong pong, JFrame frame, MatchHistory peliHistoria, Voittaja voittaja) {
        this.paavalikko = paavalikko;
        this.pelinAsetukset = pelinAsetukset;
        this.virheellinenSyoteilmoitus = virheellinenSyoteilmoitus;
        this.tulostaulu = tulostaulu;
        this.peliHistoria = peliHistoria;
        this.voittaja = voittaja;
        this.pong = pong;
        this.frame = frame;
        this.virheTeksti = "";
    }

    /**
     * Metodi maarittelee toiminnan kayttajan syotteen perusteella.
     *
     * @param ae on tehty ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        painettiinkoNewGame(ae);
        painettiinkoBack(ae);
        painettiinkoPlay(ae);
        painettiinkoVirheilmoituksenOk(ae);
        painettiinkoBackToMenu(ae);
        painettiinkoQuit(ae);
        painettiinkoClose(ae);
        painettiinkoMatchHistory(ae);
        painettiinkoVoittajanOk(ae);
    }

    /**
     * Tarkistaa onko pelin asetukset/tiedot syotetty sallitussa muodossa.
     *
     * @return true, jos tiedot on syotetty oikein - false, jos tiedoissa on
     * virheita
     */
    private boolean AsetuksetSyotettyOikein() {

        if (nimienPituudetOikein() && mihinPelataanSallittuArvo()) {
            return true;
        } else if (nimienPituudetOikein() == false && mihinPelataanSallittuArvo() == true) {
            virheTeksti = "The names must be 1-10 characters long!\n";
        } else if (nimienPituudetOikein() == true && mihinPelataanSallittuArvo() == false) {
            virheTeksti = "Winning score must be a number between 1 and 20!";
        } else {
            virheTeksti = "<html>The names must be 1-10 characters long!<br>Winning score must be a number between 1 and 20!</html>";
        }

        return false;
    }

    /**
     * Tarkistaa ovatko syotetyt nimet oikean pituisia.
     *
     * @return true, jos ovat - false, jos eivat ole
     */
    private boolean nimienPituudetOikein() {
        String pelaajan1Nimi = pelinAsetukset.getPelaajan1NimiKentta().getText();
        String pelaajan2Nimi = pelinAsetukset.getPelaajan2NimiKentta().getText();

        if (pelaajan1Nimi.length() >= 1
                && pelaajan1Nimi.length() <= 10
                && pelaajan2Nimi.length() >= 1
                && pelaajan2Nimi.length() <= 10) {
            return true;
        }
        return false;
    }

    /**
     * Tarkistaa onko syotetty voittava pistemaara sallittu arvo.
     *
     * @return true, jos on - false, jos ei ole
     */
    private boolean mihinPelataanSallittuArvo() {
        int mihinPelataan;

        try {
            mihinPelataan = Integer.parseInt(pelinAsetukset.getMihinPelataanKentta().getText());
        } catch (Exception e) {
            return false;
        }
        if (mihinPelataan >= 1 && mihinPelataan <= 20) {
            return true;
        }
        return false;
    }

    /**
     * Metodi siirtaa pelaajan syotteet pelinAsetukset-valikon tekstikentista
     * pong-peliin, ja asettaa oikeat taulut ja paneelit nakyviin.
     */
    private void valmisteleKayttoliittymaPelinAloitustaVarten() {
        pong.setPelaajan1Nimi(pelinAsetukset.getPelaajan1NimiKentta().getText());
        pong.setPelaajan2Nimi(pelinAsetukset.getPelaajan2NimiKentta().getText());
        pong.setMihinPelataan(Integer.parseInt(pelinAsetukset.getMihinPelataanKentta().getText()));
        tulostaulu.asetaTulostauluunAlkutiedot();
        pelinAsetukset.setVisible(false);
        pelinAsetukset.nollaaKentat();
        tulostaulu.setVisible(true);
        frame.requestFocus();
    }

    /**
     * Nollaa pelin paattymisen ja keskeytyksen yhteydessa pong-pelista tiedot,
     * ja palauttaa pelin alkutilaan.
     */
    private void nollaaPongPeli() {
        pong.nollaaPongPeli();
    }

    /**
     * Tarkistaa painettiinko New Game- nappia, ja tekee tarvittavat
     * toimenpiteet
     *
     * @param ae tehty ActionEvent
     */
    private void painettiinkoNewGame(ActionEvent ae) {
        if (ae.getSource() == paavalikko.getNewGameNappi()) {
            paavalikko.setVisible(false);
            pelinAsetukset.setVisible(true);
            pelinAsetukset.requestFocus();
        }
    }

    /**
     * Tarkistaa painettiinko Back- nappia, ja tekee tarvittavat toimenpiteet
     *
     * @param ae tehty ActionEvent
     */
    private void painettiinkoBack(ActionEvent ae) {
        if (ae.getSource() == pelinAsetukset.getTakaisinNappi()) {
            pelinAsetukset.setVisible(false);
            pelinAsetukset.nollaaKentat();
            paavalikko.setVisible(true);
            paavalikko.requestFocus();
        }
    }

    /**
     * Tarkistaa painettiinko Play- nappia, ja tekee tarvittavat toimenpiteet
     *
     * @param ae tehty ActionEvent
     */
    private void painettiinkoPlay(ActionEvent ae) {
        if (ae.getSource() == pelinAsetukset.getAloitaPeliNappi()) {
            if (AsetuksetSyotettyOikein()) {
                valmisteleKayttoliittymaPelinAloitustaVarten();

                new Thread(pong).start();

            } else {
                pelinAsetukset.setVisible(false);
                virheellinenSyoteilmoitus.setVirheilmoitus(virheTeksti);
                virheellinenSyoteilmoitus.setVisible(true);
                virheellinenSyoteilmoitus.requestFocus();
            }

        }
    }

    /**
     * Tarkistaa painettiinko Ok- nappia, ja tekee tarvittavat toimenpiteet
     *
     * @param ae tehty ActionEvent
     */
    private void painettiinkoVirheilmoituksenOk(ActionEvent ae) {
        if (ae.getSource() == virheellinenSyoteilmoitus.getOkNappi()) {
            virheellinenSyoteilmoitus.setVisible(false);
            virheTeksti = "";
            virheellinenSyoteilmoitus.setVirheilmoitus("");
            pelinAsetukset.setVisible(true);
            pelinAsetukset.requestFocus();
        }
    }

    /**
     * Tarkistaa painettiinko Back to Menu- nappia, ja tekee tarvittavat
     * toimenpiteet
     *
     * @param ae tehty ActionEvent
     */
    private void painettiinkoBackToMenu(ActionEvent ae) {
        if (ae.getSource() == tulostaulu.getMenuNappi()) {
            pong.stop();
            nollaaPongPeli();
            tulostaulu.setVisible(false);
            paavalikko.setVisible(true);
            paavalikko.requestFocus();
        }
    }

    /**
     * Tarkistaa painettiinko Quit- nappia, ja sulkee ohjelman.
     *
     * @param ae tehty ActionEvent
     */
    private void painettiinkoQuit(ActionEvent ae) {
        if (ae.getSource() == paavalikko.getQuitNappi()) {
            System.exit(0);
        }
    }

    /**
     * Tarkistaa painettiinko peliHistoria-ikkunan Close- nappia, ja tekee
     * tarvittavat toimenpiteet
     *
     * @param ae tehty ActionEvent
     */
    private void painettiinkoClose(ActionEvent ae) {
        if (ae.getSource() == peliHistoria.getCloseNappi()) {
            peliHistoria.piilotaPeliHistoria();
        }
    }

    /**
     * Tarkistaa painettiinko paavalikon Match History- nappia, ja tekee
     * tarvittavat toimenpiteet
     *
     * @param ae tehty ActionEvent
     */
    private void painettiinkoMatchHistory(ActionEvent ae) {
        if (ae.getSource() == paavalikko.getMatchHistoryNappi()) {
            peliHistoria.naytaPeliHistoria();
        }
    }

    /**
     * Tarkistaa painettiinko voittajan ilmoittavan ikkunan Ok- nappia, ja tekee
     * tarvittavat toimenpiteet
     *
     * @param ae tehty ActionEvent
     */
    private void painettiinkoVoittajanOk(ActionEvent ae) {
        if (ae.getSource() == voittaja.getVoittoilmoituksenOkNappi()) {
            voittaja.setVisible(false);
            paavalikko.setVisible(true);
        }
    }
}
