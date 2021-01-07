package lambdas;

import org.junit.jupiter.api.Test;

import java.awt.*;
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
        assertEquals(21, files.length);
    }

    @Test
    void listOnlyDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_lambdaAsVariable() {
        FileFilter filter = (File pathname) -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyJavaSourceFiles_fileNameFilter() {
        File[] javaSource = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaSource != null) {
            assertEquals(8, javaSource.length);
        }
    }
}
