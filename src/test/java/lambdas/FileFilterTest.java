package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FileFilterTest {
    @Test
    void listFiles() {
        File root = new File("src/main/java");
        File[] files = root.listFiles();
        assertEquals(21, files.length);
    }

    @Test
    void listFiles_anonInnerClass() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_lambda() {
        File root = new File("src/main/java");
        // 1. Are there listFiles methods that take a single argument?
        // 2. Are any of those single arguments functional interfaces?
        // 3. If so, is the provided lambda compatible with the
        //    single abstract method in the functional interface?
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] files = root.listFiles(filter);
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_methodReference() {
        File root = new File("src/main/java");
        FileFilter filter = File::isDirectory;
        File[] files = root.listFiles(filter);
        assertEquals(13, files.length);
    }

    @Test
    void listFiles_javaSource() {
        File root = new File("src/main/java");
        File[] javaFiles = root.listFiles(pathname -> pathname.getName().endsWith(".java"));
        assertEquals(8, javaFiles.length);
    }

    @Test
    void throwNPE() {
        File root = new File("src/main/java/abcd");
        File[] files = root.listFiles();
        NullPointerException ex = assertThrows(NullPointerException.class,
                () -> assertEquals(21, files.length));
        assertNull(ex.getMessage());
    }

    @Test
    void printValuesInCollection() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        System.out.println(strings);
        strings.forEach(str -> System.out.println(str + " has length " + str.length()));
        strings.forEach(System.out::println);
    }

    @Test
    void showOrderingEvenInParallel() {
        List<Integer> ints = Arrays.asList(3, 1, 4, 1, 5, 9);

        List<Integer> doubles = ints.parallelStream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
        System.out.println(doubles);

        // Legal, but not a good idea (shared mutable state, not ordered)
        List<Integer> doubleCollection = new ArrayList<>();
        ints.parallelStream()
                .map(n -> n * 2)
                .forEach(doubleCollection::add);
        System.out.println(doubleCollection);
    }

    @Test
    void iterateOverAMap() {
        Map<String, Integer> map = new HashMap<>();
        // In Java 11:
        // Map.ofEntries(Map.entry("a", 1), Map.entry("b", 2), Map.entry("c", 2));
        // unmodifiable map --> no way to add, remove, or replace elements
        map.put("hello", 5);
        map.put("world", 5);
        map.put("what up?", 8);
        map.forEach((k, v) -> map.put(k, 42));
    }
}
