package mobi.porquenao.gol.rule;

import android.util.Log;

import mobi.porquenao.gol.test.MockInstrumentationTestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class LevelRuleTest extends MockInstrumentationTestCase {

    private static final String TAG = "TAG";

    public void testIsLoggable_VERBOSE() {
        LevelRule levelRuleDebug = new LevelRule(Log.VERBOSE);
        assertThat(levelRuleDebug.isLoggable(Log.VERBOSE, TAG)).isTrue();
        assertThat(levelRuleDebug.isLoggable(Log.DEBUG, TAG)).isTrue();
    }

    public void testIsLoggable_DEBUG() {
        LevelRule levelRuleDebug = new LevelRule(Log.DEBUG);
        assertThat(levelRuleDebug.isLoggable(Log.VERBOSE, TAG)).isFalse();
        assertThat(levelRuleDebug.isLoggable(Log.DEBUG, TAG)).isTrue();
        assertThat(levelRuleDebug.isLoggable(Log.ERROR, TAG)).isTrue();
    }

    public void testIsLoggable_ERROR() {
        LevelRule levelRuleDebug = new LevelRule(Log.ERROR);
        assertThat(levelRuleDebug.isLoggable(Log.DEBUG, TAG)).isFalse();
        assertThat(levelRuleDebug.isLoggable(Log.ERROR, TAG)).isTrue();
    }

}
