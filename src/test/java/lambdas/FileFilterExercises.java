package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"Convert2MethodRef", "Convert2Lambda", "Anonymous2MethodRef"})
public class FileFilterExercises {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assertNotNull(files);
        // The exact count may vary based on project structure
        // But we expect at least some files/directories
        for (File file : files) {
            System.out.println(file);
        }
        assertTrue(files.length > 0);
    }

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
         assertTrue(dirs.length > 0);
    }

    @Test
    void listDirectories_expressionLambda() {
        // TODO: Implement the same FileFilter using expression lambda
         File[] dirs = root.listFiles(pathname -> pathname.isDirectory());
        
         assertNotNull(dirs);
         assertTrue(dirs.length > 0);
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
        FileFilter filter =pathname -> pathname.isDirectory();
        File[] dirs = root.listFiles(filter);
        
         assertNotNull(dirs);
        assertEquals(14, dirs.length);
    }

    @Test
    void listJavaFiles_filenameFilter() {
        // TODO: List only .java files using FilenameFilter (two parameters)
        FilenameFilter filter = (File dir, String name) -> name.endsWith(".java");
        File[] javaSrcFiles = root.listFiles(filter);
        
         assertNotNull(javaSrcFiles);
         // Check that we found some .java files
         for (File file : javaSrcFiles) {
             assertTrue(file.getName().endsWith(".java"));
         }
        assertEquals(8, javaSrcFiles.length);
    }

    @Test
    void listJavaFiles_fileFilter() {
        // TODO: List only .java files using FileFilter (one parameter)
         File[] javaSrcFiles = root.listFiles(file -> file.getName().endsWith(".java"));
        System.out.println(Arrays.toString(javaSrcFiles));
        
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
    void mapForEach() {
        Map<String, String> map = Map.ofEntries(
                Map.entry("key1", "value1"),
                Map.entry("key2", "value2"),
                Map.entry("key3", "value3")
        );

        // Iterate over the map of entries
        for (var entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // Iterate over the set of keys
        for (String key : map.keySet()) {
            System.out.println(key + " = " + map.get(key));
        }

        // Use forEach(BiConsumer) method
        map.forEach((k,v) -> System.out.println(k + " maps to " + v));
    }
}