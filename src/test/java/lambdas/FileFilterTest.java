package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

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
    void listOnlyDirectories_anonInnerClass() {
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

    @Test
    void listOnlyDirectories_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyDirectories_lambdaAsVariable() {
        FileFilter filter = (File pathname) -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listOnlyJavaSourceFiles_fileNameFilter() {
        File[] javaSource = root.listFiles((dir, name) -> name.endsWith(".java"));
        if (javaSource != null) {
            assertEquals(8, javaSource.length);
        }
    }

    @Test
    void iterableWithForEach() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        for (String s : strings) {
            System.out.println(s);
        }

        strings.forEach(s -> System.out.println("s = " + s));
        strings.forEach(System.out::println);
    }
}
