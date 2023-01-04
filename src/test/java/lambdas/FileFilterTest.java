package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(21, files.length);
        }
    }

    @Test
    void listFiles_directories_anoninnerclass() {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_directories_expressionlambda() {
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_directories_blocklambda() {
        File[] files = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_javaSrcFiles() {
        File[] javaSrcFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaSrcFiles != null) {
            assertEquals(8, javaSrcFiles.length);
        }
    }

}
