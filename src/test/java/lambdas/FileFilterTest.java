package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles_withoutFilter() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File f : files) {
                System.out.println(f);
            }
            assertEquals(21, files.length);
        }
    }

    @Test
    public void listFiles_withFileFilter_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();  // only directories
            }
        });
        if (directories != null) {
            for (File f : directories) {
                System.out.println(f);
            }
            assertEquals(13, directories.length);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void listFiles_withFileFilter_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        if (directories != null) {
            for (File f : directories) {
                System.out.println(f);
            }
            assertEquals(13, directories.length);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void listFiles_withFileFilter_blocLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            for (File f : directories) {
                System.out.println(f);
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listFiles_withFileFilter_methodReference() {
        // method reference
        File[] directories = root.listFiles(File::isDirectory);
        if (directories != null) {
            for (File f : directories) {
                System.out.println(f);
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listFiles_withFileNameFilter_expressionLambda() {
        File[] javaSourceFiles = root.listFiles((pathname, name) -> name.endsWith(".java"));
        if (javaSourceFiles != null) {
            for (File f : javaSourceFiles) {
                System.out.println(f);
            }
            assertEquals(8, javaSourceFiles.length);
        }
    }
}
