package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles_noFilter() {
        File[] files = root.listFiles();
        assert files != null;
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
        // File[] directories = root.listFiles(file -> file.isDirectory());
        File[] directories = root.listFiles(File::isDirectory);
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] directories = root.listFiles(file -> {
            System.out.println("Checking " + file.getName());
            return file.isDirectory();
        });
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listJavaSrcFiles() {
        File[] javaSrc = root.listFiles((dir, name) -> {
            System.out.println("Root directory is " + dir + ", checking " + name);
            return name.endsWith(".java");
        });
        assert javaSrc != null;
        assertEquals(8, javaSrc.length);
    }

    @Test
    void forEach_list() {
        // Java 8: Arrays.asList(T...) --> "fixed size" list
        // Java 9: List.of(T...) --> "unmodifiable" list

        List<String> strings = Arrays.asList("a", "b", "c");

        // for-each loop
        for (String s: strings) {
            System.out.println(s);
        }

        // default forEach(Consumer) method
        strings.forEach(System.out::println);
    }
}
