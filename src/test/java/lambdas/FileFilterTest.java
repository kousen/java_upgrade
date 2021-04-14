package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
        assertEquals(21, files.length);
    }

    @Test
    public void listFiles_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_expressionLambda() {
        FileFilter fileFilter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(fileFilter);
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assertEquals(13, directories.length);
    }

    @Test
    public void listJavaSourceFiles() {
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assertEquals(8, javaFiles.length);
    }

    @Test
    public void listFiles_methodRef() {
        File[] directories = root.listFiles(File::isDirectory);
        assertEquals(13, directories.length);
    }

    @Test
    void showForEachOnAnIterable() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(string -> System.out.println("the string is " + string));
        strings.forEach(System.out::println);
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "This is my error message";
    }

    @Test
    void myTest() {
        assertTrue(true, getErrorMessage());
    }

    @Test
    void myLazyTest() {
        assertTrue(true, () -> getErrorMessage());
    }
}
