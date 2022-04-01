package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            assertEquals(21, files.length);
        }
    }

    @Test
    void fileFilter_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, directories.length);
    }

    @Test
    void fileFilter_lambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(13, directories.length);
    }

    @Test
    void fileFilter_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        assertEquals(13, directories.length);
    }

    @Test
    void filenameFilter_javaSrc() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".java");
        File[] javaFiles = root.listFiles(filter);
        assertEquals(8, javaFiles.length);
    }

    @Test
    void showForEach() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        for (String string : strings) {
            System.out.println(string);
        }
        strings.forEach(s -> System.out.println(s));
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "error message";
    }

    @Test
    void testSupplier() {
        boolean b = true;
        assertTrue(b, this::getErrorMessage);
    }
}
