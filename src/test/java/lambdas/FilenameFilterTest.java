package lambdas;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class FilenameFilterTest {
    @Test
    public void testFileListWithoutFilter() {
        File dir = new File("src/main/java");
        String[] fileNames = dir.list();
        System.out.println(Arrays.asList(fileNames));
    }

    @Test
    public void testFileListWithAnonInnerClassFilter() {
        File dir = new File("src/main/java");
        String[] fileNames = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        System.out.println(Arrays.asList(fileNames));
    }

    @Test
    public void testFileListWithExpressionLambdaFilter() {
        File dir = new File("src/main/java");
        String[] fileNames = dir.list((File directory, String name) -> name.endsWith(".java"));
        System.out.println(Arrays.asList(fileNames));
    }

    @Test
    public void testFileListWithBlockLambdaFilter() {
        File dir = new File("src/main/java");
        String[] fileNames = dir.list((directory, name) -> {
            return name.endsWith(".java");
        });
        System.out.println(Arrays.asList(fileNames));
    }

    @Test
    public void testFileListWithFilterAssignment() {
        File dir = new File("src/main/java");
        FilenameFilter filter = (directory, name) -> name.endsWith(".java");
        String[] fileNames = dir.list(filter);
        System.out.println(Arrays.asList(fileNames));
    }
}
