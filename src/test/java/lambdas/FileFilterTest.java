package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(21, files.length);
        }
    }

    @Test
    void listFiles_directories_anoninnerclass() {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_directories_expressionlambda() {
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_directories_blocklambda() {
        File[] files = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_javaSrcFiles() {
        File[] javaSrcFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaSrcFiles != null) {
            assertEquals(8, javaSrcFiles.length);
        }
    }

    @Test
    void iterateUsingForEach() {
        List<String> strings = Arrays.asList("one", "two", "three");
        strings.forEach(entry -> System.out.println(entry));

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 2);
        // In Java 9+, you could write:
        // map = Map.ofEntries(Map.entry("a", 1), Map.entry("b", 2), Map.entry("c", 2));
        map.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
