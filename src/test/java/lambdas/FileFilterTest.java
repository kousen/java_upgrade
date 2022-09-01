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
    public void listFiles_noFilter() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(21, files.length);
    }

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    public void listFiles_fileFilterAnonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void listFiles_fileFilter_expressionLambda() {
        //File[] directories = root.listFiles((File pathname) -> pathname.isDirectory());
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @SuppressWarnings({"CodeBlock2Expr", "Convert2MethodRef"})
    @Test
    public void listFiles_fileFilter_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @SuppressWarnings({"Convert2MethodRef"})
    @Test
    public void listFiles_fileFilter_assignedToVariable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_fileFilter_methodReference() {
        FileFilter filter = File::isDirectory;
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(13, directories.length);
    }

    @Test
    void listFiles_javaSrc() {
        File[] javaSourceFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaSourceFiles != null;
        assertEquals(8, javaSourceFiles.length);
    }

    @Test
    void iterateOverCollection() {
        List<String> strings = Arrays.asList("a", "b", "c");
        // iterate over list using for-each loop
        for (String s : strings) {
            System.out.println(s);
        }

        // iterate over list using forEach method (new default method in Iterable)
        // strings.forEach(s -> System.out.println(s)); // lambda expression implementing Consumer<String>
        strings.forEach(System.out::println);  // method reference
    }
}
