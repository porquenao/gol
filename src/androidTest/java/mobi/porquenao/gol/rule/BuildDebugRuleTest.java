package mobi.porquenao.gol.rule;

import android.util.Log;

import mobi.porquenao.gol.test.MockInstrumentationTestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class BuildDebugRuleTest extends MockInstrumentationTestCase {

    private static final String TAG = "TAG";

    public void testIsLoggable() {
        BuildDebugRule debugRuleBuildDebug = new BuildDebugRule();
        assertThat(debugRuleBuildDebug.isLoggable(Log.VERBOSE, TAG)).isTrue();
    }

}
