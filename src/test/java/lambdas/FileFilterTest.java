package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertEquals;

public class FileFilterTest {
    @Test
    public void listAllFiles() {
        File dir = new File("src/main/java");
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(19, files.length);
    }

    @Test
    public void listFile_anonInnerClass() {
        File dir = new File("src/main/java");
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".java");
            }
        });

        assertEquals(8, files.length);
    }

    @Test
    public void listFiles_lambda() {
        File dir = new File("src/main/java");
        File[] files = dir.listFiles(file -> file.getName().endsWith(".java"));

        assertEquals(8, files.length);
    }
}
