package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("Duplicates")
public class FileFilterTests {
    @Test
    public void listFilesWithoutFilter() {
        File dir = new File("src/main/java");
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(18, files.length);
    }

    @Test
    public void listOnlyDirectories_usingAnonInnerClass() {
        File dir = new File("src/main/java");
        File[] directories = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        for (File file : directories) {
            System.out.println(file);
        }
        assertEquals(10, directories.length);
    }

    @Test
    public void listOnlyDirectories_usingLambda() {
        File dir = new File("src/main/java");
        File[] directories = dir.listFiles(path -> path.isDirectory());
        for (File file : directories) {
            System.out.println(file);
        }
        assertEquals(10, directories.length);
    }

    @Test
    public void listOnlyDirectories_usingMethodRef() {
        File dir = new File("src/main/java");
        File[] directories = dir.listFiles(File::isDirectory);
        for (File file : directories) {
            System.out.println(file);
        }
        assertEquals(10, directories.length);
    }
}
