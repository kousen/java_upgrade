package lambdas;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ConstantConditions")
public class FileFilterTest {

    private final File root = new File("src/main/java");

    @Test
    public void listFiles() {
        File[] files = root.listFiles();
//        for (File file : files) {
//            System.out.println(file);
//        }
        assertEquals(21, files.length);
    }

    @Test
    public void listFiles_anonInnerClass() {
        File[] directories = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_expressionLambda() {
        File[] directories = root.listFiles(pathname -> pathname.isDirectory());
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_methodReference() {
        File[] directories = root.listFiles(File::isDirectory);
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_expressionLambdaWithVariable() {
        FileFilter filter = (File pathname) -> pathname.isDirectory();
        File[] directories = root.listFiles(filter);
        assertEquals(13, directories.length);
    }

    @Test
    public void listFiles_blockLambda() {
        File[] directories = root.listFiles((File pathname) -> {
            return pathname.isDirectory();
        });
        assertEquals(13, directories.length);
    }
}
