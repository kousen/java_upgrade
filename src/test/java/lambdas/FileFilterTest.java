package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {

    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
    }

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            System.out.println("Checking " + pathname);
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listJavaSrcFiles_fileFilter() {
        File[] javaSrcFiles = root.listFiles(pathname -> pathname.getName().endsWith(".java"));
        assert javaSrcFiles != null;
        assertEquals(8, javaSrcFiles.length);
    }

    @Test
    void listJavaSrcFiles_fileNameFilter() {
        File[] javaSrcFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaSrcFiles != null;
        assertEquals(8, javaSrcFiles.length);
    }

    @Test
    void listJavaSrcFiles_assignToVariable() {
        FilenameFilter filter = (File dir, String name) -> name.endsWith(".java");
        File[] javaSrcFiles = root.listFiles(filter);
        assert javaSrcFiles != null;
        assertEquals(8, javaSrcFiles.length);
    }
}
