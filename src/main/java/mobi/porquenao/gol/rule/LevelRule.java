package mobi.porquenao.gol.rule;

import android.support.annotation.NonNull;

import mobi.porquenao.gol.Gol;

public class LevelRule implements Gol.Rule {

    private int mLevel;

    public LevelRule(int level) {
        mLevel = level;
    }

    @Override
    public boolean isLoggable(int priority, @NonNull String tag) {
        return mLevel <= priority;
    }

}
