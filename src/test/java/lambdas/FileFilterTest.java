package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileFilterTest {
    private File root = new File("src/main/java");

    @Test
    public void listAllFiles() {
        File[] files = root.listFiles();

        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(19, files.length);
    }

    @Test
    public void listDirectories_anonInnerClass() {
        // Java 5, 6, 7
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(11, files.length);
    }

    @Test
    public void listDirectories_lambda() {
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(11, files.length);
    }

    @Test
    public void listDirectories_methodReference() {
        File[] files = root.listFiles(File::isDirectory);
        assertEquals(11, files.length);
    }

    @Test
    public void listJavaSourceFiles() {
        File[] files = root.listFiles(pathname -> pathname.getName().endsWith(".java"));
        assertEquals(8, files.length);
        for (File file : files) {
            assertTrue(file.getName().endsWith(".java"));
        }

    }
}
