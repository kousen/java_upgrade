package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.*;

public class FileFilterTest {
    @Test
    void listFiles() {
        File root = new File("src/main/java");
        File[] files = root.listFiles();
        assertEquals(21, files.length);
    }

    @Test
    void listFiles_anonInnerClass() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_lambda() {
        File root = new File("src/main/java");
        // 1. Are there listFiles methods that take a single argument?
        // 2. Are any of those single arguments functional interfaces?
        // 3. If so, is the provided lambda compatible with the
        //    single abstract method in the functional interface?
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] files = root.listFiles(filter);
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_methodReference() {
        File root = new File("src/main/java");
        FileFilter filter = File::isDirectory;
        File[] files = root.listFiles(filter);
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_javaSource() {
        File root = new File("src/main/java");
        File[] javaFiles = root.listFiles(pathname -> pathname.getName().endsWith(".java"));
        assertEquals(8, javaFiles.length);
    }

    @Test
    void throwNPE() {
        File root = new File("src/main/java/abcd");
        File[] files = root.listFiles();
        NullPointerException ex = assertThrows(NullPointerException.class,
                () -> assertEquals(21, files.length));
        assertNull(ex.getMessage());
    }
}
