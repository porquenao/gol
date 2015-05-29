package mobi.porquenao.gol.rule;

import android.support.annotation.NonNull;

import mobi.porquenao.gol.Gol;

public class LimitRule implements Gol.Rule {

    private int mLimit;
    private int mLogged;

    public LimitRule(int limit) {
        mLimit = limit;
    }

    @Override
    public boolean isLoggable(int priority, @NonNull String tag) {
        return mLogged++ < mLimit;
    }

}
