package Sovelluslogiikka;

import Grafiikka.Piirtoalusta;
import Grafiikka.Tulostaulu;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PongTest {

    private Pong pong;

    public PongTest() {
    }

    @Before
    public void setUp() {
        this.pong = new Pong();
        pong.setPiirtoalusta(new Piirtoalusta(pong));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void palloOnSyntynytOikeaanPaikkaan() {
        assertEquals(320, pong.getPallo().getX());
        assertEquals(215, pong.getPallo().getY());
    }

    @Test
    public void nopeusOnOikea() {
        assertEquals(5, pong.getNopeus());
    }

    @Test
    public void pelaajatSyntyvatOikeisiinPaikkoihin() {
        assertEquals(20, pong.getPelaaja1().getX());
        assertEquals(165, pong.getPelaaja1().getY());

        assertEquals(610, pong.getPelaaja2().getX());
        assertEquals(165, pong.getPelaaja2().getY());
    }

    @Test
    public void pisteetAlkavatNollasta() {
        assertEquals(0, pong.getPelaaja1Pisteet());
        assertEquals(0, pong.getPelaaja2Pisteet());
    }

    @Test
    public void piirtoalustanLuominenJaAsettaminenOnnistuu() {
        try {
            pong.setPiirtoalusta(new Piirtoalusta(pong));
        } catch (Exception e) {
            throw new UnknownError("Piirtoalustan luominen epäonnistui");
        }
    }

    @Test
    public void palloOnMaalissa() {

        pong.getPallo().setX(641);
        assertEquals(true, pong.palloMeniMaaliin());
        pong.getPallo().setX(-1);
        assertEquals(true, pong.palloMeniMaaliin());
    }

    @Test
    public void palloEiVielaMaalissa() {
        pong.getPallo().setX(640);
        assertEquals(false, pong.palloMeniMaaliin());
        pong.getPallo().setX(0);
        assertEquals(false, pong.palloMeniMaaliin());
        pong.getPallo().setX(240);
        assertEquals(false, pong.palloMeniMaaliin());
    }

    @Test
    public void alkutilaanPalauttaminenOnnistuu() {
        pong.getPelaaja1().setNopeus(1);
        pong.getPelaaja2().setNopeus(-1);
        for (int i = 0; i < 3; i++) {
            pong.liikutaKomponentteja();
        }
        assertEquals(180, pong.getPelaaja1().getY());
        assertEquals(150, pong.getPelaaja2().getY());
        assertEquals(335, pong.getPallo().getX());
        assertEquals(230, pong.getPallo().getY());

        pong.palautaAlkutilaan(false);

        assertEquals(165, pong.getPelaaja1().getY());
        assertEquals(165, pong.getPelaaja2().getY());
        assertEquals(320, pong.getPallo().getX());
        assertEquals(215, pong.getPallo().getY());
    }

    @Test
    public void palloVaihtaaYSuuntaaOsuessaasSeiniin() {
        pong.getPallo().setNopeusY(-5);
        pong.getPallo().setY(2);
        pong.liikutaKomponentteja();
        assertEquals(3, pong.getPallo().getY());
        assertEquals(325, pong.getPallo().getX());

        pong.getPallo().setNopeusY(5);
        pong.getPallo().setNopeusX(-5);
        pong.getPallo().setY(428);
        pong.getPallo().setX(320);
        pong.liikutaKomponentteja();
        assertEquals(427, pong.getPallo().getY());
        assertEquals(315, pong.getPallo().getX());
    }

    @Test
    public void palloVaihtaaSuuntaaOsuessaanPelaajiin() {
        pong.getPallo().setX(32);
        pong.getPallo().setY(180);
        pong.getPallo().setNopeusX(-5);
        pong.getPallo().setNopeusY(-5);
        pong.liikutaKomponentteja();
        assertEquals(33, pong.getPallo().getX());
        assertEquals(175, pong.getPallo().getY());

        pong.getPallo().setX(588);
        pong.getPallo().setY(180);
        pong.getPallo().setNopeusX(5);
        pong.getPallo().setNopeusY(5);
        pong.liikutaKomponentteja();
        assertEquals(587, pong.getPallo().getX());
        assertEquals(185, pong.getPallo().getY());
    }

    @Test
    public void sunnanVaihtoToimii() {
        pong.getPallo().setX(-1);
        pong.palloMeniMaaliin();
        assertEquals(1, pong.getPelaaja2Pisteet());
        pong.palautaAlkutilaan(false);
        pong.liikutaKomponentteja();
        assertEquals(315, pong.getPallo().getX());
        assertEquals(220, pong.getPallo().getY());
    }

    @Test
    public void nopeutusOnnistuu() {
        for (int i = 0; i < 5; i++) {
            pong.getPallo().setX(32);
            pong.getPallo().setY(180);
            pong.getPallo().setNopeusX(-5);
            pong.getPallo().setNopeusY(-5);
            pong.liikutaKomponentteja();
        }
        pong.nopeutetaankoPelia();
        assertEquals(6, pong.getNopeus());
    }
}
