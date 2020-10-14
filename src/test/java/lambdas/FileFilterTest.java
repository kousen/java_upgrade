package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {

    @Test
    public void testListFiles() {
        File root = new File("src/main/java");
        File[] files = root.listFiles();
        if (files != null) {
            assertEquals(21, files.length);
        }
    }

    @Test
    void listDirectories_anonInnerClass() {
        File root = new File("src/main/java");
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listDirectories_expression_lambda() {
        File root = new File("src/main/java");
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_block_lambda() {
        File root = new File("src/main/java");
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listDirectories_method_reference() {
        File root = new File("src/main/java");
        File[] directories = root.listFiles(File::isDirectory); // Method reference
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

}
