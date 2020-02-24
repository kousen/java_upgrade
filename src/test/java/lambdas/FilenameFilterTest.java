package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FilenameFilterTest {
    @Test
    public void filenameFilterNoArgs() {
        File root = new File("src/main/java");
        File[] files = root.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(20, files.length);
    }

    @Test
    public void filenameFilterAnonInnerClass() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("java");
            }
        });
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(8, files.length);
    }

    @Test
    public void filenameFilterExpressionLambda() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(
                (directory, fileName) -> fileName.endsWith("java"));
        for (File file : files) {
            System.out.println(file.getName());
        }
        assertEquals(8, files.length);
    }
}
