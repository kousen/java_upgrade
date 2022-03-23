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

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles_withoutFilter() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
            }
            assertEquals(21, files.length);
        }
    }

    @Test
    void listFiles_withFileFilter_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file.getName());
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listFiles_withFileFilter_expressionLambda() {
        FileFilter fileFilter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(fileFilter);
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file.getName());
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listFiles_withFileFilter_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file.getName());
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listFiles_withFilenameFilter() {
        File[] javaSourceFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaSourceFiles != null) {
            assertEquals(8, javaSourceFiles.length);
        }
    }

    @Test
    void printEachElementOfList() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(word -> System.out.println("The next word is " + word));
        strings.forEach(System.out::println);
    }

    @Test
    void iterateOverMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1); map.put("b", 2); map.put("c", 2);
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }

        for (String key : map.keySet()) {
            System.out.println(key + " maps " + map.get(key));
        }

        map.forEach((k,v) -> System.out.println(k + " : " + v));
    }
}
