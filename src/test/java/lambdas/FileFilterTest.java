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
        for (File file : files) {
            System.out.println(file);
        }
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, files.length);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(13, files.length);
    }

    @Test
    void listDirectories_expressionLambdaWithParams() {
        File[] files = root.listFiles((File pathname) -> pathname.isDirectory());
        assertEquals(13, files.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] files = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assertEquals(13, files.length);
    }

    @Test
    void listDirectories_methodReference() {
        File[] files = root.listFiles(File::isDirectory);
        assertEquals(13, files.length);
    }
}
