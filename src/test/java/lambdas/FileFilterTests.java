package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTests {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(21, files.length);
        assertThat(files).hasSize(21);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        assertThat(directories).hasSize(13);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(file -> file.isDirectory());
        assertThat(directories).hasSize(13);
    }

    @Test
    void listJavaSourceFiles_fileFilter() {
        FileFilter filter = file -> file.getName().endsWith(".java");
        File[] javaFiles = root.listFiles(filter);
        assertThat(javaFiles).hasSize(8);
    }

    @Test
    void listJavaSourceFiles_fileNameFilter() {
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assertThat(javaFiles).hasSize(8);
    }
}
