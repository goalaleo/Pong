package domain;

import domain.Pallo;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PalloTest {

    private Pallo pallo;

    public PalloTest() {
    }

    @Before
    public void setUp() {
        pallo = new Pallo(320, 180, 5);
    }

    @Test
    public void palloLuodaanOikeaanPaikkaan() {
        assertEquals(320, pallo.getX());
        assertEquals(180, pallo.getY());
    }

    @Test
    public void palloSiirtyyYhdenYksikonOikeaanSuuntaan1() {
        pallo.siirry();
        assertEquals(321, pallo.getX());
        assertEquals(181, pallo.getY());
    }

    @Test
    public void palloSiirtyyYhdenYksikonOikeaanSuuntaan2() {
        pallo.setNopeusY(-5);
        pallo.siirry();
        assertEquals(321, pallo.getX());
        assertEquals(179, pallo.getY());
    }

    @Test
    public void palloSiirtyyYhdenYksikonOikeaanSuuntaan3() {
        pallo.setNopeusX(-5);
        pallo.siirry();
        assertEquals(319, pallo.getX());
        assertEquals(181, pallo.getY());
    }

    @Test
    public void palloSiirtyyYhdenYksikonOikeaanSuuntaan4() {
        pallo.setNopeusX(-5);
        pallo.setNopeusY(-5);
        pallo.siirry();
        assertEquals(319, pallo.getX());
        assertEquals(179, pallo.getY());


    }

    public void nopeudenMuutosOnnistuuXSuunnassa() {
        pallo.setNopeusX(-10);
        assertEquals(-10, pallo.getNopeusX());
    }

    public void nopeudenMuutosOnnistuuYSuunnassa() {
        pallo.setNopeusY(-5);
        assertEquals(-5, pallo.getNopeusY());
    }

    @Test
    public void setXJaYToimii() {
        pallo.setX(50);
        assertEquals(50, pallo.getX());
        pallo.setY(163);
        assertEquals(163, pallo.getY());
    }

    @Test
    public void KonstruktoriToimii() {
        Pallo pallo2 = new Pallo(120, 250, 10);
        assertEquals(120, pallo2.getX());
        assertEquals(250, pallo2.getY());
        assertEquals(10, pallo2.getNopeusX());
        assertEquals(10, pallo2.getNopeusY());

    }
}

