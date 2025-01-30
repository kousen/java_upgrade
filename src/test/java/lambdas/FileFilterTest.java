package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] dirs = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert dirs != null;
        assertEquals(14, dirs.length);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] dirs = root.listFiles(pathname -> pathname.isDirectory());
        assert dirs != null;
        assertEquals(14, dirs.length);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] dirs = root.listFiles(pathname -> {
            System.out.println("Checking " + pathname);
            return pathname.isDirectory();
        });
        assert dirs != null;
        assertEquals(14, dirs.length);
    }

    @Test
    void listDirectories_assignToVariable() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] dirs = root.listFiles(filter);
        assert dirs != null;
        assertEquals(14, dirs.length);
    }

}
