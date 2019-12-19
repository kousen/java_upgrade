package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileFilterTest {
    @Test
    public void listAllFiles() {
        File dir = new File("src/main/java");
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(19, files.length);
    }

    @Test
    public void listFile_anonInnerClass() {
        File dir = new File("src/main/java");
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".java");
            }
        });

        assertEquals(8, files.length);
    }

    @Test
    public void listFiles_lambda() {
        File dir = new File("src/main/java");
        // Lambda must be compatibile with method in FilenameFilter, or won't compile
        // S.A.M. is "boolean accept(File, String)"
        File[] files = dir.listFiles((File f, String name) -> name.endsWith(".java"));

        assertEquals(8, files.length);
    }

    @Test
    public void listFiles_directories() {
        File dir = new File("src/main/java");
        // Lambda must be compatibile with FileFilter, or this won't compile
        // S.A.M. is "boolean accept(File)"
        FileFilter filter = file -> file.isDirectory();
        File[] directories = dir.listFiles(filter);
        assertEquals(11, directories.length);
    }

    @Test
    public void myInterface() {
        MyInterface whatev = () -> true;
    }

    @Test
    public void iterableForEach() {
        List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        integers.forEach(x -> System.out.println(x));
    }
}
