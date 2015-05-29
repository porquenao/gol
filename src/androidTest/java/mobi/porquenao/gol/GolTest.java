package mobi.porquenao.gol;

import android.support.annotation.NonNull;
import android.util.Log;

import org.mockito.Mock;

import mobi.porquenao.gol.test.MockInstrumentationTestCase;
import mobi.porquenao.gol.writer.LogCatWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class GolTest extends MockInstrumentationTestCase {

    private static final String MESSAGE = "MESSAGE";

    @Mock private Gol mGolMock;
    @Mock private Gol.Writer mWriterMock;

    private Gol mGol;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mGol = new Gol();
        mGol.getErrorHandlers().clear();
        mGol.getRules().clear();
        mGol.getWriters().clear();
        mGol.addWriter(mWriterMock);
    }

    public void testStarters() {
        Gol gol = new Gol();
        assertThat(gol.getErrorHandlers()).isEmpty();
        assertThat(gol.getRules()).isEmpty();
        assertThat(gol.getWriters()).isNotEmpty();
        assertThat(gol.getWriters().get(0)).isInstanceOf(LogCatWriter.class);
    }

    // Static

    public void testDefault() {
        assertThat(Gol.getDefault()).isNotNull();
        assertThat(Gol.getDefault()).isSameAs(Gol.getDefault());
        Gol.getDefault().setDelimiter(" || ");
        assertThat(Gol.getDefault().getDelimiter()).isEqualTo(" || ");
    }

    public void testSetDefault() {
        Gol.setDefault(mGolMock);
        assertThat(Gol.getDefault()).isSameAs(mGolMock);
    }

    public void testL() {
        Gol.setDefault(mGolMock);
        Gol.l(MESSAGE);
        verify(mGolMock).d(MESSAGE);
    }

    // Public API

    public void testWrite() {
        mGol.write(Log.ERROR, MESSAGE);
        verify(mWriterMock).write(Log.ERROR, mGol.getTag(), MESSAGE);
    }

    public void testError() {
        Gol.ErrorHandler errorHandlerMock = mock(Gol.ErrorHandler.class);
        mGol.getErrorHandlers().add(errorHandlerMock);
        RuntimeException exception = new RuntimeException(MESSAGE);
        mGol.error(exception);
        verify(errorHandlerMock).error(exception);
    }

    // Wrappers

    public void testV() {
        mGol.v(MESSAGE);
        verify(mWriterMock).write(Log.VERBOSE, mGol.getTag(), MESSAGE);
    }

    public void testV_NotLoggable() {
        mGol.addRule(new FalseRule());
        mGolMock.v(MESSAGE);
        verify(mGolMock, never()).write(Log.VERBOSE, mGolMock.getTag(), MESSAGE);
    }

    public void testD() {
        mGol.d(MESSAGE);
        verify(mWriterMock).write(Log.DEBUG, mGol.getTag(), MESSAGE);
    }

    public void testD_NotLoggable() {
        mGol.addRule(new FalseRule());
        mGolMock.d(MESSAGE);
        verify(mGolMock, never()).write(Log.DEBUG, mGolMock.getTag(), MESSAGE);
    }

    public void testI() {
        mGol.i(MESSAGE);
        verify(mWriterMock).write(Log.INFO, mGol.getTag(), MESSAGE);
    }

    public void testI_NotLoggable() {
        mGol.addRule(new FalseRule());
        mGolMock.i(MESSAGE);
        verify(mGolMock, never()).write(Log.INFO, mGolMock.getTag(), MESSAGE);
    }

    public void testW() {
        mGol.w(MESSAGE);
        verify(mWriterMock).write(Log.WARN, mGol.getTag(), MESSAGE);
    }

    public void testW_NotLoggable() {
        mGol.addRule(new FalseRule());
        mGolMock.w(MESSAGE);
        verify(mGolMock, never()).write(Log.WARN, mGolMock.getTag(), MESSAGE);
    }

    public void testE() {
        mGol.e(MESSAGE);
        verify(mWriterMock).write(Log.ERROR, mGol.getTag(), MESSAGE);
    }

    public void testE_NotLoggable() {
        mGol.addRule(new FalseRule());
        mGolMock.e(MESSAGE);
        verify(mGolMock, never()).write(Log.ERROR, mGolMock.getTag(), MESSAGE);
    }

    public void testIsLoggable() {
        assertThat(mGol.isLoggable(Log.VERBOSE)).isTrue();
        mGol.addRule(new FalseRule());
        assertThat(mGol.isLoggable(Log.VERBOSE)).isFalse();
    }

    // Getters & Setters

    public void testGetTag() {
        assertThat(mGol.getTag()).isInstanceOf(String.class);
    }

    public void testSetTag() {
        mGol.setTag("TAG");
        mGol.d(MESSAGE);
        verify(mWriterMock).write(Log.DEBUG, "TAG", MESSAGE);
    }

    public void testGetDelimiter() {
        assertThat(mGol.getDelimiter()).isInstanceOf(String.class);
    }

    public void testSetDelimiter() {
        mGol.setDelimiter(" : ");
        mGol.d(MESSAGE, MESSAGE);
        verify(mWriterMock).write(Log.DEBUG, mGol.getTag(), MESSAGE + " : " + MESSAGE);
    }

    public void testGetErrorHandlers() {
        assertThat(mGol.getErrorHandlers()).isEmpty();
    }

    public void testAddErrorHandler() {
        Gol.ErrorHandler errorHandler = mock(Gol.ErrorHandler.class);
        mGol.addErrorHandler(errorHandler);
        assertThat(mGol.getErrorHandlers()).isNotEmpty();
    }

    public void testGetRules() {
        assertThat(mGol.getRules()).isEmpty();
    }

    public void testAddRule() {
        Gol.Rule rule = mock(Gol.Rule.class);
        mGol.addRule(rule);
        assertThat(mGol.getRules()).isNotEmpty();
    }

    public void testGetWrites() {
        assertThat(mGol.getWriters()).isNotEmpty();
    }

    public void testAddWrite() {
        Gol.Writer writer = mock(Gol.Writer.class);
        mGol.addWriter(writer);
        assertThat(mGol.getWriters()).isNotEmpty();
    }

    private class FalseRule implements Gol.Rule {

        @Override
        public boolean isLoggable(int priority, @NonNull String tag) {
            return false;
        }

    }

}
