package mobi.porquenao.gol;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobi.porquenao.gol.rule.BuildDebugRule;
import mobi.porquenao.gol.writer.LogCatWriter;

public class Gol {

    private static Gol sDefault = new Gol();

    private String mTag = "GOL";
    private String mDelimiter = " : ";

    private List<ErrorHandler> mErrorHandlers = new ArrayList<>();

    private List<Rule> mRules = new ArrayList<Rule>() {{
        add(new BuildDebugRule());
    }};

    private List<Writer> mWriters = new ArrayList<Writer>() {{
        add(new LogCatWriter());
    }};

    // Static

    public static Gol getDefault() {
        return sDefault;
    }

    public static void setDefault(@NonNull Gol gol) {
        sDefault = gol;
    }

    public static void l(@NonNull Object... objects) {
        sDefault.d(objects);
    }

    // Public API

    public void write(int priority, @NonNull Object... objects) {
        for (Writer writter : mWriters) {
            writter.write(priority, mTag, TextUtils.join(mDelimiter, objects));
        }
    }

    public void error(@NonNull Throwable throwable) {
        e(Log.getStackTraceString(throwable));
        for (ErrorHandler errorHandler : mErrorHandlers) {
            errorHandler.error(throwable);
        }
    }

    // Wrappers

    public void v(@NonNull Object... objects) {
        if (!mWriters.isEmpty() && isLoggable(Log.VERBOSE)) {
            write(Log.VERBOSE, objects);
        }
    }

    public void d(@NonNull Object... objects) {
        if (isLoggable(Log.DEBUG)) {
            write(Log.DEBUG, objects);
        }
    }

    public void i(@NonNull Object... objects) {
        if (isLoggable(Log.INFO)) {
            write(Log.INFO, objects);
        }
    }

    public void w(@NonNull Object... objects) {
        if (isLoggable(Log.WARN)) {
            write(Log.WARN, objects);
        }
    }

    public void e(@NonNull Object... objects) {
        if (isLoggable(Log.ERROR)) {
            write(Log.ERROR, objects);
        }
    }

    public boolean isLoggable(int priority) {
        boolean isLoggable = true;
        for (Rule rule : mRules) {
            isLoggable = rule.isLoggable(priority, mTag) && isLoggable;
        }
        return isLoggable;
    }

    // Getters & Setters

    public String getTag() {
        return mTag;
    }

    public Gol setTag(@NonNull String tag) {
        mTag = tag;
        return this;
    }

    public String getDelimiter() {
        return mDelimiter;
    }

    public Gol setDelimiter(@NonNull String delimiter) {
        mDelimiter = delimiter;
        return this;
    }

    public List<ErrorHandler> getErrorHandlers() {
        return mErrorHandlers;
    }

    public void addErrorHandler(Gol.ErrorHandler errorHandler) {
        mErrorHandlers.add(errorHandler);
    }

    public List<Rule> getRules() {
        return mRules;
    }

    public void addRule(Gol.Rule rule) {
        mRules.add(rule);
    }

    public List<Writer> getWriters() {
        return mWriters;
    }

    public void addWriter(Gol.Writer writer) {
        mWriters.add(writer);
    }

    // Interfaces

    public interface Writer {

        void write(int priority, @NonNull String tag, @NonNull String message);

    }

    public interface ErrorHandler {

        void error(@NonNull Throwable throwable);

    }

    public interface Rule {

        boolean isLoggable(int priority, @NonNull String tag);

    }

}
