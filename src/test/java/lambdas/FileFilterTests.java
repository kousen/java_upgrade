package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTests {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(21, files.length);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(file -> file.isDirectory());
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] directories = root.listFiles(file -> {
            return file.isDirectory();
        });
        assertEquals(13, directories.length);
    }

    @Test
    void listJavaSourceFiles_fileFilter() {
        File[] javaSrc = root.listFiles(file -> file.getName().endsWith(".java"));
        assertEquals(8, javaSrc.length);
    }

    @Test
    void listJavaSourceFiles_fileFilterVariable() {
        FileFilter filter = file -> file.getName().endsWith(".java");
        File[] javaSrc = root.listFiles(filter);
        assertEquals(8, javaSrc.length);
    }

    @Test
    void listJavaSourceFiles_filenameFilter() {
        File[] javaSrc = root.listFiles((dir, name) -> name.endsWith(".java"));
        assertEquals(8, javaSrc.length);
    }
}
