package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void testListFiles() {
        File[] files = root.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
        assertThat(files.length).isEqualTo(22);
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
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(file -> file.isDirectory());
        assert directories != null;
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] directories = root.listFiles(file -> {
            System.out.println("Checking " + file + " to see if it's a directory.");
            return file.isDirectory();
        });
        assert directories != null;
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listJavaSrcFiles_fileFilter() {
        File[] javaFiles = root.listFiles(file -> file.getName().endsWith(".java"));
        assert javaFiles != null;
        assertThat(javaFiles.length).isEqualTo(8);
    }

    @Test
    void listJavaSrcFiles_fileNameFilter() {
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaFiles != null;
        assertThat(javaFiles.length).isEqualTo(8);
    }

    @Test
    void forEachOnList() {
        List<String> strings = List.of("this", "is", "a", "list", "of", "strings");

        // Using the forEach(Consumer) method
        strings.forEach(s -> System.out.println("The next string is " + s));

        // For-each loop
        for (String s : strings) {
            System.out.println("The next string is " + s);
        }
    }
}
