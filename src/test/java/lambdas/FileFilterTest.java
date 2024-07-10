package lambdas;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

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
    void list_directories_expression_lambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void list_directories_block_lambda() {
        File[] directories = root.listFiles(pathname -> {
            System.out.println("Checking " + pathname.getName());
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void list_directories_variable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void list_directories_method_references() {
        File[] directories = root.listFiles(File::isDirectory);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void list_java_src_files_file_filter() {
        FileFilter filter = pathname -> pathname.getName().endsWith(".java");
        File[] javaFiles = root.listFiles(filter);
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @Test
    void list_java_src_files_file_name_filter() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".java");
        File[] javaFiles = root.listFiles(filter);
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }


}
