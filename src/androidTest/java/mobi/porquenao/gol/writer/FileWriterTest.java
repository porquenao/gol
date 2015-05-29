package mobi.porquenao.gol.writer;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import mobi.porquenao.gol.test.MockInstrumentationTestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class FileWriterTest extends MockInstrumentationTestCase {

    private static final String TAG = "TAG";
    private static final String MESSAGE = "MESSAGE";

    public void testWrite() throws Exception {
        File file = new File(Environment.getExternalStorageDirectory().toString(), "/log.gol");
        cleanFile(file);

        FileWriter fileWriter = new FileWriter();
        fileWriter.write(Log.ERROR, TAG, MESSAGE);

        String content = readFile(new FileInputStream(file));
        assertThat(content).contains(TAG, MESSAGE);
    }

    public void testBuilder() throws Exception {
        File file = new File(getInstrumentation().getTargetContext().getFilesDir(), "/log.gol");
        cleanFile(file);

        FileWriter fileWriter = new FileWriter.Builder()
                .setFile(file)
                .setDateFormatter("yyyy-MM-dd")
                .setFormatter("|| %s %s %s %s")
                .build();
        fileWriter.write(Log.ERROR, TAG, MESSAGE);

        String content = readFile(new FileInputStream(file));
        assertThat(content).contains(TAG, MESSAGE);
    }

    private void cleanFile(File file) throws Exception {
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }

    private String readFile(FileInputStream fileInputStream) {
        try {
            if (fileInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                fileInputStream.close();
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            Log.e("GOL", "Could not read file", e);
        }
        return "";
    }

}
