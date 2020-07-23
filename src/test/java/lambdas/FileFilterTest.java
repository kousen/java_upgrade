package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        assertEquals(21, files.length);
    }

    @Test
    void listFiles_anonInnerClass() {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_expressionLambda() {
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_blockLambda() {
        File[] files = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_expressionLambda_parameterType() {
        File[] files = root.listFiles((File pathname) -> pathname.isDirectory());
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_fileNameFilter() {
        // find just the Java source files
        File[] files = root.listFiles((dir, name) -> name.endsWith(".java"));
        assertEquals(8, files.length);
    }

    @Test
    void listFiles_variable() {
        FileFilter filter = (File pathname) -> pathname.isDirectory();
        File[] files = root.listFiles(filter);
        assertEquals(13, files.length);
    }


}
