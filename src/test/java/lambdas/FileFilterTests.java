package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTests {
    @Test
    public void fileFilterWithoutArgs() {
        File root = new File("src/main/java");
        File[] files = root.listFiles();
        for (File f : files) {
            System.out.println(f);
        }
        assertEquals(19, files.length);
    }

    @Test
    public void fileFilterListDirectories() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        assertEquals(11, files.length);
    }

    @Test
    public void fileFilterListDirectoriesUsingExpressionLambda() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(11, files.length);
    }

    @Test
    public void fileFilterListDirectoriesUsingBlockLambda() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assertEquals(11, files.length);
    }

    @Test
    public void fileFilterListDirectoriesUsingMethodReference() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(File::isDirectory);
        assertEquals(11, files.length);
    }
}
