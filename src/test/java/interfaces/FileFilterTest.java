package interfaces;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles_noFileFilter() {
        File[] files = root.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
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
        // File[] directories = root.listFiles((File pathname) -> pathname.isDirectory());
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @SuppressWarnings({"Convert2MethodRef", "CodeBlock2Expr"})
    @Test
    void listDirectories_blockLambda() {
        // File[] directories = root.listFiles((File pathname) -> pathname.isDirectory());
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }
}
