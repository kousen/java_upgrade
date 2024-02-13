package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static java.util.Map.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertThat(files.length).isEqualTo(22);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new java.io.FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        assert directories != null;
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(file -> file.isDirectory());
        assert directories != null;
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listJavaSrcFiles_fileFilter() {
        File[] javaSrcFiles = root.listFiles(file -> file.getName()
                .endsWith(".java"));
        assert javaSrcFiles != null;
        assertThat(javaSrcFiles.length).isEqualTo(8);
    }

    @Test
    void listJavaSrcFiles_fileNameFilter() {
        File[] javaSrcFiles = root.listFiles((File dir, String name) -> name.endsWith(".java"));
        assert javaSrcFiles != null;
        assertThat(javaSrcFiles.length).isEqualTo(8);
    }

    @Test
    void listJavaSrcFiles_fileFilter_variable() {
        FileFilter filter = (File file) -> file.getName()
                .endsWith(".java");
        File[] javaSrcFiles = root.listFiles(filter);
        assert javaSrcFiles != null;
        assertThat(javaSrcFiles.length).isEqualTo(8);
    }

    @Test
    void forEachMap() {
        ofEntries(
                entry("a", 1),
                entry("b", 2),
                entry("c", 2))
                .forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
