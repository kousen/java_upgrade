package lambdas;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private String root = "src/main/java";

    @Test
    public void listFilesNoArgs() {
        File f = new File(root);
        File[] files = f.listFiles();
        assertEquals(20, files.length);
    }

    @Test
    public void listJavaFiles_anonInnerClass() {
        File f = new File(root);
        File[] files = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".java");
            }
        });

        assertEquals(8, files.length);
    }

    @Test
    public void listJavaFiles_lambda() {
        File f = new File(root);
        FileFilter filter = path -> path.getName().endsWith(".java");
        File[] files = f.listFiles(filter);

        assertEquals(8, files.length);
    }

    @Test
    public void listDirectories() {
        File f = new File(root);
        // File[] directories = f.listFiles(pathname -> pathname.isDirectory());
        File[] directories = f.listFiles(File::isDirectory);  // method reference
        assertEquals(12, directories.length);
    }

    @Test
    void forEachOnIterable() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(s -> System.out.println("The word is " + s));
        strings.forEach(System.out::println);
    }
}
