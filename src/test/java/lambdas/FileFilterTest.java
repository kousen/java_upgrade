package lambdas;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void list_files() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
    }

    @Test
    void list_directories_anon_inner_class() {
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
    void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(path -> path.isDirectory());
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] directories = root.listFiles(path -> {
            return path.isDirectory();
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void list_directories_assigned_to_variable() {
        FileFilter filter = path -> path.isDirectory();
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
    }
}
