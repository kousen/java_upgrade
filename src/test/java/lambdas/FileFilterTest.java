package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File dir = new File("src/main/java");

    @Test
    void listFiles() {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(21, files.length);
        }
    }

    @Test
    void listFiles_anonInnerClass() {
        File[] files = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
        if (files != null) {
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_expressionLambda() {
        File[] files = dir.listFiles(pathname -> pathname.isDirectory());
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_blockLambda() {
        File[] files = dir.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(13, files.length);
        }
    }
}
