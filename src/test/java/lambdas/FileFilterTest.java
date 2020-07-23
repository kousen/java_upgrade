package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void listFiles_anonInnerClass() {
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
    void listFiles_expressionLambda() {
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_methodReference() {
        File[] files = root.listFiles(File::isDirectory);
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_blockLambda() {
        File[] files = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_expressionLambda_parameterType() {
        File[] files = root.listFiles((File pathname) -> pathname.isDirectory());
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_fileNameFilter() {
        // find just the Java source files
        File[] files = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (files != null) {
            assertEquals(8, files.length);
        }
    }

    @Test
    void listFiles_variable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        // FileFilter filter = File::isDirectory;
        File[] files = root.listFiles(filter);
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void printElements() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(s -> System.out.println(s + " is the name of the file"));
        strings.forEach(System.out::println);
    }
}
