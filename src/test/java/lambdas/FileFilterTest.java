package lambdas;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"Convert2MethodRef", "Convert2Lambda", "Anonymous2MethodRef"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
    }

    @Test
    void listDirectories_anon_inner_class() {
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
    void listDirectories_expression_lambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_method_reference() {
        File[] directories = root.listFiles(File::isDirectory);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_block_lambda() {
        File[] directories = root.listFiles(pathname -> {
            System.out.println("Checking " + pathname.getName());
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_assigned_variable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void list_java_src_files_fileFilter() {
        File[] javaFiles = root.listFiles(pathname -> pathname.getName().endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @Test
    void list_java_src_files_filenameFilter() {
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }
}
