package mobi.porquenao.gol.test;

import android.test.InstrumentationTestCase;

import org.mockito.MockitoAnnotations;

public class MockInstrumentationTestCase extends InstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
        MockitoAnnotations.initMocks(this);
    }

}
