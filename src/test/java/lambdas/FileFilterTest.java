package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileFilterTest {
    private final File root = new File("src/main/java");

    @Test
    public void listFiles_withoutFilter() {
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file);
            }
        }
    }

    @Test
    public void listFiles_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file);
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listFiles_expressionLambda() {
        // File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        File[] directories = root.listFiles(File::isDirectory);
        //File[] directories = root.listFiles((File pathname) -> pathname.isDirectory());
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file);
            }
            assertEquals(13, directories.length);
        }
    }

    @SuppressWarnings({"CodeBlock2Expr", "Convert2MethodRef"})
    @Test
    public void listFiles_blockLambda() {
        File[] directories = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        if (directories != null) {
            for (File file : directories) {
                System.out.println(file);
            }
            assertEquals(13, directories.length);
        }
    }

    @Test
    public void listJavaSrcFiles() {
        FilenameFilter filter = (directory, fileName) -> fileName.endsWith(".java");
        File[] javaSrcFiles = root.listFiles(filter);
        if (javaSrcFiles != null) {
            for (File file : javaSrcFiles) {
                System.out.println(file);
            }
            assertEquals(8, javaSrcFiles.length);
        }
    }

    @Test
    void iterableWithAConsumer() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        // loop over strings
        for (String s : strings) {
            System.out.println(s);
        }

        // use forEach(Consumer)
        strings.forEach(System.out::println);
        // no method reference equivalent:
        strings.forEach(s -> System.out.println("the next word is " + s));

        // iterate over a map using a BiConsumer and print to console
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1); map.put("b", 2); map.put("c", 2);
        map.forEach((key,value) -> System.out.println(key + " maps to " + value));

        // log to standard error (default)
        Logger logger = Logger.getLogger("myLogger");
        map.forEach((key,value) -> logger.info(key + " maps to " + value));

        // lambda "composition" --> put two lambdas together
        BiConsumer<String, Integer> consolePrint = (key, value) -> System.out.println(key + " maps to " + value);
        BiConsumer<String, Integer> logInfoPrint = (key, value) -> logger.info(key + " maps to " + value);
        map.forEach(consolePrint.andThen(logInfoPrint));
    }
}
