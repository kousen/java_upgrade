package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {

    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
//        for (File file : files) {
//            System.out.println(file);
//        }
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
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_expressionLambdaWithVariable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_blockLambda() {
        File[] directories = root.listFiles((File pathname) -> {
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
    void printListUsingConsumer() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(s -> System.out.println(s));
        strings.forEach(s -> System.out.println("The string is " + s));
        strings.forEach(System.out::println);
    }
}
