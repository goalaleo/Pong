
import domain.Pallo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalloTest {

    private Pallo pallo;

    public PalloTest() {
    }

    @Before
    public void setUp() {
        pallo = new Pallo(60, 140, 10, 10);
    }

    @Test
public void palloLuodaanOikeaanPaikkaan() {
        assertEquals(60, pallo.getX());
        assertEquals(140, pallo.getY());
    }

//    @Test
////    public void palloSiirtyyOikeanMaaran() {
////        pallo.liiku();
////        assertEquals(70, pallo.getX());
////        assertEquals(150, pallo.getY());
////    }

    public void nopeudenMuutosOnnistuuXSuunnassa() {
        pallo.setNopeusX(-10);
        assertEquals(-10, pallo.getNopeusX());
    }

    public void nopeudenMuutosOnnistuuYSuunnassa() {
        pallo.setNopeusY(-5);
        assertEquals(-5, pallo.getNopeusY());
    }
}
