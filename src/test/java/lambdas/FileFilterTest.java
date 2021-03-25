package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

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

    @Test
    void listDirectories_methodReference() {
        File dir = new File(root);
        File[] directories = dir.listFiles(File::isDirectory);
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void useForEachInIterable() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        for (String string : strings) {
            System.out.println(string);
        }

        strings.forEach(s -> System.out.println(s));  // lambda impl of Consumer
        strings.forEach(System.out::println);     // method ref impl of Consumer
    }
}
