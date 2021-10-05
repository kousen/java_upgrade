package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            assertEquals(21, files.length);
        }
    }

    @Test
    public void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
//            for (File dir : directories) {
//                System.out.println(dir);
//            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listDirectories_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listDirectories_variable() {
        // FileFilter filter = pathname -> pathname.isDirectory();
        FileFilter filter = File::isDirectory;
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listJavaSourceFiles() {
        File[] javaSrc = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaSrc != null) {
            assertEquals(8, javaSrc.length);
        }
    }

    @Test
    void forEachConsumerOfList() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(x -> System.out.println(x));
        strings.forEach(System.out::println);
    }

    @Test
    void forEachConsumerOfMap() {
        //Map<String, Integer> map = Map.ofEntries(Map.entry("a", 1), Map.entry("b", 2), Map.entry("c", 2));
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1); map.put("b", 2); map.put("c", 2);
        map.forEach((key, value) -> System.out.println(key + " map to " + value));
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "error message";
    }

    @Test // Lazy or deferred evaluation
    void notNullSupplier() {
        String s = "abc";
        assertNotNull(s, () -> getErrorMessage());
        assertNotNull(s, this::getErrorMessage);
    }
}
