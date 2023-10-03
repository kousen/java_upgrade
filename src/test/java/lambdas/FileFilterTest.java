package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void testListFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
    }

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_expression_lambda() {
        File[] directories = root.listFiles(file -> file.isDirectory());
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_lambda_variable() {
        FileFilter fileFilter = file -> file.isDirectory();
        File[] directories = root.listFiles(fileFilter);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @SuppressWarnings({"Convert2MethodRef", "CodeBlock2Expr"})
    @Test
    void listDirectories_block_lambda() {
        File[] directories = root.listFiles(file -> {
            return file.isDirectory();
        });
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listDirectories_method_reference() {
        File[] directories = root.listFiles(File::isDirectory);
        assert directories != null;
        assertEquals(14, directories.length);
    }

    @Test
    void listJavaSrcFiles_fileFilter() {
        File[] javaFiles = root.listFiles(file -> file.getName().endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @Test
    void listJavaSrcFiles_fileNameFilter() {
        File[] javaFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        assert javaFiles != null;
        assertEquals(8, javaFiles.length);
    }

    @Test
    void forEach_on_a_list() {
        List<String> strings = List.of("this", "is", "a", "list");
        // Java 5+
        for (String string : strings) {
            System.out.println(string);
        }

        // Java 8+
        strings.forEach(System.out::println);

        // Parallel stream
        strings.parallelStream().forEach(System.out::println);
    }

}
