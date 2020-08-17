package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    @Test
    public void testListFiles() {
        File root = new File("src/main/java");
        File[] files = root.listFiles();
        if (files != null) {
            assertEquals(21, files.length);
        }
    }

    @Test
    public void listDirectories_anonInnerClass() {
        File root = new File("src/main/java");
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
    public void listDirectories_lambda() {
        File root = new File("src/main/java");
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listDirectories_lambdaVariable() {
        File root = new File("src/main/java");
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listDirectories_methodRef() {
        File root = new File("src/main/java");
        FileFilter filter = File::isDirectory;
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void iterateOverList() {
        List<String> strings = Arrays.asList("this", "is", "a", "list");
        // List<String> strings = List.of("this", "is", "a", "list");
        strings.forEach(s -> System.out.println("The string is " + s));
        strings.forEach(System.out::println);
    }
}
