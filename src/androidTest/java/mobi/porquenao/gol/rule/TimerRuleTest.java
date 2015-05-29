package mobi.porquenao.gol.rule;

import android.util.Log;

import mobi.porquenao.gol.test.MockInstrumentationTestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class TimerRuleTest extends MockInstrumentationTestCase {

    private static final String TAG = "TAG";

    public void testIsLoggable() {
        TimerRule timerRuleDebug = new TimerRule(5);
        assertThat(timerRuleDebug.isLoggable(Log.DEBUG, TAG)).isTrue();
        assertThat(timerRuleDebug.isLoggable(Log.DEBUG, TAG)).isFalse();
        try {
            Thread.sleep(6);
            assertThat(timerRuleDebug.isLoggable(Log.DEBUG, TAG)).isTrue();
        } catch (InterruptedException ignored) {}
    }

}
