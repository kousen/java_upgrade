package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(21, files.length);
    }

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    void listDirectories_anonInnerClass() {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        assertEquals(13, files.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_expressionLambda() {
        File[] files = root.listFiles(file -> file.isDirectory());
        assertEquals(13, files.length);
    }

    @SuppressWarnings({"Convert2MethodRef", "CodeBlock2Expr"})
    @Test
    void listDirectories_blockLambda() {
        File[] files = root.listFiles(file -> {
            return file.isDirectory();
        });
        assertEquals(13, files.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_assignToVariable() {
        FileFilter filter = file -> file.isDirectory();
        File[] files = root.listFiles(filter);
        assertEquals(13, files.length);
    }

    @Test
    void javaSrcFiles_fileFilter() {
        File[] files = root.listFiles(file -> file.getName().endsWith(".java"));
        assertEquals(8, files.length);
    }

    @Test
    void javaSrcFiles_filenameFilter() {
        File[] files = root.listFiles((dir, name) -> name.endsWith(".java"));
        assertEquals(8, files.length);
    }
}
