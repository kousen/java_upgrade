package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.assertj.core.api.Assertions.assertThat;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = root.listFiles();
        assertThat(files)
                .isNotNull()
                .isNotEmpty()
                .hasSize(22);
    }

    @Test
    void listDirectories_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertThat(directories)
                .isNotNull()
                .isNotEmpty()
                .hasSize(14);
    }

    @Test
    void listDirectories_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assertThat(directories)
                .isNotNull()
                .isNotEmpty()
                .hasSize(14);
    }

    @Test
    void listDirectories_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            System.out.println("Checking " + pathname);
            return pathname.isDirectory();
        });
        assertThat(directories)
                .isNotNull()
                .isNotEmpty()
                .hasSize(14);
    }
}
