package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilenameFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void testListFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            Arrays.stream(files)
                    .forEach(System.out::println);
            assertEquals(21, files.length);
        }
    }

    @Test
    public void testListJavaSourceFiles_anonInnerClass() {
        File[] files = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(8, files.length);
        }
    }

    @Test
    public void testListJavaSourceFiles_expressionLambda() {
        File[] files = root.listFiles(((dir, name) -> name.endsWith(".java")));
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(8, files.length);
        }
    }

    @Test
    public void testListJavaSourceFiles_blockLambda() {
        File[] files = root.listFiles(((dir, name) -> {
            return name.endsWith(".java");
        }));
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(8, files.length);
        }
    }

}
