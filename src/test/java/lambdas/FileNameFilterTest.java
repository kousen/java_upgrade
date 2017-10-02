package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

import static org.junit.Assert.assertEquals;

public class FileNameFilterTest {
    @Test
    public void listAll() {
        File root = new File("src/main/java");
        String[] files = root.list();
        // 8 folders + 9 files
        assertEquals(17, files.length);
    }

    @Test
    public void listJustJavaSourceFiles_UsingAnonInnerClass() {
        File root = new File("src/main/java");
        String[] files = root.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        assertEquals(9, files.length);
    }

    @Test
    public void listJustJavaSourceFiles_UsingLambda() {
        File root = new File("src/main/java");
        String[] files = root.list((dir, name) -> name.endsWith(".java"));
        assertEquals(9, files.length);
    }
}
