package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
    }

    @Test
    void listDirectories_fileFilter_anoninnerclass() {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
    }

    @Test
    void listDirectories_fileFilter_expressionlambda() {
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        assert files != null;
        assertEquals(2, files.length);
    }

    @Test
    void listDirectories_fileNameFilter_expressionlambda() {
        File[] files = root.listFiles((dir, name) -> new File(dir, name).isDirectory());
        assert files != null;
        assertEquals(2, files.length);
    }

    @Test
    void listDirectories_fileFilter_variable() {
        FileFilter fileFilter = (File pathname) -> pathname.isDirectory();
        File[] files = root.listFiles(fileFilter);
        assert files != null;
        assertEquals(2, files.length);
    }

    @Test
    void listDirectories_fileFilter_methodReference() {
        File[] files = root.listFiles(File::isDirectory);
        assert files != null;
        assertEquals(2, files.length);
    }

    @Test
    void iterateOverMap() {
        Map.of("a", 1, "b", 2, "c", 2)
                .forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    @Test
    void iterateOverList() {
        List.of("this", "is", "a", "list")
                .forEach(System.out::println);

    }
}
