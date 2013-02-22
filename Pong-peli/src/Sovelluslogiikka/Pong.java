package Sovelluslogiikka;

import Grafiikka.Piirtoalusta;
import Grafiikka.Tulostaulu;
import Grafiikka.Voittaja;
import domain.Pallo;
import domain.Pelaaja;

/**
 * Pelin peruslogiikasta vastaava luokka.
 *
 * Pong-luokka luo ja sailyttaa sisallaan pelaajat seka pallon. Luokka huolehtii
 * myos pisteiden laskemisesta, pelin nopeuden saadosta seka kontrolloi
 * pelikomponenttien liiketta.
 */
public class Pong implements Runnable {

    /**
     * Pelin ylaraja
     */
    private final int YLARAJA = 0;
    /**
     * Pelin alaraja
     */
    private final int ALARAJA = 430;
    /**
     * Pelin vasen laita
     */
    private final int VASEN_LAITA = 0;
    /**
     * Pelin oikea laita
     */
    private final int OIKEA_LAITA = 640;
    /**
     * Mailan korkeus eli pituus
     */
    private final int MAILAN_KORKEUS = 128;
    /**
     * Mailan leveys
     */
    private final int MAILAN_LEVEYS = 10;
    /**
     * Pelaajan 1 X-koordinaatti
     */
    private final int PELAAJA1_X = 20;
    /**
     * Pelaajan 2 X-koordinaatti
     */
    private final int PELAAJA2_X = 610;
    /**
     * Molempien pelaajien Y-koordinaatti alussa ja maalin jalkeen
     */
    private final int PELAAJIEN_ALKUY = 165;
    /**
     * Pallon X-koordinaatti alussa ja maalin jalkeen
     */
    private final int PALLO_ALKUX = 320;
    /**
     * Pallon Y-koordinaatti alussa ja maalin jalkeen
     */
    private final int PALLO_ALKUY = 215;
    /**
     * Pallon sade
     */
    private final int PALLON_SADE = 20;
    /**
     * @see Grafiikka.Piirtoalusta
     */
    private Piirtoalusta piirtoalusta;
    /**
     * Pelin nopeuden kertova suure. Komponentteja liikutetaan jokaisessa
     * run()-metodin loopissa nopeuden kertovan pikselimaaran. Kaikkien
     * komponenttien liike siis nopeutuu pelin edetessa.
     *
     */
    private int nopeus;
    /**
     * @see domain.Pallo
     */
    private Pallo pallo;
    /**
     * Vasemmanpuoleinen pelaaja
     *
     * @see domain.Pelaaja
     */
    private Pelaaja pelaaja1;
    /**
     * Oikeanpuoleinen pelaaja
     *
     * @see domain.Pelaaja
     */
    private Pelaaja pelaaja2;
    /**
     * Pelaajan 1 pistemaara
     */
    private int pelaajan1Pisteet;
    /**
     * Pelaajan 2 pistemaara
     */
    private int pelaajan2Pisteet;
    /**
     * Pelin alussa syotetty nimi
     */
    private String pelaajan1Nimi;
    /**
     * Pelin alussa syotetty nimi
     */
    private String pelaajan2Nimi;
    /**
     * Voittava maalimaara
     */
    private int mihinPelataan;
    /**
     * Muuttuja laskee pallon osumia pelaajiin, ja pelin nopeutta saadetaan sen
     * mukaan. Muuttujalle annetaan konstruktorissa arvo 0 ja se kasvaa aina
     * yhdella, kun metodi palloTormaaPelaajaan() palauttaa true. Metodi
     * nopeutetaankoPelia() nopeuttaa pelia aina kun pallo on osunut 5 kertaa
     * pelaajiin.
     *
     * @see Sovelluslogiikka.Pong#palloTormaaPelaajaan()
     * @see Sovelluslogiikka.Pong#nopeutetaankoPelia()
     */
    private int pallonOsumatPelaajiin;
    /**
     * Nopeutustahtia voi saataa vaikeustason lisaamiseksi
     */
    private int pelinNopeutusTahti;
    /**
     * Auttaa nopeuden lisaamisessa
     */
    private int nopeudenOsumalaskuri;
    /**
     * Tulostaulu jota Pong paivittaa
     */
    private Tulostaulu tulostaulu;
    /**
     * Boolean jatketaan maarittaa pidetaanko lankaa ylla. Kun jatketaan =
     * false, niin run()-metodin loopin suoritus lopetetaan.
     */
    private boolean jatketaan;
    /**
     * Pelihistoriasta vastaava luokka
     */
    private MatchHistory peliHistoria;
    /**
     * Ponnahdusikkuna, joka kertoo kuka voitti pelin
     */
    private Voittaja voittajaFrame;

