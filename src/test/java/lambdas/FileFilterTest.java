package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
    }

    @Test
    public void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    public void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    public void listDirectories_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            System.out.println("Checking: " + pathname.getName());
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    public void listDirectories_assignToVariable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    public void listJavaSrcFiles_fileFilter() {
        File[] javaFiles = root.listFiles(pathname -> pathname.getName().endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @Test
    public void listJavaSrcFiles_fileNameFilter() {
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @Test
    void iterate_over_map() {
        var map = Map.of("a", 1, "b", 2, "c", 3);
        map.forEach((k, v) -> System.out.println(k + " -> " + v));

        var list = List.of("a", "b", "c");
        list.forEach(x -> System.out.println(x));
    }

}
