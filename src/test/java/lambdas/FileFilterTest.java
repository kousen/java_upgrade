package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void testListFilesNoArgs() {
        File[] files = root.listFiles();
        if (files != null) {
            assertEquals(20, files.length);
        }
    }

    @SuppressWarnings("Convert2Lambda")
    @Test
    void testListFilesAnonInnerClass() {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".java");
            }
        });
        if (files != null) {
            assertEquals(8, files.length);
        }
    }

    @Test
    void testListFilesLambdaAsArgument() {
        File[] files = root.listFiles(pathname -> pathname.getName().endsWith(".java"));
        if (files != null) {
            assertEquals(8, files.length);
        }
    }

    @Test
    void testListFilesLambdaWithVariable() {
        FileFilter javaFiles = pathname -> pathname.getName().endsWith(".java");
        File[] files = root.listFiles(javaFiles);
        if (files != null) {
            assertEquals(8, files.length);
        }
    }

    // @SuppressWarnings("Convert2MethodRef")
    @Test
    void testListFilesLambdaForDirectories() {
        File[] directories = root.listFiles(path -> {
            boolean isDir = path.isDirectory();
            System.out.println("File " + path.getName() + " "
                + (isDir ? "is" : "is not") + " a directory");
            return isDir;
        });
        if (directories != null) {
            assertEquals(12, directories.length);
        }
    }

    @Test
    void testListFilesMethodRefForDirectories() {
        File[] directories = root.listFiles(File::isDirectory);
        if (directories != null) {
            assertEquals(12, directories.length);
        }
    }
}
