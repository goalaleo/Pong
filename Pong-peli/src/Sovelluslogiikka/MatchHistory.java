package Sovelluslogiikka;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * MatchHistory vastaa pelihistorian tarkastelusta ja tulosten talletuksesta
 * pelihistoriaan
 */
public class MatchHistory {

    /**
     * Pelihistorian nayttava ikkuna
     */
    private JFrame matchHistoryFrame;
    /**
     * Pelihistorian kirjoituksen/tallennuksen hoitava olio
     */
    private BufferedWriter historianKirjoittaja;
    /**
     * Tekstitiedosto, johon pelihistoria tallennetaan. Sijaitsee kayttajan
     * tietokoneen kotihakemistossa
     */
    private File matchHistoryFile;
    /**
     * Pelihistorian lukemisen tiedostosta hoitava olio
     */
    private Scanner lukija;
    /**
     * Pelihistoria- ikkunan osa, johon historia asetetaan naytettavaksi
     */
    private JTextArea historia;
    /**
     * "Scrollauksen" mahdollistava Pane johon historian tekstimuoto asetetaan
     */
    private JScrollPane scrollPane;
    /**
     * Pelihistoria- ikkunan sulkeva nappi
     */
    private JButton close;
    /**
     * Pong- luokan ilmentya, jolta tama luokka kysyy lopputuloksen
     */
    private Pong pong;
    /**
     * Kayttajan kotihakemiston sijainti
     */
    private String kotihakemisto;

    /**
     * MatchHistory-luokan konstruktori
     */
    public MatchHistory() {
        matchHistoryFrame = new JFrame("Pong's Match History");
        matchHistoryFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        matchHistoryFrame.setResizable(false);
        matchHistoryFrame.setPreferredSize(new Dimension(300, 400));

        luoMatchHistoryKomponentit(matchHistoryFrame.getContentPane());

        kotihakemisto = System.getProperty("user.home");
        luoKayttajanKotihakemistoonHistorianTallentavaTiedosto();
        lisaaTiedostolleUusiScanner();
        maaritaUusiHistorianKirjoittaja();
        matchHistoryFrame.pack();
        matchHistoryFrame.setVisible(false);
    }

    /**
     * Luo pelihistoria- nakyman graafiset komponentit.
     *
     * @param container frame, joka sisaltaa komponentit
     */
    private void luoMatchHistoryKomponentit(Container container) {
        this.historia = new JTextArea();
        this.scrollPane = new JScrollPane(historia);
        this.close = new JButton("Close");
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(close, BorderLayout.SOUTH);
    }

    public JButton getCloseNappi() {
        return close;
    }

    /**
     * Asettaa pelihistoria-nakyman nakymattomaksi
     */
    public void piilotaPeliHistoria() {
        matchHistoryFrame.setVisible(false);
    }

    /**
     * Tama metodi kirjaa pelin paatyttya ja voittajan selvittya tuloksen
     * pelihistoriaan
     *
     * @param voittaja voittajan nimi
     * @param haviaja haviajan nimi
     * @param voittajanPisteet voittajan pisteet
     * @param haviajanPisteet haviajanpisteet
     */
    public void kirjaaTulos(String voittaja, String haviaja, int voittajanPisteet, int haviajanPisteet) {
        Calendar kalenteri = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String aikaLeima = sdf.format(kalenteri.getTime());
        try {
            historianKirjoittaja.append(voittaja + " VS " + haviaja + " : " + voittajanPisteet + " - " + haviajanPisteet + "   " + aikaLeima + "\n");
            historianKirjoittaja.close();
        } catch (Exception e) {
        }
        maaritaUusiHistorianKirjoittaja();
    }

    /**
     * Metodi hakee tekstitiedostosta pelihistorian ja lisaa sen pelihistoria-
     * ikkunan nakymaan.
     */
    public void naytaPeliHistoria() {
        historia.setEditable(true);
        historia.setText("");
        String seuraavaRivi;
        while (lukija.hasNextLine()) {
            seuraavaRivi = lukija.nextLine();
            historia.append(seuraavaRivi + "\n");
        }
        lukija.close();
        lisaaTiedostolleUusiScanner();
        historia.setEditable(false);
        matchHistoryFrame.setVisible(true);
    }

    /**
     * Tama metodi luo kayttajan tietokoneen kotihakemistoon tekstitiedoston
     * (mikali sita ei viela ole), johon tallennetaan historia.
     *
     * Tiedoston nimi on 'LeosPongMatchHistory.txt'
     */
    private void luoKayttajanKotihakemistoonHistorianTallentavaTiedosto() {
        matchHistoryFile = new File(kotihakemisto + "/LeosPongMatchHistory.txt");
        if (!matchHistoryFile.exists()) {
            try {
                matchHistoryFile.createNewFile();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Luo pelihistoriaa lukevalle Scannerille uuden ilmentyman
     *
     * @throws NullPointerException
     */
    private void lisaaTiedostolleUusiScanner() throws NullPointerException {
        try {
            lukija = new Scanner(matchHistoryFile);
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    /**
     * Luo pelihistoriaa kirjoittavalle BufferedWriterille uuden ilmentyman
     *
     * @throws NullPointerException
     */
    private void maaritaUusiHistorianKirjoittaja() throws NullPointerException {
        try {
            historianKirjoittaja = new BufferedWriter(new FileWriter(kotihakemisto + "/LeosPongMatchHistory.txt", true));
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }
}
