package lambdas;

import org.junit.jupiter.api.DisplayName;
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
    public void testListFiles() {
        File[] files = root.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(21, files.length);
    }

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    public void testListFiles_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void testListFiles_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(13, directories.length);
    }

    @Test
    public void testListFiles_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        assertEquals(13, directories.length);
    }

    @SuppressWarnings({"Convert2MethodRef", "CodeBlock2Expr"})
    @Test
    public void testListFiles_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void testListFiles_assignedToVariable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assertEquals(13, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    private FileFilter getDirectories() {
        return pathname -> pathname.isDirectory();
    }

    @Test
    public void testListFiles_getLambdaFromMethod() {
        File[] directories = root.listFiles(getDirectories());
        assertEquals(13, directories.length);
    }

    @Test @DisplayName("find Java source files only, using FileFilter")
    void testListFiles_javaSrc_fileFilter() {
        File[] javaSrc = root.listFiles(pathname -> pathname.getName().endsWith(".java"));
        assertEquals(8, javaSrc.length);
    }

    @Test @DisplayName("find Java source files only, using FilenameFilter")
    void testListFiles_javaSrce_filenameFilter() {
        File[] javaSrc = root.listFiles((dir, name) -> name.endsWith(".java"));
        assertEquals(8, javaSrc.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void forEachOnIterable() {
        List<String> strings = Arrays.asList("this", "is", "a", "list");
        // For-each loop
        for (String string : strings) {
            System.out.println(string);
        }

        // forEach method on Iterable
        strings.forEach(string -> System.out.println(string));
    }
}
