package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FileFilterExercises {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assertNotNull(files);
        // The exact count may vary based on project structure
        // But we expect at least some files/directories
        assertEquals(22, files.length);
    }

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    void listDirectories_anonInnerClass() {
        // TODO: Implement FileFilter using anonymous inner class
        // Filter for directories only

        File[] dirs = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                // Filter for directories
                return pathname.isDirectory();
            }
        });

        assertNotNull(dirs);
        assertEquals(14, dirs.length);
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void listDirectories_expressionLambda() {
        // TODO: Implement the same FileFilter using expression lambda
        File[] dirs = root.listFiles(pathname -> pathname.isDirectory());

        assertNotNull(dirs);
        assertEquals(14, dirs.length);
    }

    @Test
    void listDirectories_blockLambda() {
        // TODO: Implement using block lambda with logging
        File[] dirs = root.listFiles(pathname -> {
            System.out.println("Checking " + pathname);
            // Return whether it's a directory
            return pathname.isDirectory();
        });

        assertNotNull(dirs);
        assertEquals(14, dirs.length);
    }

    @Test
    void forEachDirectory() {
        var strings = List.of("this", "is", "a", "list", "of", "strings");
        strings.forEach(string -> System.out.println("The string is " + string));
        assertEquals(6, strings.size());
    }

    @Test
    void listDirectories_methodReference() {
        // TODO: Implement using method reference
        // Hint: Look for a method in File class that checks if it's a directory
        File[] dirs = root.listFiles(File::isDirectory);

        assertNotNull(dirs);
        assertEquals(14, dirs.length);
    }

    @Test
    void listDirectories_assignToVariable() {
        // TODO: Create a FileFilter variable and assign lambda to it
        FileFilter filter = File::isDirectory;
        File[] dirs = root.listFiles(filter);
        assertNotNull(dirs);
        assertEquals(14, dirs.length);
    }

    @Test
    void listJavaFiles_filenameFilter() {
        // TODO: List only .java files using FilenameFilter (two parameters)
        File[] javaSrcFiles = root.listFiles((dir, name) -> name.endsWith(".java"));

        assertNotNull(javaSrcFiles);
        assertEquals(8, javaSrcFiles.length);
    }

    @Test
    void listJavaFiles_fileFilter() {
        // TODO: List only .java files using FileFilter (one parameter)
        File[] javaSrcFiles = root.listFiles(file -> file.getName().endsWith(".java"));
        assertNotNull(javaSrcFiles);
        assertEquals(8, javaSrcFiles.length);
    }

    @Test
    void composedFilter() {
        // TODO: Create a filter for .java files that are larger than 1000 bytes
        // File[] largejavaFiles = root.listFiles(file -> ...);

        // assertNotNull(largejavaFiles);
        // for (File file : largejavaFiles) {
        //     assertTrue(file.getName().endsWith(".java"));
        //     assertTrue(file.length() > 1000);
        // }
    }

    @Test
    void map() {
        Map<String, Integer> map = Map.ofEntries(Map.entry("a", 1),
                Map.entry("b", 2),
                Map.entry("c", 3));
        map.forEach((k, v) -> System.out.printf("%s = %d%n", k, v));
    }
}