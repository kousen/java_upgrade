package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
        assertThat(files.length).isEqualTo(22);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert directories != null;
        assertEquals(14, directories.length);
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listDirectories_expressionLambda() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listDirectories_methodReference() {
        FileFilter filter = File::isDirectory;
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listDirectories_blockLambda() {
        FileFilter filter = pathname -> {
            return pathname.isDirectory();
        };
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
        assertThat(directories.length).isEqualTo(14);
    }

    // Add a test that uses a FileFilter to list only .java files
    @Test
    void listJavaSrcFiles_fileFilter() {
        FileFilter filter = pathname -> pathname.getName().endsWith(".java");
        File[] javaFiles = root.listFiles(filter);
        assert javaFiles != null;
        assertThat(javaFiles.length).isEqualTo(8);
    }

    // Add a test that uses a FilenameFilter to list only .java files
    @Test
    void listJavaSrcFiles_filenameFilter() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".java");
        File[] javaFiles = root.listFiles(filter);
        assert javaFiles != null;
        assertThat(javaFiles.length).isEqualTo(8);
    }

    @Test
    void iterateOverMap() {
        Map.ofEntries(
                Map.entry("a", 1),
                Map.entry("b", 2),
                Map.entry("c", 3)
        ).forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
