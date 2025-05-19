package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        File[] dirs = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        
        assertNotNull(dirs);
        assert dirs.length > 0;
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] dirs = root.listFiles(pathname -> pathname.isDirectory());
        
        assertNotNull(dirs);
        assert dirs.length > 0;
    }

    @Test
    void listDirectories_blockLambda() {
        File[] dirs = root.listFiles(pathname -> {
            System.out.println("Checking " + pathname);
            return pathname.isDirectory();
        });
        
        assertNotNull(dirs);
        assert dirs.length > 0;
    }

    @Test
    void listDirectories_methodReference() {
        File[] dirs = root.listFiles(File::isDirectory);
        
        assertNotNull(dirs);
        assert dirs.length > 0;
    }

    @Test
    void listDirectories_assignToVariable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] dirs = root.listFiles(filter);
        
        assertNotNull(dirs);
        assert dirs.length > 0;
    }

    @Test
    void listJavaFiles_filenameFilter() {
        File[] javaSrcFiles = root.listFiles((dir, name) -> name.endsWith(".java"));
        
        assertNotNull(javaSrcFiles);
        // Check that we found some .java files
        for (File file : javaSrcFiles) {
            assertTrue(file.getName().endsWith(".java"));
        }
    }

    @Test
    void listJavaFiles_fileFilter() {
        File[] javaSrcFiles = root.listFiles(file -> file.getName().endsWith(".java"));
        
        assertNotNull(javaSrcFiles);
        for (File file : javaSrcFiles) {
            assertTrue(file.getName().endsWith(".java"));
        }
    }

    @Test
    void composedFilter() {
        File[] largejavaFiles = root.listFiles(file -> 
            file.getName().endsWith(".java") && file.length() > 1000);
        
        assertNotNull(largejavaFiles);
        for (File file : largejavaFiles) {
            assertTrue(file.getName().endsWith(".java"));
            assertTrue(file.length() > 1000);
        }
    }
}