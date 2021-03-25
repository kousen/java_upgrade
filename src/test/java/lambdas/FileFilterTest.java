package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {
    private final String root = "src/main/java";

    @Test
    public void listFiles() {
        File dir = new File(root);
        File[] files = dir.listFiles();
        assertEquals(21, files.length);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File dir = new File(root);
        File[] directories = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_expressionLambda() {
        File dir = new File(root);
        File[] directories = dir.listFiles(pathname -> pathname.isDirectory());
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_blockLambda() {
        File dir = new File(root);
        File[] directories = dir.listFiles((File pathname) -> {
            return pathname.isDirectory();
        });
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_variable() {
        File dir = new File(root);
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = dir.listFiles(filter);
        assertEquals(13, directories.length);
    }
}
