package SovelluslogiikkaTests;

import Grafiikka.Piirtoalusta;
import Sovelluslogiikka.Pong;
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

        assertEquals(580, pong.getPelaaja2().getX());
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
    public void runMetodinAjaminenOnnistuu() {
        new Thread(pong).start();
    }
}

