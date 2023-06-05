package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    void listDirectories_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
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

    @Test
    void iterateOverMap() {
        // LVTI -- Local Variable Type Inference (infer at compile time)
        // Collection factory methods (List.of, Set.of, Map.of, Map.ofEntries)
        //   added in Java 9
        // var map = Map.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1); map.put("b", 2); map.put("c", 3);
        map.forEach((k, v) -> System.out.println(k + " -> " + v));
        assertThat(map).hasSize(3);

        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(s -> System.out.println(s));
        strings.forEach(System.out::println);
    }
}
