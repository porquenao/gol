package mobi.porquenao.gol.test;

import android.support.annotation.NonNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import mobi.porquenao.gol.Gol;

@RunWith(JUnit4.class)
public class JUnitTest {

    @Test
    public void testJUnit() {
        Gol gol = new Gol();

        // In JUnit tests we need to remove the default writer once it has a LogCatWriter
        gol.getWriters().clear();

        // Optional, we can add an STDOUT log to test
        gol.addWriter(new Gol.Writer() {
            @Override
            public void write(int priority, @NonNull String tag, @NonNull String message) {
                System.out.println(String.format("%s: %s", tag, message));
            }
        });

        Gol.setDefault(gol);
        Gol.l("Logging");
    }

}
