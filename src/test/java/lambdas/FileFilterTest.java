package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    void testListFiles() {
        File[] files = root.listFiles();
        if (files != null) {
            assertEquals(21, files.length);
        }
    }

    @Test
    void testListFiles_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void testListFiles_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void testListFiles_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void testListFiles_assignToVariable() {
        //FileFilter filter = (File pathname) -> pathname.isDirectory();
        FileFilter filter = File::isDirectory;
        File[] directories = root.listFiles(filter);
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void testListFiles_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            assertEquals(13, directories.length);
        }
    }

    @Test
    void listListFile_fileNameFilter() {
        File[] javaSourceFiles = root.listFiles(((dir, name) -> name.endsWith(".java")));
        if (javaSourceFiles != null) {
            assertEquals(8, javaSourceFiles.length);
        }
    }

    // Use a Consumer inside forEach for a list
    @Test
    void useConsumerInsideForeach() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        Logger logger = Logger.getLogger(FileFilterTest.class.getName());
        strings.forEach(s -> {
            logger.info(s);
            System.out.println(s);
        });

        // strings.forEach(s -> System.out.println(s));
        strings.forEach(System.out::println);
    }

    @Test
    void useBiConsumerInsideForeach() {
        Map<String, Integer> map = new HashMap<>();
        map.put("this", 4);
        map.put("is", 2);
        map.put("a", 1);
        map.put("map", 3);
        map.forEach((key,value) -> System.out.println("The word " + key + " has length " + value));
    }
}
