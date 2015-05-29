package mobi.porquenao.gol.rule;

import android.util.Log;

import mobi.porquenao.gol.test.MockInstrumentationTestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class SkipRuleTest extends MockInstrumentationTestCase {

    private static final String TAG = "TAG";

    public void testIsLoggable() {
        SkipRule skipRuleDebug = new SkipRule(2);
        assertThat(skipRuleDebug.isLoggable(Log.DEBUG, TAG)).isTrue();
        assertThat(skipRuleDebug.isLoggable(Log.DEBUG, TAG)).isFalse();
        assertThat(skipRuleDebug.isLoggable(Log.DEBUG, TAG)).isFalse();
        assertThat(skipRuleDebug.isLoggable(Log.DEBUG, TAG)).isTrue();
        assertThat(skipRuleDebug.isLoggable(Log.DEBUG, TAG)).isFalse();
        assertThat(skipRuleDebug.isLoggable(Log.DEBUG, TAG)).isFalse();
    }

}
