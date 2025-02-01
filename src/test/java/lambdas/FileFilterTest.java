package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"Convert2MethodRef", "Convert2Lambda", "Anonymous2MethodRef"})
public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] dirs = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert dirs != null;
        assertEquals(14, dirs.length);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] dirs = root.listFiles(pathname -> pathname.isDirectory());
        assert dirs != null;
        assertEquals(14, dirs.length);
    }

    @Test
    void listDirectories_methodReference() {
        File[] dirs = root.listFiles(File::isDirectory);
        assert dirs != null;
        assertEquals(14, dirs.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] dirs = root.listFiles(pathname -> {
            System.out.println("Checking " + pathname);
            return pathname.isDirectory();
        });
        assert dirs != null;
        assertEquals(14, dirs.length);
    }

    @Test
    void listDirectories_assignToVariable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] dirs = root.listFiles(filter);
        assert dirs != null;
        assertEquals(14, dirs.length);
    }

    @Test
    void listJavaSrcFiles_fileNameFilter() {
        File[] javaSrcFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaSrcFiles != null;
        assertEquals(8, javaSrcFiles.length);
    }

    @Test
    void listJavaSrcFiles_fileFilter() {
        File[] javaSrcFiles = root.listFiles(file -> file.getName().endsWith(".java"));
        assert javaSrcFiles != null;
        assertEquals(8, javaSrcFiles.length);
    }

    @Test
    void consumerDemo() {
        List.of("this", "is", "a", "list", "of", "strings")
                .forEach(System.out::println);

    }

    @Test
    void mapForEach() {
        Map.ofEntries(Map.entry("one", 1), Map.entry("two", 2))
                .forEach((k, v) -> System.out.println(k + " -> " + v));
    }

}
