package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles_withoutFilter() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
        }
    }

    @Test
    public void listFiles_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file);
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listFiles_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        //File[] directories = root.listFiles((File pathname) -> pathname.isDirectory());
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file);
            }
            assertEquals(13, directories.length);
        }
    }

    @SuppressWarnings({"CodeBlock2Expr", "Convert2MethodRef"})
    @Test
    public void listFiles_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file);
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listJavaSrcFiles() {
        FilenameFilter filter = (directory, fileName) -> fileName.endsWith(".java");
        File[] javaSrcFiles = root.listFiles(filter);
        if (javaSrcFiles != null) {
            for (File file : javaSrcFiles) {
                System.out.println(file);
            }
            assertEquals(8, javaSrcFiles.length);
        }
    }
}
