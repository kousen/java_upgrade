package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTests {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(21, files.length);
    }

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectories_expressionLambda() {
        // File[] directories = root.listFiles(file -> file.isDirectory());
        File[] directories = root.listFiles(File::isDirectory);
        assertEquals(13, directories.length);
    }

    @SuppressWarnings({"Convert2MethodRef", "CodeBlock2Expr"})
    @Test
    void listDirectories_blockLambda() {
        File[] directories = root.listFiles(file -> {
            return file.isDirectory();
        });
        assertEquals(13, directories.length);
    }

    @Test
    void listJavaSourceFiles_fileFilter() {
        File[] javaSrc = root.listFiles(file -> file.getName().endsWith(".java"));
        assertEquals(8, javaSrc.length);
    }

    @Test
    void listJavaSourceFiles_fileFilterVariable() {
        FileFilter filter = file -> file.getName().endsWith(".java");
        File[] javaSrc = root.listFiles(filter);
        assertEquals(8, javaSrc.length);
    }

    @Test
    void listJavaSourceFiles_filenameFilter() {
        File[] javaSrc = root.listFiles((dir, name) -> name.endsWith(".java"));
        assertEquals(8, javaSrc.length);
    }

    @Test
    void useIterableWithConsumer() {
        List<String> strings = Arrays.asList("this", "is", "a", "list");
        // traditional for-each loop
        for (String string : strings) {
            System.out.println(string);
        }

        // new forEach method that takes a consumer
        // strings.forEach(string -> System.out.println(string));
        strings.forEach(System.out::println);

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 2);

        // iterate using key set
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }

        // iterate using entry set
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // iterate using biconsumer
        map.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
