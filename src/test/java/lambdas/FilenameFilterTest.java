package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import static org.junit.Assert.*;

@SuppressWarnings("ConstantConditions")
public class FilenameFilterTest {
    private File root = new File("src/main/java");

    @Test
    public void listWithoutFilter() {
        String[] files = root.list();
        assertEquals(17, files.length);
        System.out.println(Arrays.asList(files));
    }

    @Test
    public void listJavaFilesOnly_UsingAnonInnerClass() {
        String[] files = root.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        assertEquals(9, files.length);
        System.out.println(Arrays.asList(files));
    }

    @Test
    public void listJavaFilesOnly_UsingExpressionLambda() {
        String[] files = root.list(
                (directory, fileName) -> fileName.endsWith(".java"));
        assertEquals(9, files.length);
        System.out.println(Arrays.asList(files));
    }

    @Test
    public void listJavaFilesOnly_UsingVariable() {
        FilenameFilter filenameFilter =
                (File directory, String fileName) -> fileName.endsWith(".java");
        String[] files = root.list(filenameFilter);
        assertEquals(9, files.length);
        System.out.println(Arrays.asList(files));
    }
}
