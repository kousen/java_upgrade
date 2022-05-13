package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        Arrays.stream(files)
                .forEach(System.out::println);
    }

    @Test
    void listDirectories() {
        File[] files = root.listFiles(File::isDirectory);
        Arrays.stream(files)
                .forEach(System.out::println);
    }

    @Test
    void listJavaSourceFiles_fileFilter() {
        File[] files = root.listFiles(File::isFile);
        Arrays.stream(files)
                .filter(file -> file.getName().endsWith(".java"))
                .forEach(System.out::println);
    }

    @Test
    void listJavaSourceFiles_fileNameFilter() {
        FilenameFilter filter = (dir, name) -> name.endsWith(".java");
        File[] javaSourceFiles = root.listFiles(filter);
        Arrays.stream(javaSourceFiles)
                .forEach(System.out::println);
    }
}
