package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles_withoutFilter() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
            assertEquals(21, files.length);
        }
    }

    @Test
    void listFiles_withFileFilter_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file.getName());
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listFiles_withFileFilter_expressionLambda() {
        FileFilter fileFilter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(fileFilter);
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file.getName());
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listFiles_withFilenameFilter() {
        File[] javaSourceFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaSourceFiles != null) {
            assertEquals(8, javaSourceFiles.length);
        }
    }

}
