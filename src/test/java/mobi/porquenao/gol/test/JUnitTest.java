package mobi.porquenao.gol.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import mobi.porquenao.gol.Gol;

@RunWith(JUnit4.class)
public class JUnitTest {

    @Test
    public void testJUnit() {
        Gol.l("Logging");
    }

}
