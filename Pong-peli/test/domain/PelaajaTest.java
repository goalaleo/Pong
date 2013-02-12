package domain;

import domain.Pelaaja;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PelaajaTest {

    private Pelaaja pelaaja;

    public PelaajaTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pelaajanLuontiOnnistuuJaNopeusOnAlussaNolla() {
        pelaaja = new Pelaaja(20, 165);
        assertEquals(0, pelaaja.getNopeus());
    }

    public void pelaajanPaikkaOnOikea() {
        pelaaja = new Pelaaja(20, 300);
        assertEquals(20, pelaaja.getX());
        assertEquals(300, pelaaja.getY());
    }

    public void pelaajaLiikkuuOikeaanSuuntaan() {
        pelaaja = new Pelaaja(560, 250);
        pelaaja.setNopeus(5);
        pelaaja.siirry();
        assertEquals(251, pelaaja.getY());
        pelaaja.siirry();
        assertEquals(252, pelaaja.getY());
        pelaaja.setNopeus(-5);
        assertEquals(251, pelaaja.getY());

    }

    public void pelaajaEiMeneYlaseinastaLapi() {
        pelaaja = new Pelaaja(560, 1);
        pelaaja.setNopeus(-5);
        pelaaja.siirry();
        assertEquals(0, pelaaja.getY());
        pelaaja.siirry();
        assertEquals(0, pelaaja.getY());
    }

    public void pelaajaEiMeneAlaseinastaLapi() {
        pelaaja = new Pelaaja(560, 325);
        pelaaja.setNopeus(5);
        pelaaja.siirry();
        assertEquals(326, pelaaja.getY());
        pelaaja.siirry();
        assertEquals(326, pelaaja.getY());
    }

    public void setteritXJaYKoordinaateilleToimivat() {
        pelaaja = new Pelaaja(560, 325);
        assertEquals(325, pelaaja.getY());
        assertEquals(560, pelaaja.getX());
    }
}
