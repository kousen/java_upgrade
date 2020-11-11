package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void testListFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            assertEquals(21, files.length);
        }
    }

    @Test
    void testListFiles_anonInnerClass() {
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
    void testListFiles_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void testListFiles_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void testListFiles_assignToVariable() {
        FileFilter filter = (File pathname) -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void testListFiles_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listListFile_fileNameFilter() {
        File[] javaSourceFiles = root.listFiles(((dir, name) -> name.endsWith(".java")));
        if (javaSourceFiles != null) {
            assertEquals(8, javaSourceFiles.length);
        }
    }
}