    /**
     * Konstruktorissa luodaan pelin pallo ja pelaajat. Konstruktori on
     * parametriton, ja peli alkaa aina samalla nopeudella ja komponenttien
     * paikalla.
     */
    public Pong() {
        this.nopeus = 5;
        this.pallo = new Pallo(PALLO_ALKUX, PALLO_ALKUY, nopeus);
        this.pelaaja1 = new Pelaaja(PELAAJA1_X, PELAAJIEN_ALKUY);
        this.pelaaja2 = new Pelaaja(PELAAJA2_X, PELAAJIEN_ALKUY);
        this.pallonOsumatPelaajiin = 0;
        this.pelinNopeutusTahti = 5;
        this.nopeudenOsumalaskuri = 0;
        this.pelaajan1Pisteet = 0;
        this.pelaajan2Pisteet = 0;
    }

    /**
     * Pelin tarkein metodi, joka vastaa eri toimintojen suoritusjärjestyksestä.
     *
     * Ennen loopin alkua metodi pysayttaa langan 2 sekunniksi, jotta pelaajat
     * ehtivat orientoitua pelin alkuun.
     *
     * Loopin rakenne on karkeasti seuraava: 1) Onko pallo maalissa? Jos on,
     * palautetaan komponentit ja nopeus alkutilaan, ja annetaan maalintekijalle
     * piste. 2) Liikutetaan kaikkia komponentteja 3) Paivitetaan piirtoalusta
     * 4) Pysaytetaan lanka sekunnin kuudeskymmenesosaksi (~60 FPS) 5)
     * Nopeutetaanko pelia? Jos pallo on osunut 5 kertaa pelaajiin edellisesta
     * nopeutuskerrasta, niin nopeutetaan.
     *
     * Tarkempi maarittely loopin metodeista ja niiden kayttamista metodeista on
     * metodien omissa JavaDoc kuvauksissa.
     *
     * @see Sovelluslogiikka.Pong#palloMeniMaaliin()
     * @see Grafiikka.Tulostaulu#paivitaPisteet()
     * @see Sovelluslogiikka.Pong#palautaAlkutilaan()
     * @see Sovelluslogiikka.Pong#liikutaKomponentteja()
     * @see Grafiikka.Piirtoalusta#repaint()
     * @see Sovelluslogiikka.Pong#odota(int)
     * @see Sovelluslogiikka.Pong#nopeutetaankoPelia()
     */
    @Override
    public void run() {
        this.jatketaan = true;
        odota(2000);
        
        while (jatketaan) {
            
            
            
            if (palloMeniMaaliin()) {
                tulostaulu.paivitaPisteet();
                palautaAlkutilaan(true);
                
                if (paattyikoPeli()) {
                    ilmoitaVoittaja();
                    break;
                }
                
            }
            
            liikutaKomponentteja();
            
            piirtoalusta.repaint();
            odota(1000 / 60);
            
            nopeutetaankoPelia();
        }
    }

