package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listAllFiles() {
        File[] files = root.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(21, files.length);
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
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectories_expressionLambda() {
        // if there is only one param and you don't specify the type, you can leave out the ()
        File[] directories = root.listFiles(file -> file.isDirectory());
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectories_blockLambda() {
        // if there is only one param and you don't specify the type, you can leave out the ()
        File[] directories = root.listFiles(file -> {
            System.out.println("Checking file: " + file);
            return file.isDirectory();
        });
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listJavaSrcFiles() {
        File[] javaSrc = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaSrc != null;
        assertEquals(8, javaSrc.length);
    }

    @Test
    void listJavaSrcFiles_asVariable() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".java");
        File[] javaSrc = root.listFiles(filter);
        assert javaSrc != null;
        assertEquals(8, javaSrc.length);
    }
}
