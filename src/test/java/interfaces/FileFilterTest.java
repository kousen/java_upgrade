package interfaces;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles_noFileFilter() {
        File[] files = root.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(22, files.length);
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
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_expressionLambda() {
        // File[] directories = root.listFiles((File pathname) -> pathname.isDirectory());
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_methodReference() {
        // File[] directories = root.listFiles((File pathname) -> pathname.isDirectory());
        File[] directories = root.listFiles(File::isDirectory);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @SuppressWarnings({"Convert2MethodRef", "CodeBlock2Expr"})
    @Test
    void listDirectories_blockLambda() {
        // File[] directories = root.listFiles((File pathname) -> pathname.isDirectory());
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @SuppressWarnings("Convert2Lambda")
    @Test
    void listJavaSrcFiles_filenameFilter_anonInnerClass() {
        File[] javaFiles = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @Test
    void listJavaSrcFiles_filenameFilter_expressionLambda() {
        File[] javaFiles = root.listFiles((directory, name) -> name.endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @Test
    void listJavaSrcFiles_fileFilter_expressionLambda() {
        File[] javaFiles = root.listFiles(file -> file.getName().endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void forEachWithConsumer() {
        Arrays.asList("this", "is", "a", "list", "of", "strings")
                .forEach(s -> System.out.println(s));  // forEach(Consumer<String>)
        Arrays.asList(LocalDate.now(), LocalDate.of(2023, Month.AUGUST, 1))
                .forEach(d -> System.out.println(d));  // forEach(Consumer<LocalDate>)
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void intBinaryOperator() {
        Stream.of(1, 2, 3, 4, 5)
                .reduce((x, y) -> x + y)
                .ifPresent(System.out::println);
        Stream.of(1, 2, 3, 4, 5)
                .reduce((x, y) -> {
                    System.out.printf("x=%d, y=%d%n", x, y);
                    return x + y;
                })
                .ifPresent(System.out::println);
        Stream.of(1, 2, 3, 4, 5)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
    }

    @Test
    void iterateOverAMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 2);
        map.forEach((k, v) -> System.out.printf("key=%s, value=%d%n", k, v));
    }
}
