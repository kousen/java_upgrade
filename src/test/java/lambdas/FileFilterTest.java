package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assert files != null;
        assertEquals(22, files.length);
        assertThat(files.length).isEqualTo(22);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assert directories != null;
        assertEquals(14, directories.length);
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listDirectories_expressionLambda() {
        FileFilter filter = pathname -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
        assertThat(directories.length).isEqualTo(14);
    }

    @Test
    void listDirectories_blockLambda() {
        FileFilter filter = pathname -> {
            return pathname.isDirectory();
        };
        File[] directories = root.listFiles(filter);
        assert directories != null;
        assertEquals(14, directories.length);
        assertThat(directories.length).isEqualTo(14);
    }
}
