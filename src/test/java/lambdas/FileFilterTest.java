package lambdas;

import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertEquals;

public class FileFilterTest {

    @Test
    public void listFilesWithoutFileFilter() {
        File dir = new File("src/main/java");
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
        assertEquals(19, files.length);
    }

    @Test
    public void fileFilter_anonInnerClass() {
        File dir = new File("src/main/java");
        File[] dirs = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(11, dirs.length);
    }

    @Test
    public void fileFilter_expressionLambda() {
        File dir = new File("src/main/java");
        File[] dirs = dir.listFiles(pathname -> pathname.isDirectory());
        assertEquals(11, dirs.length);
    }

    @Test
    public void fileFilter_methodReference() {
        File dir = new File("src/main/java");
        FileFilter filter = File::isDirectory;
        File[] dirs = dir.listFiles(filter);
        if (dirs != null) {
            assertEquals(11, dirs.length);
        }
    }
}
