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
    public void listFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(21, files.length);
        }
    }

    @Test
    void listOnlyDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_lambdaAsVariable() {
        FileFilter filter = (File pathname) -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyJavaSourceFiles_fileNameFilter() {
        File[] javaSource = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaSource != null) {
            assertEquals(8, javaSource.length);
        }
    }

    @Test
    void iterableWithForEach() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        for (String s : strings) {
            System.out.println(s);
        }

        strings.forEach(s -> System.out.println("s = " + s));
        strings.forEach(System.out::println);
    }

    @Test
    void iterateOverMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 2);

        // keySet to iterate
        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }

        // entrySet to iterate
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " maps to " + entry.getValue());
        }

        // Use forEach in Map (default method)
        map.forEach((k, v) -> System.out.println(k + " to " + v));
    }
}
