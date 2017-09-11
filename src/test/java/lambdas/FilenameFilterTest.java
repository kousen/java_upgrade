package lambdas;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

import static org.junit.Assert.assertArrayEquals;

public class FilenameFilterTest {
    private File sourceDir = new File("src/main/java");
    private String[] javaFiles;

    @Before
    public void setUp() {
        javaFiles = sourceDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
    }

    @Test
    public void listJavaSourceFilesWithLambda() {
        String[] filesLambda = sourceDir.list(
                (directory, fileName) -> fileName.endsWith(".java"));
        assertArrayEquals(javaFiles, filesLambda);
    }
}