    /**
     * Metodi maaraa komponenttien siirtymiset seka muuttaa tarvittaessa pallon
     * suuntaa sen tormatessa seinaan tai pelaajaan.
     *
     * Ensin tarkistetaan tormaako pallo seinaan (yla- ja alaseina) metodin
     * palloTormaaSeinaan():boolean avulla. Mikali tormaa, muutetaan pallon
     * Y-suuntainen nopeus vastakkaismerkkiseksi, ja paivitetaan piirtoalusta.
     *
     * Toiseksi Tarkistetaan tormaako pallo pelaajiin metodin
     * palloTormaaPelaajaan():boolean avulla. Mikali tormaa, muutetaan pallon
     * X-suuntainen nopeus vastakkaismerkkiseksi, ja paivitetaan piirtoalusta.
     *
     * Piirtoalustan paivittamisella valtetaan tapaus, jossa pallo nayttaa
     * vaihtavan suuntaa ennen tormausta. Huomaa, etta ilman tormayksia
     * piirtoalusta paivitetaan kerran loopin aikana eli komponenttien
     * koordinaatit muuttuvat pelin nopeuden maaritteleman pikselimaaran ennen
     * kuin ne piirretaan uudestaan.
     *
     * Tormayksien tarkistusten jalkeen kutsutaan jokaisen komponentin
     * siirry()-metodia vuorollaan.
     *
     * Looppi siis kaydaan lapi yhta monta kertaa kuin oliomuuttuja 'nopeus'
     * maaraa, ja mahdollinen tormays tarkastetaan jokaisen loopin alussa ennen
     * komponenttien siirtoa.
     *
     * @see Sovelluslogiikka.Pong#palloTormaaSeinaan()
     * @see Sovelluslogiikka.Pong#palloTormaaPelaajaan()
     * @see domain.Pelaaja#siirry()
     * @see domain.Pallo#siirry()
     */
    protected void liikutaKomponentteja() {
        for (int i = 0; i < nopeus; i++) {
            if (palloTormaaSeinaan()) {
                pallo.setNopeusY(-pallo.getNopeusY());
                piirtoalusta.repaint();
            }
            if (palloTormaaPelaajaan()) {
                pallo.setNopeusX(-pallo.getNopeusX());
                piirtoalusta.repaint();
            }
            pelaaja1.siirry();
            pelaaja2.siirry();
            pallo.siirry();
            
        }
    }

    /**
     * Metodissa tarkistetaan pallon Y-koordinaatti ja katsotaan tormaako se
     * seinaan (onko kiinni seinassa).
     *
     * @return true, jos tormaa - false, jos EI tormaa
     */
    protected boolean palloTormaaSeinaan() {
        if (pallo.getY() == YLARAJA || pallo.getY() == ALARAJA) {
            return true;
        }
        return false;
    }

    /**
     * Metodissa tarkistetaan pallon X-koordinaatti ja katsotaan tormaako se
     * jompaan kumpaan pelaajaan (onko kiinni pelaajassa).
     *
     * @return true, jos tormaa jompaan kumpaan - false, jos EI tormaa
     * kumpaankaan
     */
    protected boolean palloTormaaPelaajaan() {
        //pallo tormaa pelaajaan2, -10 ja +10 ehkaisevat sita, etta pallo nayttaa menevan mailan reunojen lapi.
        if (pallo.getX() == PELAAJA2_X - PALLON_SADE && pallo.getY() >= pelaaja2.getY() - 10 && pallo.getY() <= (pelaaja2.getY() + MAILAN_KORKEUS + 10)) {
            pallonOsumatPelaajiin += 1;
            nopeudenOsumalaskuri += 1;
            return true;
        }
        //pallo tormaa pelaajaan1 
        if (pallo.getX() == (PELAAJA1_X + MAILAN_LEVEYS) && pallo.getY() >= pelaaja1.getY() - 10 && pallo.getY() <= (pelaaja1.getY() + MAILAN_KORKEUS + 10)) {
            pallonOsumatPelaajiin += 1;
            nopeudenOsumalaskuri += 1;
            return true;
        }
        return false;
    }
    
    public Pallo getPallo() {
        return pallo;
    }
    
    public Pelaaja getPelaaja1() {
        return pelaaja1;
    }
    
    public Pelaaja getPelaaja2() {
        return pelaaja2;
    }
    
    public int getNopeus() {
        return nopeus;
    }
    
    public int getPelaaja1Pisteet() {
        return pelaajan1Pisteet;
    }
    
    public int getPelaaja2Pisteet() {
        return pelaajan2Pisteet;
    }
    
    public String getPelaaja1Nimi() {
        return pelaajan1Nimi;
    }
    
    public String getPelaaja2Nimi() {
        return pelaajan2Nimi;
    }
    
    public int getMihinPelataan() {
        return mihinPelataan;
    }
    
    public Piirtoalusta getPiirtoalusta() {
        return piirtoalusta;
    }

    /**
     * Ennen pelin kaynnistysta, annetaan Pong-luokasta tehdylle pong-oliolle
     * piirtoalusta, jonka paivityksen se kaskee pelin aikana.
     *
     * @param piirtoalusta Kayttoliittymassa luotu piirtoalusta
     */
    public void setPiirtoalusta(Piirtoalusta piirtoalusta) {
        this.piirtoalusta = piirtoalusta;
    }
    
    public void setPelaajan1Nimi(String nimi) {
        this.pelaajan1Nimi = nimi;
    }
    
    public void setPelaajan2Nimi(String nimi) {
        this.pelaajan2Nimi = nimi;
    }
    
