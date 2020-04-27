package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {

    @Test
    void listFilesWithNoFilter() {
        File root = new File("src/main/java");
        File[] files = root.listFiles();
        assertEquals(20, files.length);
    }

    @Test
    void listJavaFiles_anonInnerClass() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".java");
            }
        });
        assertEquals(8, files.length);
    }

    @Test
    void listJavaFiles_expressionLambda() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(pathname -> pathname.getName().endsWith(".java"));
        assertEquals(8, files.length);
    }

    @Test
    void listJavaFiles_blockLambda() {
        File root = new File("src/main/java");
        File[] files = root.listFiles((File pathname) -> {
            return pathname.getName().endsWith(".java");
        });
        assertEquals(8, files.length);
    }
}
