package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SuppressWarnings({"Convert2MethodRef", "Convert2Lambda", "Anonymous2MethodRef"})
public class FileFilterExercises {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assertNotNull(files);
        // The exact count may vary based on project structure
        // But we expect at least some files/directories
        assert files.length > 0;
    }

    @Test
    void listDirectories_anonInnerClass() {
        // TODO: Implement FileFilter using anonymous inner class
        // Filter for directories only
        
        // File[] dirs = root.listFiles(new FileFilter() {
        //     @Override
        //     public boolean accept(File pathname) {
        //         // Filter for directories
        //     }
        // });
        
        // assertNotNull(dirs);
        // assert dirs.length > 0;
    }

    @Test
    void listDirectories_expressionLambda() {
        // TODO: Implement the same FileFilter using expression lambda
        // File[] dirs = root.listFiles(pathname -> ...);
        
        // assertNotNull(dirs);
        // assert dirs.length > 0;
    }

    @Test
    void listDirectories_blockLambda() {
        // TODO: Implement using block lambda with logging
        // File[] dirs = root.listFiles(pathname -> {
        //     System.out.println("Checking " + pathname);
        //     // Return whether it's a directory
        // });
        
        // assertNotNull(dirs);
        // assert dirs.length > 0;
    }

    @Test
    void listDirectories_methodReference() {
        // TODO: Implement using method reference
        // Hint: Look for a method in File class that checks if it's a directory
        // File[] dirs = root.listFiles(...);
        
        // assertNotNull(dirs);
        // assert dirs.length > 0;
    }

    @Test
    void listDirectories_assignToVariable() {
        // TODO: Create a FileFilter variable and assign lambda to it
        // FileFilter filter = ...;
        // File[] dirs = root.listFiles(filter);
        
        // assertNotNull(dirs);
        // assert dirs.length > 0;
    }

    @Test
    void listJavaFiles_filenameFilter() {
        // TODO: List only .java files using FilenameFilter (two parameters)
        // File[] javaSrcFiles = root.listFiles((dir, name) -> ...);
        
        // assertNotNull(javaSrcFiles);
        // Check that we found some .java files
        // for (File file : javaSrcFiles) {
        //     assertTrue(file.getName().endsWith(".java"));
        // }
    }

    @Test
    void listJavaFiles_fileFilter() {
        // TODO: List only .java files using FileFilter (one parameter)
        // File[] javaSrcFiles = root.listFiles(file -> ...);
        
        // assertNotNull(javaSrcFiles);
        // for (File file : javaSrcFiles) {
        //     assertTrue(file.getName().endsWith(".java"));
        // }
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
}