    public void setMihinPelataan(int mihinPelataan) {
        this.mihinPelataan = mihinPelataan;
    }
    /**
     * Asettaa pelille pelihistorian, johon se kirjoittaa lopputuloksen
     * @param peliHistoria pelien tuloksista vastaavan luokan ilmentyma
     */
    public void setPeliHistoria(MatchHistory peliHistoria) {
        this.peliHistoria = peliHistoria;
    }

    /**
     * Metodissa tarkastetaan pallon X-koordinaatin perusteella onko pallo
     * mennyt pelaajien "selan taakse" maaliin.
     *
     * Maalin tehneen pelaajan pistemaaraa kasvatetaan myos yhdella.
     *
     * @return true, jos pallo mennyt maaliin - false, jos pallo EI ole mennyt
     * maaliin
     */
    protected boolean palloMeniMaaliin() {
        if (pallo.getX() < VASEN_LAITA) {
            pelaajan2Pisteet++;
            return true;
        } else if (pallo.getX() > OIKEA_LAITA) {
            pelaajan1Pisteet++;
            return true;
        }
        return false;
    }

    /**
     * Palauttaa pelin komponentit ja nopeuden alkutilaan, mikali metodi
     * palloMeniMaaliin() on palauttanut true.
     *
     * Alkutilaan palautuksen yhteydessa, pallon alkusuuntaa muutetaan
     * vaihdaPallonAlkusuuntaa()- metodilla.
     *
     * Metodin lopuksi, kun piirtoalusta on paivitetty (komponentit
     * alkupaikoilla), pysaytetaan lanka taas 2 sekunniksi mikali parametri on
     * true, jotta pelaajat ehtivat orientoitua uuteen palloon.
     *
     * @see Sovelluslogiikka.Pong#vaihdaPallonAlkusuuntaa()
     * @param viiveella Maarittaa pidetaanko metodin lopuksi tauko. Pelin
     * keskeytys palauttaa myos pelin alkutilaan, mutta parametri on false,
     * jotta Paavalikkoon palaaminen tapahtuu viiveetta.
     */
    protected void palautaAlkutilaan(Boolean viiveella) {
        this.nopeus = 5;
        this.nopeudenOsumalaskuri = 0;
        pallo.setX(PALLO_ALKUX);
        pallo.setY(PALLO_ALKUY);
        
        vaihdaPallonAlkusuuntaa();
        
        pelaaja1.setPaikkaX(
                PELAAJA1_X);
        pelaaja1.setPaikkaY(
                PELAAJIEN_ALKUY);
        pelaaja2.setPaikkaX(
                PELAAJA2_X);
        pelaaja2.setPaikkaY(
                PELAAJIEN_ALKUY);
        
        piirtoalusta.repaint();
        
        System.out.println(
                "1. :" + pelaajan1Pisteet + ", " + "2. :" + pelaajan2Pisteet);
        if (viiveella) {
            odota(
                    2000);
        }
    }

    /**
     * Pelia nopeutetaan, mikali edellisesta nopeutuksesta on tapahtunut 5
     * pallon tormaysta pelaajiin.
     *
     * Metodi tarkistaa oliomuuttujan 'nopeudenOsumalaskuri' (jota kasvattaa
     * metodi palloTormaaPelaajaan()) arvon. Mikali sen ja oliomuuttujan
     * pelinNopeutusTahti jakojaannos on nolla, kasvatetaan oliomuuttujan
     * 'nopeus' arvoa yhdella, ja nollataan 'nopeudenOsumaLaskuri'.
     *
     * @see Sovelluslogiikka.Pong#palloTormaaPelaajaan()
     * @see Sovelluslogiikka.Pong#nopeudenOsumalaskuri
     * @see Sovelluslogiikka.Pong#nopeus
     */
    protected void nopeutetaankoPelia() {
        if (nopeudenOsumalaskuri % pelinNopeutusTahti == 0 && nopeudenOsumalaskuri != 0) {
            nopeus++;
            nopeudenOsumalaskuri = 0;
        }
    }

