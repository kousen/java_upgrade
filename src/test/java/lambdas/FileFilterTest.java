package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File dir = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = dir.listFiles();
        assert files != null;
        assertEquals(22, files.length);
    }

    @Test
    void listJavaFiles_anonInnerClass() {
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".java");
            }
        });
        assert files != null;
        assertEquals(8, files.length);
    }

    @Test
    void listJavaFiles_expressionLambda() {
        File[] files = dir.listFiles(pathname -> pathname.getName().endsWith(".java"));
        assert files != null;
        assertEquals(8, files.length);
    }

    @Test
    void listDirectories() {
        File[] files = dir.listFiles(File::isDirectory);
        assert files != null;
        assertEquals(14, files.length);
    }

    @Test
    void listJavaFiles_blockLambda() {
        File[] files = dir.listFiles(pathname -> {
            System.out.println("Checking " + pathname.getName());
            return pathname.getName().endsWith(".java");
        });
        assert files != null;
        assertEquals(8, files.length);
    }

    @Test
    void listJavaFiles_assignedToVariable() {
        FileFilter filter = pathname -> pathname.getName().endsWith(".java");
        File[] files = dir.listFiles(filter);
        assert files != null;
        assertEquals(8, files.length);
    }

    @Test
    void listJavaFiles_fileNameFilter() {
        File[] files = dir.listFiles((dir, name) -> name.endsWith(".java"));
        assert files != null;
        assertEquals(8, files.length);
    }


}
