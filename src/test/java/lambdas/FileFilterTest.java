package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {
    @Test
    public void fileFilterNoArgs() {
        File root = new File("src/main/java");
        File[] files = root.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(20, files.length);
    }

    @Test
    public void fileFilterAnonInnerClass() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(12, files.length);
    }

    @Test
    public void fileFilterExpressionLambda() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(12, files.length);
    }

    @Test
    public void fileFilterMethodReference() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(File::isDirectory);
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(12, files.length);
    }

    @Test
    public void fileFilterBlockLambda() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(12, files.length);
    }

}
