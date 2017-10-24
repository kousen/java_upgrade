package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilenameFilterTest {
    private File root = new File("src/main/java");

    @Test
    public void checkAllFiles() {
        String[] files = root.list();
        assertEquals(17, files.length);
    }

    @Test
    public void listOnlyJavaSourceFilesUsingAnonInnerClass() {
        String[] files = root.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        assertEquals(9, files.length);
    }

    @Test
    public void listOnlyJavaSourceFilesUsingLambda() {
        FilenameFilter filter = (directory, name) -> name.endsWith(".java");
        String[] files = root.list(filter);
        assertEquals(9, files.length);

        Arrays.stream(files)
                .forEach(name -> assertTrue(name.endsWith(".java")));
    }
}
