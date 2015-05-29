package mobi.porquenao.gol.writer;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import mobi.porquenao.gol.Gol;

public class FileWriter implements Gol.Writer {

    private File mFile = new File(Environment.getExternalStorageDirectory().toString(), "/log.gol");
    private String mFormatter = "%s %s/%s﹕ %s\n";
    private String mDateFormatter = "MM-dd HH:mm:ss.SSS";

    @Override
    public void write(int priority, @NonNull String tag, @NonNull String message) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(mDateFormatter, Locale.getDefault());
            File file = mFile;
            FileOutputStream out = new FileOutputStream(file, true);
            out.write(String.format(
                    mFormatter,
                    dateFormat.format(new Date()),
                    getPriority(priority),
                    tag,
                    message
            ).getBytes("UTF-8"));
            out.flush();
            out.close();
        } catch (IOException e) {
            Log.e("GOL", "Could not write to file", e);
        }
    }

    private String getPriority(int priority) {
        switch (priority) {
            case Log.VERBOSE:
                return "V";
            case Log.DEBUG:
                return "D";
            case Log.INFO:
                return "I";
            case Log.WARN:
                return "W";
            case Log.ERROR:
                return "E";
            case Log.ASSERT:
                return "A";
            default:
                return "?";
        }
    }

    public static class Builder {

        private File mFile;
        private String mFormatter;
        private String mDateFormatter;

        public Builder setFile(File file) {
            mFile = file;
            return this;
        }

        public Builder setFormatter(String formatter) {
            mFormatter = formatter;
            return this;
        }

        public Builder setDateFormatter(String dateFormatter) {
            mDateFormatter = dateFormatter;
            return this;
        }

        public FileWriter build() {
            FileWriter fileWriter = new FileWriter();
            fileWriter.mFile = mFile;
            fileWriter.mFormatter = mFormatter;
            fileWriter.mDateFormatter = mDateFormatter;
            return fileWriter;
        }

    }

}
