package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef", "CodeBlock2Expr"})
public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(21, files.length);
    }

    @Test
    public void listFiles_fileFilter_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void listFiles_fileFilter_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_fileFilter_methodReference() {
        // File[] directories = root.listFiles((File pathname) -> pathname.isDirectory());
        File[] directories = root.listFiles(File::isDirectory);
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void listFiles_fileFilter_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void listFiles_fileFilter_assignedToVariable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_fileNameFilter() {
        // data types in lambda inferred from "context"
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void forEachInList() {
        // Lambda version
        Arrays.asList("this", "is", "a", "list", "of", "strings").forEach(s -> System.out.println(s));

        // Method reference version
        Arrays.asList("this", "is", "a", "list", "of", "strings").forEach(System.out::println);

        // Create a map to print
        Map<String,Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("too", 2);

        // Iterate over the set of keys
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + ": " + map.get(key));
        }

        // Iterate over the map entries
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // Iterate over the map using forEach(BiConsumer)
        map.forEach((key, value) -> System.out.println(key + " maps to " + value));
    }
}
