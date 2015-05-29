package mobi.porquenao.gol.rule;

import android.support.annotation.NonNull;

import mobi.porquenao.gol.Gol;

public class SkipRule implements Gol.Rule {

    private int mAmount;
    private int mLogged;

    public SkipRule(int amount) {
        mAmount = amount;
    }

    @Override
    public boolean isLoggable(int priority, @NonNull String tag) {
        if (mLogged-- <= 0) {
            mLogged = mAmount;
            return true;
        }
        return false;
    }

}
