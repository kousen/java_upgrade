package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTests {
    @Test
    public void fileFilterWithoutArgs() {
        File root = new File("src/main/java");
        File[] files = root.listFiles();
        for (File f : files) {
            System.out.println(f);
        }
        assertEquals(19, files.length);
    }

    @Test
    public void fileFilterListDirectories() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        assertEquals(11, files.length);
    }

    @Test
    public void fileFilterListDirectoriesUsingExpressionLambda() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(11, files.length);
    }

    @Test
    public void fileFilterListDirectoriesUsingBlockLambda() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(pathname -> {
            return pathname.isDirectory();
        });
        assertEquals(11, files.length);
    }

    @Test
    public void fileFilterListDirectoriesUsingMethodReference() {
        File root = new File("src/main/java");
        File[] files = root.listFiles(File::isDirectory);
        assertEquals(11, files.length);
    }

    @Test
    public void fileNameFilterShowJavaFiles() {
        File root = new File("src/main/java");
        File[] files = root.listFiles((directory, fileName) -> fileName.endsWith(".java"));
//        for (File file : files) {
//            System.out.println(file);
//        }
        assertEquals(8, files.length);

        Stream.of(files).forEach(f -> System.out.println(f));

        List<String> stringList = Arrays.asList("first", "second", "third");
        stringList.forEach(System.out::println);

        Map<String, Integer> map = new HashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 2);

        map.forEach((k, v) -> System.out.println(k + " maps to " + v));
    }


}
