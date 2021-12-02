package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    void listFiles_methodReference() {
        File[] files = dir.listFiles(File::isDirectory);
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(13, files.length);
        }
    }

    @Test
    void listFiles_assignToVariable() {
        // FileFilter filter = pathname -> pathname.isDirectory();
        FileFilter filter = (File pathname) -> pathname.isDirectory();
        File[] files = dir.listFiles(filter);
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

    @Test
    void listFiles_fileNameFilter() {
        FilenameFilter filter = (File dir, String name) -> name.endsWith(".java");
        File[] files = dir.listFiles(filter);
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
            assertEquals(8, files.length);
        }
    }

    @Test
    void showForEach() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        strings.forEach(string -> System.out.println(string));
        strings.forEach(System.out::println);
        strings.forEach(string -> System.out.println("The next string is " + string));

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 2);

        map.forEach((key, value) -> System.out.println(key + " maps to " + value));
    }
}
