package lambdas;

import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {
    @Test
    public void checkNumberOfDirectories_UsingAnonInnerClass() {
        File dir = new File("src/main/java");
        int numDirectories = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        }).length;

        assertEquals(8, numDirectories);
    }

    @Test
    public void checkNumberOfDirectories_UsingLambda() {
        File dir = new File("src/main/java");
        // int numDirectories = dir.listFiles((File f) -> f.isDirectory()).length;
        //int numDirectories = dir.listFiles(f -> f.isDirectory()).length;
        FileFilter filter = f -> f.isDirectory();
        int numDirectories = dir.listFiles(filter).length;

        assertEquals(8, numDirectories);
    }

    @Test
    public void checkNumberOfDirectories_UsingMethodReference() {
        File dir = new File("src/main/java");

//        FileFilter filter = File::isDirectory;
//        int numDirectories = dir.listFiles(filter).length;

        assertEquals(8, dir.listFiles(File::isDirectory).length);
    }
}
