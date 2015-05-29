package mobi.porquenao.gol.rule;

import android.support.annotation.NonNull;

import mobi.porquenao.gol.BuildConfig;
import mobi.porquenao.gol.Gol;

public class BuildDebugRule implements Gol.Rule {

    @Override
    public boolean isLoggable(int priority, @NonNull String tag) {
        return BuildConfig.DEBUG;
    }

}
