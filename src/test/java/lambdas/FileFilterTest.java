package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileFilterTest {
    private final File root = new File("src/main/java");
    private final Logger logger = Logger.getLogger(FileFilterTest.class.toString());

    private String logMessage() {
        System.out.println("Inside logMessage");
        return "log message";
    }

    @Test
    void loggerWithSupplier() {
        logger.fine(logMessage()); // invoke method, even though not seen
        logger.fine(() -> logMessage()); // don't invoke method, because not seen
    }

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(21, files.length);
        }
    }

    @Test
    public void listDirectoriesAnonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void listDirectoriesExpressionLambda() {
        File[] directories = root.listFiles(file -> file.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void listDirectoriesBlockLambda() {
        File[] directories = root.listFiles(file -> {
            return file.isDirectory();
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void listDirectoriesUsingVariable() {
        FileFilter filter = file -> file.isDirectory();
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listJavaSourceFiles() {
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaFiles != null) {
            assertEquals(8, javaFiles.length);
        }
    }

    @Test
    public void listDirectoriesMethodReference() {
        // File[] directories = root.listFiles(file -> file.isDirectory());
        File[] directories = root.listFiles(File::isDirectory);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void forEachWithAConsumer() {
        List<String> strings = Arrays.asList("this", "is", "a", "list");
        strings.forEach(System.out::println);
    }

    @Test
    void forEachWithABiConsumer() {
        Map<String,Integer> map = new HashMap<>();
        map.put("a", 1); map.put("b", 2); map.put("c", 2);
        map.forEach((key,value) -> System.out.println(key + " maps to " + value));
    }

    private String getErrorMessage() {
        System.out.println("Inside getErroMessage");
        return "error message";
    }

    @Test
    void assertNotNullWithError() {
        // retrieve the error message only if the test fails
        assertNotNull("abc", () -> getErrorMessage());
    }


}