    /**
     * Kayttaa Luokan Thread metodia sleep().
     *
     * Metodi on luotu, jotta run()- metodi olisi helpommin luettavissa.
     *
     * @param millisekunteja aika jonka lanka "nukkuu"
     */
    private void odota(int millisekunteja) {
        try {
            Thread.sleep(millisekunteja);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Talla metodilla muutetaan pallon alkusuuntaa maaliin menon jalkeen.
     *
     * palautaAlkutilaan()-metodi kutsuu tata metodia, joka vaihtaa pallon X- ja
     * Y-suuntaisia nopeuksia pistetilanteen mukaan. Alkusuunta ei ole
     * sattumanvarainen, vaan se noudattaa 4 sunnan kiertoa: oikea-alas,
     * vasen-ylos, oikea-ylos, vasen-alas.
     */
    private void vaihdaPallonAlkusuuntaa() {
        pallo.setNopeusX(nopeus);
        pallo.setNopeusY(nopeus);
        
        if (((pelaajan1Pisteet + pelaajan2Pisteet) % 2 == 0) && ((pelaajan1Pisteet + pelaajan2Pisteet) % 4 != 0)) {
            pallo.setNopeusY(-nopeus);
        } else if (((pelaajan1Pisteet + pelaajan2Pisteet) == 3) || ((pelaajan1Pisteet + pelaajan2Pisteet) % 4 == 3)) {
            pallo.setNopeusY(-nopeus);
        } else if ((pelaajan1Pisteet + pelaajan2Pisteet) % 4 == 0) {
            pallo.setNopeusY(nopeus);
        }
        
        
        if ((pelaajan1Pisteet + pelaajan2Pisteet) % 2 != 0) {
            pallo.setNopeusX(-nopeus);
        } else {
            pallo.setNopeusX(nopeus);
        }
    }

    /**
     * Asettaa pelille tulostaulun, jota pong paivittaa.
     *
     * @param tulostaulu Kayttoliittymassa luotu tulostaulu.
     */
    public void setTulostaulu(Tulostaulu tulostaulu) {
        this.tulostaulu = tulostaulu;
    }

    /**
     * Katkaisee run()-metodin while()-loopin.
     */
    protected void stop() {
        jatketaan = false;
    }

    /**
     * Nollaa pisteet ja pallonOsumatPelaajiin uutta pelia varten.
     */
    protected void nollaaPisteet() {
        pelaajan1Pisteet = 0;
        pelaajan2Pisteet = 0;
        pallonOsumatPelaajiin = 0;
    }

    /**
     * Nollaa pelin tilanteen
     */
    protected void nollaaPongPeli() {
        palautaAlkutilaan(false);
        piirtoalusta.repaint();
        pallo.setNopeusX(nopeus);
        pallo.setNopeusY(nopeus);
        nollaaPisteet();
    }
    /**
     * Tarkistaa onko jommallakummalla pelaajalla voittava pistemaara ja kaskee
     * tuloksen kirjaamisen pelihistoriaan
     * @return true, jos voittaja on loytynyt - false, jos peli jatkuu viela
     */
    private boolean paattyikoPeli() {
        String voittaja;
        String haviaja;
        int voittajanPisteet;
        int haviajanPisteet;
        
        if (pelaajan1Pisteet == mihinPelataan || pelaajan2Pisteet == mihinPelataan) {
            jatketaan = false;
            if (pelaajan1Pisteet > pelaajan2Pisteet) {
                voittaja = pelaajan1Nimi;
                haviaja = pelaajan2Nimi;
                voittajanPisteet = pelaajan1Pisteet;
                haviajanPisteet = pelaajan2Pisteet;
            } else {
                voittaja = pelaajan2Nimi;
                haviaja = pelaajan1Nimi;
                voittajanPisteet = pelaajan2Pisteet;
                haviajanPisteet = pelaajan1Pisteet;
            }
            peliHistoria.kirjaaTulos(voittaja, haviaja, voittajanPisteet, haviajanPisteet);
            nollaaPongPeli();
            return true;
        }
        return false;
    }
    /**
     * Asettaa pongille voittaja-framen, jonka pong kutsuu esille voittajan selvittya
     * @param voittajaFrame 
     */
    public void setVoittajaFrame(Voittaja voittajaFrame) {
        this.voittajaFrame = voittajaFrame;
    }
    /**
     * Maaraa voittotaulun tekstin, ja kaskee sen nayttamisen.
     */
    private void ilmoitaVoittaja() {
        if (pelaajan1Pisteet > pelaajan2Pisteet) {
            voittajaFrame.setVoittoteksti(pelaajan1Nimi);
        } else {
            voittajaFrame.setVoittoteksti(pelaajan2Nimi);
        }
        tulostaulu.setVisible(false);
        voittajaFrame.setVisible(true);
    }
}
