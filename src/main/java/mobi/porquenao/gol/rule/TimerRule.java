package mobi.porquenao.gol.rule;

import android.support.annotation.NonNull;

import mobi.porquenao.gol.Gol;

public class TimerRule implements Gol.Rule {

    private long mMilliseconds;
    private long mLastMilliseconds;

    public TimerRule(long milliseconds) {
        mMilliseconds = milliseconds;
    }

    @Override
    public boolean isLoggable(int priority, @NonNull String tag) {
        long now = System.currentTimeMillis();
        if (now - mLastMilliseconds > mMilliseconds) {
            mLastMilliseconds = now;
            return true;
        }
        return false;
    }

}
