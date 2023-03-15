package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

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
    void listDirectories_anonInnerClass() {
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
    void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectories_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listJavaSrcFiles() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".java");
        File[] javaSrcFiles = root.listFiles(filter);
        assert javaSrcFiles != null;
        assertEquals(8, javaSrcFiles.length);
    }

    @Test
    void forEach() {
        List.of("this", "is", "a", "list")
                .forEach(x -> System.out.println("x = " + x));


        Logger logger = Logger.getLogger(FileFilterTest.class.getName());
        IntStream.iterate(0, x -> x + 1)
                .limit(10)
                .peek(x -> logger.info(() -> "x = " + x))
                .forEach(x -> System.out.println("x = " + x));
    }
}
