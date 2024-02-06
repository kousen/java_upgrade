package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listAllFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(file -> file.isDirectory());
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] directories = root.listFiles(file -> {
            System.out.println("Checking " + file.getName());
            return file.isDirectory();
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_fileFilterVariable() {
        FileFilter filter = (File file) -> file.isDirectory();
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listJavaSourceFiles() {
        // FilenameFilter filenameFilter = (File dir, String name) -> name.endsWith(".java");
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }
}
