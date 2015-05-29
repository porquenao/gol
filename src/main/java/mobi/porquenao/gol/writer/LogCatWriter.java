package mobi.porquenao.gol.writer;

import android.support.annotation.NonNull;
import android.util.Log;

import mobi.porquenao.gol.Gol;

public class LogCatWriter implements Gol.Writer {

    @Override
    public void write(int priority, @NonNull String tag, @NonNull String message) {
        Log.println(priority, tag, message);
    }

}
