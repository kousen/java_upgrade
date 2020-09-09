package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void testListFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            assertEquals(21, files.length);
//            for (File file : files) {
//                System.out.println(file);
//            }
        }
    }

    @Test
    public void testListFiles_fileFilter_anonInnerClass() {
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    public void testListFiles_fileFilter_lambda() {
        // listFiles takes a lambda
        // there are two overloads of listFiles that take functional interfaces
        // this lambda has only a single argument
        // the functional interface with a single argument is FileFilter
        // its SAM is accept that takes a File and returns a boolean
        // this lambda only works if the arg is of type File
        File[] files = root.listFiles(file -> file.isDirectory());
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

}
