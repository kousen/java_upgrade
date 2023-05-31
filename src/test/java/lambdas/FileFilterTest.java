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
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(21, files.length);
    }

    @Test
    void listDirectoriesWithFileFilter_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectoriesWithFileFilter_expressionLambda() {
        File[] directories = root.listFiles(path -> path.isDirectory());
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectoriesWithFileFilter_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            System.out.println("Checking whether " + pathname + " is a directory");
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listJavaSrcFiles_anonInnerClass() {
        File[] javaSourceFiles = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        assert javaSourceFiles != null;
        assertEquals(8, javaSourceFiles.length);
    }

    @Test
    void listJavaSrcFiles_lambda() {
        File[] javaSourceFiles = root.listFiles((dir, name) -> {
            System.out.println("Checking whether " + name + " in " +
                               dir + " is a Java source file");
            return name.endsWith(".java");
        });
        assert javaSourceFiles != null;
        assertEquals(8, javaSourceFiles.length);
    }

    @Test
    void listJavaSrcFiles_assignedToVariable() {
        FilenameFilter filter = (directory, fileName) -> fileName.endsWith(".java");
        File[] javaSourceFiles = root.listFiles(filter);
        assert javaSourceFiles != null;
        assertEquals(8, javaSourceFiles.length);
    }
}
