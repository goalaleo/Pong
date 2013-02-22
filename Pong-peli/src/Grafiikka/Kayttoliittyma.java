package Grafiikka;

import Sovelluslogiikka.MatchHistory;
import Sovelluslogiikka.NappaimistonKuuntelija;
import Sovelluslogiikka.Pong;
import Sovelluslogiikka.ValikoidenKuuntelija;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Maarittelee ja luo visuaaliset komponentit
 */
public class Kayttoliittyma implements Runnable {

    /**
     * Ikkuna, joka sisaltaa visuaaliset komponentit
     */
    private JFrame frame;
    /**
     * Ikkunan leveys
     *
     * @see Grafiikka.Kayttoliittyma#frame
     */
    private final int LEVEYS = 640;
    /**
     * Ikkunan korkeus
     *
     * @see Grafiikka.Kayttoliittyma#frame
     */
    private final int KORKEUS = 500;
    /**
     * @see Grafiikka.Piirtoalusta
     */
    private Piirtoalusta piirtoalusta;
    /**
     * @see Sovelluslogiikka.Pong
     */
    private Pong pong;

    /**
     * Kayttoliittyman konstruktori
     */
    public Kayttoliittyma() {
    }

    /**
     * Metodin suorittaminen johtaa pelin komponenttien luomiseen
     *
     */
    @Override
    public void run() {

        frame = new JFrame("Pong");
        this.pong = new Pong();
        Tulostaulu tulostaulu = new Tulostaulu(pong);
        Voittaja voittaja = new Voittaja(pong);
        MatchHistory peliHistoria = new MatchHistory();
        luoValikot(tulostaulu, peliHistoria, voittaja);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setPreferredSize(new Dimension(LEVEYS, KORKEUS));

        luoPerusKomponentit(frame.getContentPane(), tulostaulu);

        pong.setPiirtoalusta(piirtoalusta);
        pong.setTulostaulu(tulostaulu);
        pong.setPeliHistoria(peliHistoria);
        pong.setVoittajaFrame(voittaja);

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Luo piirtoalustan ja asettaa sen oliomuuttujan arvoksi. Taman jalkeen
     * lisaa piirtoalustan containeriin, ja luo seka lisaa
     * Nappaimistonkuuntelijan ikkunaan
     *
     * @param container framen sisaltama Container-luokan ilmentyma
     *
     * @see Grafiikka.Piirtoalusta
     * @see Grafiikka.Kayttoliittyma#frame
     * @see Container
     * @see Sovelluslogiikka.NappaimistonKuuntelija
     * @see KeyListener
     */
    private void luoPerusKomponentit(Container container, Tulostaulu tulostaulu) {
        this.piirtoalusta = new Piirtoalusta(pong);
        container.add(piirtoalusta);
        container.add(tulostaulu, BorderLayout.NORTH);
        frame.addKeyListener(new NappaimistonKuuntelija(pong.getPelaaja1(), pong.getPelaaja2()));

    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Tama metodi luo kaikki Kayttoliittyman valikkotaulut, ja lisaa ne
     * frameen.
     *
     * @param tulostaulu pelissa kaytettava tulostaulu.
     */
    private void luoValikot(Tulostaulu tulostaulu, MatchHistory peliHistoria, Voittaja voittaja) {
        PaavalikkoFrame paavalikko = new PaavalikkoFrame();
        PelinAsetusFrame pelinAsetukset = new PelinAsetusFrame();
        VirheellinenSyoteilmoitus virheellinenSyoteilmoitus = new VirheellinenSyoteilmoitus();
        ValikoidenKuuntelija valikoidenKuuntelija = new ValikoidenKuuntelija(paavalikko, pelinAsetukset, virheellinenSyoteilmoitus, tulostaulu, pong, frame, peliHistoria, voittaja);

        lisaaValikoidenNapeilleKuuntelija(paavalikko, pelinAsetukset, valikoidenKuuntelija, virheellinenSyoteilmoitus, tulostaulu, peliHistoria, voittaja);

        pelinAsetukset.setVisible(false);
        virheellinenSyoteilmoitus.setVisible(false);
        paavalikko.setVisible(true);
        tulostaulu.setVisible(false);
        frame.add(voittaja);
        frame.add(pelinAsetukset);
        frame.add(virheellinenSyoteilmoitus);
        frame.add(tulostaulu);
        frame.add(paavalikko);

    }

    /**
     * Lisaa kaikille valikkotauluille Valikoidenkuuntelijan, joka on
     * ActionListener-luokan ilmentyma.
     *
     * @param paavalikko pelin paavalikkotaulu
     * @param pelinAsetukset taulu johon syotetaan pelin tiedot
     * @param valikoidenKuuntelija kuuntelee valikkotaulujen nappeja ja
     * tekstikenttia
     * @param virheellinenSyoteilmoitus taulu, joka nayttaa kayttajan
     * syottovirheen laadun
     * @param tulostaulu pelin tulostaulu
     * @param voittaja ilmoittaa pelin voittajan pelin lopussa
     */
    private void lisaaValikoidenNapeilleKuuntelija(PaavalikkoFrame paavalikko, PelinAsetusFrame pelinAsetukset, ValikoidenKuuntelija valikoidenKuuntelija, VirheellinenSyoteilmoitus virheellinenSyoteilmoitus, Tulostaulu tulostaulu, MatchHistory peliHistoria, Voittaja voittaja) {
        paavalikko.getNewGameNappi().addActionListener(valikoidenKuuntelija);
        paavalikko.getMatchHistoryNappi().addActionListener(valikoidenKuuntelija);
        paavalikko.getQuitNappi().addActionListener(valikoidenKuuntelija);
        pelinAsetukset.getAloitaPeliNappi().addActionListener(valikoidenKuuntelija);
        pelinAsetukset.getTakaisinNappi().addActionListener(valikoidenKuuntelija);
        virheellinenSyoteilmoitus.getOkNappi().addActionListener(valikoidenKuuntelija);
        tulostaulu.getMenuNappi().addActionListener(valikoidenKuuntelija);
        peliHistoria.getCloseNappi().addActionListener(valikoidenKuuntelija);
        voittaja.getVoittoilmoituksenOkNappi().addActionListener(valikoidenKuuntelija);
    }
}
