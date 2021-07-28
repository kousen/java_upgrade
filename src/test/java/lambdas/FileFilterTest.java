package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, files.length);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(13, files.length);
    }

    @Test
    void listDirectories_expressionLambdaWithParams() {
        File[] files = root.listFiles((File pathname) -> pathname.isDirectory());
        assertEquals(13, files.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] files = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assertEquals(13, files.length);
    }

    @Test
    void listDirectories_methodReference() {
        File[] files = root.listFiles(File::isDirectory);
        assertEquals(13, files.length);
    }

    @Test
    void listJavaSourceFiles() {
//        File[] javaFiles = root.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".java");
//            }
//        });
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaFiles != null) {
            assertEquals(8, javaFiles.length);
        }
    }

    @Test
    void listFactory() {
        List<String> strings = List.of("this", "is", "a", "list", "of", "strings");
        // strings.forEach(s -> System.out.println(s));
        strings.forEach(System.out::println);

        Map<String, Integer> map = Map.ofEntries(
                Map.entry("a", 1),
                Map.entry("b", 2),
                Map.entry("c", 2));
        map.forEach((k,v) -> System.out.println(k + " maps to " + v));
    }

    @Test
    void throwUOE() {
        List<String> strings = List.of("this", "is", "a", "list", "of", "strings");
        UnsupportedOperationException ex =
                assertThrows(UnsupportedOperationException.class,
                    () -> strings.add("abc"));
        System.out.println(ex.getMessage());
    }

    @Test
    void addingIntegers() {
        int total = 0;
        // Can NOT modify a local variable from inside a lambda
//        Stream.of(3, 1, 4, 1, 5, 9)
//                .forEach(n -> total += n);
        total = IntStream.of(3, 1, 4, 1, 5, 9).sum();
    }
}
