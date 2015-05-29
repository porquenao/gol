package mobi.porquenao.gol.rule;

import android.util.Log;

import mobi.porquenao.gol.test.MockInstrumentationTestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class LimitRuleTest extends MockInstrumentationTestCase {

    private static final String TAG = "TAG";

    public void testIsLoggable() {
        LimitRule limitRuleDebug = new LimitRule(2);
        assertThat(limitRuleDebug.isLoggable(Log.DEBUG, TAG)).isTrue();
        assertThat(limitRuleDebug.isLoggable(Log.DEBUG, TAG)).isTrue();
        assertThat(limitRuleDebug.isLoggable(Log.DEBUG, TAG)).isFalse();
        assertThat(limitRuleDebug.isLoggable(Log.DEBUG, TAG)).isFalse();
    }

}
